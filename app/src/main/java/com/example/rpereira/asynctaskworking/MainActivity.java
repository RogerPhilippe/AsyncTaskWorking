package com.example.rpereira.asynctaskworking;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static ImageView mIvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("AsyncTask", "Elementos de tela criados e atribuidos Thread: " +
                Thread.currentThread().getName());

        mIvMain = findViewById(R.id.idimMain);

        String mURL = "https://avatars1.githubusercontent.com/u/17157253?s=460&v=4";
        chamarAsyncTask(mURL);

    }

    private void chamarAsyncTask(String endereco){
        TarefaDownload download = new TarefaDownload(MainActivity.this);
        Log.i("AsyncTask", "AsyncTask senado chamado Thread: " +
                Thread.currentThread().getName());
        download.execute(endereco);
    }

}
