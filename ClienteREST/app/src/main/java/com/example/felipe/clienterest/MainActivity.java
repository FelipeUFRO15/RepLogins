package com.example.felipe.clienterest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textoResultado = (TextView) findViewById(R.id.textoResultado);
    }

    public void ejecutarCliente(View view){
        GetWebService geth = new GetWebService();
        geth.execute();
    }

    private class GetWebService extends AsyncTask<String, String, Boolean>{

        String resultado;

        @Override
        protected Boolean doInBackground(String... params){
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

        @Override
        public void onPostExecute(Boolean bool){
            if(bool) textoResultado.setText(resultado);
            else textoResultado.setText("No hay respuesta");
        }
    }
}
