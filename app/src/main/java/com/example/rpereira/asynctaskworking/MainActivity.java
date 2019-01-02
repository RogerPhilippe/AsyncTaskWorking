package com.example.rpereira.asynctaskworking;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mIvMain;

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
        TarefaDownload download = new TarefaDownload();
        Log.i("AsyncTask", "AsyncTask senado chamado Thread: " +
                Thread.currentThread().getName());
        download.execute(endereco);
    }

    private class TarefaDownload extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute(){
            Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " +
                    Thread.currentThread().getName());
            mLoad = ProgressDialog.show(MainActivity.this, "Por favor Aguarde ...",
                    "Baixando Imagem ...");
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            Bitmap imageBitmap = null;

            try {
                Log.i("AsyncTask", "Baixando a imagem Thread: " +
                        Thread.currentThread().getName());
                imageBitmap = Auxiliar.baixarImagem(params[0]);
            } catch (Exception er) { er.printStackTrace(); }

            return imageBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap!=null) {
                mIvMain.setImageBitmap(bitmap);
                Log.i("AsyncTask", "Exibindo Bitmap Thread: " + Thread.currentThread().getName());
            } else {
                Log.i("AsyncTask", "Erro ao baixar a imagem " +
                        Thread.currentThread().getName());
            }
            Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " +
                    Thread.currentThread().getName());
            mLoad.dismiss();
        }

    }

}
