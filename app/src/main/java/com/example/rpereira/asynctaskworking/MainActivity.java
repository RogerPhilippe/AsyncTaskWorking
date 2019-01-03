package com.example.rpereira.asynctaskworking;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static ImageView mIvMain;

    private ProgressDialog mLoad;

    private String mURL = "https://avatars1.githubusercontent.com/u/17157253?s=460&v=4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("AsyncTask", "Elementos de tela criados e atribuidos Thread: " +
                Thread.currentThread().getName());

        mIvMain = (ImageView) findViewById(R.id.idimMain);

        chamarAsyncTask(mURL);

    }

    private void chamarAsyncTask(String endereco){
        TarefaDownload download = new TarefaDownload(MainActivity.this);
        Log.i("AsyncTask", "AsyncTask senado chamado Thread: " +
                Thread.currentThread().getName());
        download.execute(endereco);
    }

}
