package com.example.felipe.redesylogin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Felipe on 17-09-2016.
 */
public class LoginActivity extends AppCompatActivity {

    Button botonIngresar;
    EditText editNombre;
    EditText editPass;
    String nombre;
    String contraseña;


    public void sayToast(String str){
        Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.botonIngresar = (Button) findViewById(R.id.botonIngresar);

        this.editNombre = (EditText) findViewById(R.id.editNombre);
        this.editPass = (EditText) findViewById(R.id.editPass);

        this.nombre = editNombre.getText().toString();
        this.contraseña = editPass.getText().toString();

        DataBaseHelper dbHelper = new DataBaseHelper(this, "BASE", null, 1);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Primero comprobamos si es que existe o no un usuario en la base
        //permite posicionarse en filas de la base

        botonIngresar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Cursor c = db.rawQuery("SELECT usuario,contrasena FROM BASE WHERE usuario='" + nombre + "' AND contrasena='" + contraseña + "'", null);
                if (c.moveToFirst()){
                    if (nombre.equals(c.getString(0)) && contraseña.equals(c.getString(1))){
                        Intent redes = new Intent(getApplicationContext(), RedesActivity.class);
                        startActivity(redes);
                    }else sayToast("Datos incorrectos");
                }
            }
        });
    }

    public void abrirRegistrar(View view){
        Intent registro = new Intent(this, RegistroActivity.class);
        startActivity(registro);
    }
}
