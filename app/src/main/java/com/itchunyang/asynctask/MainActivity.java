package com.itchunyang.asynctask;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
    }

    public void downImage(View view) {

        //注意每次需new一个实例,新建的任务只能执行一次,否则会出现异常
        ImageAsyncTask task = new ImageAsyncTask();
        task.setOnDataFinishedListener(new ImageAsyncTask.onDataFinishedListener() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                iv.setImageBitmap(bitmap);
            }

            @Override
            public void onFailed() {

            }
        });
        task.execute("http://www.hsdcw.com/photo/upload/2013-7-29/2013729112104971.jpg");

        //取消一个正在执行的任务,onCancelled方法将会被调用
        //当调用cancel()后，在doInBackground（）return后 我们将会调用onCancelled(Object) 不在调用onPostExecute(Object)
        //为了保证任务更快取消掉，你应该在doInBackground（）周期性的检查iscancelled 去进行判断。
//        task.cancel(true);
    }
}
