package com.example.felipe.clientesoap;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    private String[] elementos;
    private Spinner spinnerElementos;
    private TextView textoResultado;
    private String resultado;
    private String NAMESPACE = "http://www.webserviceX.NET";
    private String URL="http://www.webservicex.net/periodictable.asmx";
    private String METHOD_NAME = "GetAtomicNumber";
    private String SOAP_ACTION = "http://www.webserviceX.NET/GetAtomicNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        this.textoResultado = (TextView) findViewById(R.id.textoResultado);

        //http://webservicex.net/New/Home/ServiceDetail/19
        //http://www.webservicex.net/periodictable.asmx/GetAtomicNumber
        //http://www.sgoliver.net/blog/acceso-a-servicios-web-soap-en-android-22/
        //http://www.sgoliver.net/blog/acceso-a-servicios-web-rest-en-android-22/
        //http://www.sgoliver.net/blog/interfaz-de-usuario-en-android-controles-de-seleccion-i/
        //https://github.com/FelipeUFRO15/TareasAndroid.git
        /**
        this.elementos = new String[]{"Hidrógeno", "Helio", "Litio", "Berilio", "Boro", "Carbono", "Nitrogeno", "Oxígeno", "Flúor", "Neón"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.content_main, elementos);

        this.spinnerElementos = (Spinner) findViewById(R.id.spinnerElementos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerElementos.setAdapter(adaptador);

        this.spinnerElementos.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        Toast.makeText(getApplicationContext(), "Elemento: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        //
                    }
                });
         */

        if(obtenerRespuesta()) this.textoResultado.setText(this.resultado);
        else this.textoResultado.setText("Sin respuesta");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean obtenerRespuesta(){
        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            SoapObject request = new SoapObject(this.NAMESPACE, this.METHOD_NAME);
            request.addProperty("ElementName", "Helium");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(this.URL);

            transport.call(this.SOAP_ACTION, envelope);

            SoapPrimitive respuesta =(SoapPrimitive)envelope.getResponse();
            this.resultado = respuesta.toString();

            return true;
        }
        catch (Exception e) {
            Log.e("ERROR: ", e.getMessage());
            return false;
        }
    }
}
