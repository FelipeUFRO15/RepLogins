package com.example.felipe.redesylogin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Felipe on 21-09-2016.
 */
public class RegistroActivity extends AppCompatActivity {
    Button botonRegistrar;
    EditText editNombreR;
    EditText editPassR;

    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_registro);

        this.editNombreR = (EditText) findViewById(R.id.editNombreR);
        this.editPassR = (EditText) findViewById(R.id.editPassR);
    }

    public void registrar(View view){
        String nombre = this.editNombreR.getText().toString();
        String pass = this.editPassR.getText().toString();

        DataBaseHelper helper = new DataBaseHelper(this, "BASE", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("usuario", nombre);
        values.put("contrasena", pass);

        db.insert("BASE", null, values);
        db.close();

        Intent in = new Intent(this, RedesActivity.class);
        startActivity(in);
    }
}
