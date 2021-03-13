package com.example.gestionActores;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity2 extends AppCompatActivity {

    EditText nombre;
    EditText apellidos;
    EditText email;
    EditText usuario;
    EditText pass1;
    EditText pass2;
    Button aceptar;

    DBAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nombre = (EditText) findViewById(R.id.editnewNombre);
        apellidos = (EditText) findViewById(R.id.editApellidos);
        email = (EditText) findViewById(R.id.editnewEmail);
        usuario = (EditText) findViewById(R.id.editNombreUsuario);
        pass1 = (EditText) findViewById(R.id.editPass);
        pass2 = (EditText) findViewById(R.id.editPass2);
        aceptar = (Button) findViewById(R.id.btAceptar);
        db = new DBAccess(this);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

    }


    private void validar(){
        Boolean validado = false;
        if (val_nombre() && val_apellidos() && val_email() && val_usuario() && val_password())
        {
            //todo correcto y lo guardamos en la base de datos
            db.insert(nombre.getText().toString(),apellidos.getText().toString(), email.getText().toString(),usuario.getText().toString(),pass1.getText().toString());
            Toast.makeText(getApplicationContext(), "Se ha guardado el usuario.", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Debe rellenar correctamente los campos.", Toast.LENGTH_LONG).show();
        }

    }
    private Boolean val_nombre(){
        if (nombre.getText().toString().equals("")){
            nombre.setTextColor(Color.RED);
            nombre.setText("No puede estar vacio");
            return false;
        }
        else{
            nombre.setTextColor(Color.GREEN);
            return true;
        }

    }
    private Boolean val_apellidos(){
        if (apellidos.getText().toString().equals("")){
            apellidos.setTextColor(Color.RED);
            apellidos.setText("No puede estar vacio");
            return false;
        }
        else{
                apellidos.setTextColor(Color.GREEN);
                return true;
            }

    }
    private Boolean val_email(){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email.getText().toString());

        if (email.getText().toString().equals("")){
            email.setTextColor(Color.RED);
            email.setText("No puede estar vacio");
            return false;
        }
        if (!mather.find()){
            email.setTextColor(Color.RED);
            email.setText("correo no valido");
            return false;
        }
        else {
            if (db.existeUsuario(email.getText().toString())) {
                Toast.makeText(getApplicationContext(), "El correo ya existe en la BD", Toast.LENGTH_LONG).show();
                return false;
            } else {
                email.setTextColor(Color.GREEN);
                return true;
            }
        }
    }
    private Boolean val_usuario(){
        if (usuario.getText().toString().equals("")){
            usuario.setTextColor(Color.RED);
            usuario.setText("No puede estar vacio");
            return false;
        }
        else{
            usuario.setTextColor(Color.GREEN);
            return true;
        }
    }

    private Boolean val_password() {

        if (pass1.getText().toString().equals("") || pass2.getText().toString().equals("")) {
            pass1.setTextColor(Color.RED);
            pass1.setText("No puede estar vacio");
            return false;
        } else {
            if (pass1.getText().toString().equals(pass2.getText().toString()))
                return true;
            else{
                return false;
            }

        }
    }
}