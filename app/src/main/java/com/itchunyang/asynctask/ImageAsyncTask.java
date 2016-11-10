package com.itchunyang.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by luchunyang on 2016/11/9.
 */

//输入参数  后台执行进度  后台执行结果
public class ImageAsyncTask extends AsyncTask<String,Void,Bitmap> {

    public static final String TAG = ImageAsyncTask.class.getSimpleName();
    private onDataFinishedListener listener;

    //UI线程运行,被调用后立即执行，一般用来在执行后台任务前对UI做一些标记
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //只有这个是子线程
    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    //在调用publishProgress(Progress... values)时，此方法被执行，直接将进度信息更新到UI组件上。
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    //当后台操作结束时，此方法将会被调用
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null && listener != null)
            listener.onSuccess(bitmap);
        else
            listener.onFailed();
    }

    public void setOnDataFinishedListener(onDataFinishedListener listener){
        this.listener = listener;
    }

    public static interface onDataFinishedListener {
        public void onSuccess(Bitmap bitmap);
        public void onFailed();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(Bitmap bitmap) {
        super.onCancelled(bitmap);
    }
}
