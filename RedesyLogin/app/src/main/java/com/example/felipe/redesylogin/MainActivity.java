package com.example.felipe.redesylogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continuar = (Button) findViewById(R.id.botonContinuar);
        continuar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
            }
        });

    }
}
