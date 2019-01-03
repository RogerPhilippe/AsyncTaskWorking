package com.example.rpereira.asynctaskworking;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

public class TarefaDownload extends AsyncTask<String, Void, Bitmap> {

    private ProgressDialog mLoad;

    @SuppressLint("StaticFieldLeak")
    private Context mContext;

    TarefaDownload(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onPreExecute(){
        Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " +
                Thread.currentThread().getName());
        mLoad = ProgressDialog.show(mContext, "Por favor Aguarde ...",
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
            MainActivity.mIvMain.setImageBitmap(bitmap);
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
