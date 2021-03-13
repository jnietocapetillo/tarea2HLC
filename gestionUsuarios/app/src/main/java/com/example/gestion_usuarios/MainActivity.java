package com.example.gestion_usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre;
    EditText password;
    Button acceder;
    Button registro;
    DBAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.editNombreUsuario);
        password = (EditText) findViewById(R.id.editPassword);
        acceder = (Button) findViewById(R.id.btAcceder);
        registro = (Button) findViewById(R.id.btRegistro);
        db = new DBAccess(this);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(i);
            }
        });

        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean correcto = false;
                //comprobamos si el usuario esta en nuestra base de datos para poder acceder a gestion de usuario
                correcto = db.buscaUsuario(nombre.getText().toString(), password.getText().toString());
                if (correcto){
                    Toast.makeText(getApplicationContext(), "Usuario Correcto", Toast.LENGTH_LONG).show();
                    Intent i= new Intent(getApplicationContext(),MainActivity3.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(), "Usuario y contraseña incorrecto", Toast.LENGTH_LONG).show();
            }
        });



    }


}