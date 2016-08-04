package com.a.mythread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    private ImageView mImageView;
    private Button mLoadImageButton;
    private Button mToastButton;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.activity_main_image_view);
        mLoadImageButton = (Button) findViewById(R.id.activity_main_load_image_button);
        mToastButton = (Button) findViewById(R.id.activity_main_toast_button);
        mProgressBar = (ProgressBar) findViewById(R.id.activity_main_progress_bar);

        mLoadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                loadImage();
                new LoadImageTask().execute();
            }
        });
        mToastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "abc", Toast.LENGTH_SHORT).show();
            }
        });
    }

   /* private void loadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
               final Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mImageView.post(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();
    }*/
    class LoadImageTask extends AsyncTask<Void,Integer,Bitmap>{
       @Override
       protected void onPreExecute() {
           mProgressBar.setVisibility(View.VISIBLE);
       }

       @Override
       protected Bitmap doInBackground(Void... params) {
           for(int i = 1;i < 11;i++){
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               publishProgress(i * 10);
           }
           Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
           return bitmap;
       }

       @Override
       protected void onProgressUpdate(Integer... values) {
           mProgressBar.setProgress(values[0]);
       }

       @Override
       protected void onPostExecute(Bitmap bitmap) {
          mImageView.setImageBitmap(bitmap);
       }
   }
}
