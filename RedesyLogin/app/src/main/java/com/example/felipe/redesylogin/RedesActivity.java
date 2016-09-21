package com.example.felipe.redesylogin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Felipe on 20-09-2016.
 */
public class RedesActivity extends AppCompatActivity {

    TextView textoBienvenida;
    EditText textoMensaje;
    Button botonCompartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redes);

        textoBienvenida = (TextView) findViewById(R.id.textoBienvenida);
        textoMensaje = (EditText) findViewById(R.id.textoMensaje);
        botonCompartir = (Button) findViewById(R.id.botonCompartir);
        EditText text = (EditText) findViewById(R.id.editNombre);
        textoBienvenida.setText("Bienvenido");


        botonCompartir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                String mensaje = textoMensaje.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, mensaje);
                //intent.setPackage("com.whatsapp"); Comentar para poder elegir dentro de la app el método para compartir o cambiar package
                startActivty(Intent.createChooser(intent, "Share with")) 
            }
        });
    }
}
