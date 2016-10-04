package com.example.daniel.ejemplohilos;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String resultado;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textoResultado = (TextView) findViewById(R.id.textoResultado);

        ///////////////////////////////////////////////////////////////////////////////////////////
        new Thread(new Runnable() {
            public void run() {
                ///////////////////////////////////////////////////////////////////////////////////
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (rest()) textoResultado.setText("HILO SECUNDARIO\n" + resultado);
                        else textoResultado.setText("no funciona xdxdXDXDXdxd");
                    }
                });
                ///////////////////////////////////////////////////////////////////////////////////
                /**if (rest()) textoResultado.post(new Runnable() {
                    @Override
                    public void run() {
                        textoResultado.setText("HILO SECUNDARIO\n" + resultado);
                    }
                });
                else textoResultado.post(new Runnable() {
                    @Override
                    public void run() {
                        textoResultado.setText("no funciona xdxdXDXDXdxd");
                    }
                });*///////////////////////////////////////////////////////////////////////////////
            }
        }).start();
        ///////////////////////////////////////////////////////////////////////////////////////////
    }

    private Boolean rest(){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        HttpClient cliente = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://indicadoresdeldia.cl/webservice/indicadores.json");
        get.setHeader("content-type", "application/json");

        try{
            HttpResponse respuesta = cliente.execute(get);
            this.resultado = EntityUtils.toString(respuesta.getEntity());
            JSONObject jobjeto = new JSONObject(resultado);
            return true;
        }catch (Exception e){
            Log.e("Error: ", e.getMessage());
            return false;
        }
    }

    public class tareaAsincrona extends AsyncTask<String, String, Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            return true;
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Boolean result) {

        }

        @Override
        protected void onCancelled() {

        }
    }
}
