package com.example.gestion_usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity6 extends AppCompatActivity {

    EditText correo;
    EditText nombreUsuario;
    EditText apellidosUsuario;
    EditText usuarioUsuario;
    Button buscar;
    DBAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        correo = (EditText) findViewById(R.id.textEmailUsuario);
        nombreUsuario = (EditText) findViewById(R.id.editNombreUsuario);
        apellidosUsuario = (EditText) findViewById(R.id.editApellidosUsuario);
        usuarioUsuario = (EditText) findViewById(R.id.editusuUsuario);
        buscar = (Button) findViewById(R.id.btBuscar);
        db = new DBAccess(this);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datos[] = new String[]{"","",""};
               if (validar_correo()){
                   if (db.existeUsuario(correo.getText().toString())) {

                       datos = db.getUsuario(correo.getText().toString());

                       nombreUsuario.setText(datos[0]);
                       apellidosUsuario.setText(datos[1]);
                       usuarioUsuario.setText(datos[2]);
                   }
                   else Toast.makeText(getApplicationContext(), "El usuario no existe.", Toast.LENGTH_LONG).show();
               }
               else
                   Toast.makeText(getApplicationContext(), "Introduzca un correo valido", Toast.LENGTH_LONG).show();
            }
        });
    }
    private Boolean validar_correo(){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo.getText().toString());

        if (correo.getText().toString().equals("")){
            correo.setTextColor(Color.RED);
            correo.setText("No puede estar vacio");
            return false;
        }
        if (!mather.find()){
            correo.setTextColor(Color.RED);
            correo.setText("correo no valido");
            return false;
        }
        else return true;
    }
}