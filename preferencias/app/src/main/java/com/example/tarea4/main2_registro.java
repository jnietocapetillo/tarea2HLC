package com.example.tarea4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class main2_registro extends AppCompatActivity {

    EditText usuario;
    EditText pass;
    EditText pass2;
    Button aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario = (EditText) findViewById(R.id.textNombre);
        pass = (EditText) findViewById(R.id.textPass);
        pass2 = (EditText) findViewById(R.id.textPass2);
        aceptar = (Button) findViewById(R.id.btnAceptar);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //llamamos a comprobar que las 2 contraseñas son iguales y los campos no estan vacios
                Boolean correcto;
                correcto = validarCampos();
                if (correcto){
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
            }
        });

    }

    private boolean validarCampos(){
        Boolean ok = true;
        String usu = usuario.getText().toString();
        String pass1 = pass.getText().toString();
        String pass11 = pass2.getText().toString();

        if (usu.compareTo("") == 0){
            Toast.makeText(getApplicationContext(), "El nombre no puede estar vacío", Toast.LENGTH_LONG).show();
            ok=false;
        }
        if (pass1.compareTo("") == 0 || pass11.compareTo("")==0){
            Toast.makeText(getApplicationContext(), "La contraseña no puede estar vacía", Toast.LENGTH_LONG).show();
            ok=false;
        }
        else{
            if (pass1.compareTo(pass11)!=0) {
                Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                ok = false;
            }
        }
        return ok;
    }
}