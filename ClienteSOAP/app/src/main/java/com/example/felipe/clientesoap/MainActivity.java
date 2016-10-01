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
    private String elemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textoResultado = (TextView) findViewById(R.id.textoResultado);

        this.elementos = new String[]{"Hidrógeno", "Helio", "Litio", "Berilio", "Boro", "Carbono", "Nitrogeno", "Oxígeno", "Flúor", "Neón"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, elementos);

        this.spinnerElementos = (Spinner) findViewById(R.id.spinnerElementos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerElementos.setAdapter(adaptador);

        this.spinnerElementos.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        switch (position) {
                            case 0: elemento = "Hydrogen";
                                    break;
                            case 1: elemento = "Helium";
                                    break;
                            case 2: elemento = "Lithium";
                                    break;
                            case 3: elemento = "Beryllium";
                                    break;
                            case 4: elemento = "Boron";
                                    break;
                            case 5: elemento = "Carbon";
                                    break;
                            case 6: elemento = "Nitrogen";
                                    break;
                            case 7: elemento = "Oxygen";
                                    break;
                            case 8: elemento = "Flourine";
                                //Debería ser Fluorine, web service tiene nombre incorrecto
                                    break;
                            case 9: elemento = "Neon";
                                    break;
                        }
                        if(obtenerRespuesta()) textoResultado.setText("Datos del elemento seleccionado:\n\n" + resultado);
                        else textoResultado.setText("Datos del elemento seleccionado:\nSin respuesta");
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        //
                    }
                });
    }

    public boolean obtenerRespuesta(){
        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            SoapObject request = new SoapObject(this.NAMESPACE, this.METHOD_NAME);
            request.addProperty("ElementName", this.elemento);

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
