package com.example.tarea4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.RED;
import static android.graphics.Color.rgb;

public class MainActivity extends AppCompatActivity {

    EditText usuario;
    EditText password;
    Button acceder;
    Button registrar;
    CheckBox recordar;
    TextView nom_usu;
    TextView text_pass;
    ConstraintLayout fondo;

    public static final String COLOR_TEXTO = "colorTexto";
    public static final String COLOR_FONDO = "colorFondo";
    public static final String SIZE_TEXT = "sizeText";

    public static String colorTexto;
    public static int colorFondo;
    public static int sizeText;

    //ponemos un menu en la barra superior de la actividad con la opcion de personaliar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //recogemos el click de la opcion del menu en la barra de aplicaciones
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();

       // if (id == R.menu.menu) {
            startActivity(new Intent(this, Preferencias.class));
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.editNombre);
        password = (EditText) findViewById(R.id.editPassword);
        acceder = (Button) findViewById(R.id.btAcceder);
        registrar = (Button) findViewById(R.id.btRegistro);
        recordar = (CheckBox) findViewById(R.id.chekRecordar);
        nom_usu = (TextView) findViewById(R.id.txtNombre);
        text_pass = (TextView) findViewById(R.id.txtPass);
        fondo = (ConstraintLayout) findViewById(R.id.fondo);




            //rescatamos lo que tengamos en preferencias
            cargarPreferencias();


        //damos click a acceder y llamamos funcion que nos valida los datos de usuario
        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean correcto= false;
                correcto = comprobarDatos();

                if (correcto){
                    Toast.makeText(getApplicationContext(), "Se ha validado correctamente", Toast.LENGTH_LONG).show();
                    //si el usuario ha marcado recordar guardamos el usuario en preferencias
                    if (recordar.isChecked()){
                        guardarPreferencias();
                    }
                    else borrarPreferencias(); //no esta marcada recordar, borramos las preferencias

                    
                }
                else
                    Toast.makeText(getApplicationContext(), "No se puede iniciar sesion", Toast.LENGTH_LONG).show();
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),main2_registro.class);
                startActivity(i);
            }
        });
    }

    //cargamos las preferencias
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void cargarPreferencias(){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String user = preferencias.getString("user","");
        String pass = preferencias.getString("pass","");

        usuario.setText(user);
        password.setText(pass);

        //cargamos del fichero de preferencias
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        //guardamos en variables las preferencias que tenemos
        String colorFondo = prefs.getString("colorFondo","BLANCO");
        String colorTexto = prefs.getString("colorTexto","GRIS");
        String sizeTexto = prefs.getString("sizeText","14");



        //ponemos el color del fondo

        switch (colorFondo){
            case "BLANCO":
                fondo.setBackgroundColor(getResources().getColor(R.color.BLANCO));
                break;
            case "VERDE":
                fondo.setBackgroundColor(getResources().getColor(R.color.VERDE));
                break;
            case "AZUL":
                fondo.setBackgroundColor(getResources().getColor(R.color.AZUL));
                break;
            case "AMARILLO":
                fondo.setBackgroundColor(getResources().getColor(R.color.AMARILLO));
                break;
            case "GRIS":
                fondo.setBackgroundColor(getResources().getColor(R.color.GRIS));
                break;
            case "ROJO":
                fondo.setBackgroundColor(getResources().getColor(R.color.ROJO));
                break;
        }
        switch (colorTexto){
            case "BLANCO":
                nom_usu.setTextColor(getResources().getColor(R.color.BLANCO));//blanco
                text_pass.setTextColor(getResources().getColor(R.color.BLANCO));
                usuario.setTextColor(getResources().getColor(R.color.BLANCO));
                break;
            case "VERDE":
                nom_usu.setTextColor(getResources().getColor(R.color.VERDE));//blanco
                text_pass.setTextColor(getResources().getColor(R.color.VERDE));
                usuario.setTextColor(getResources().getColor(R.color.VERDE));
                break;
            case "AZUL":
                nom_usu.setTextColor(getResources().getColor(R.color.AZUL));//blanco
                text_pass.setTextColor(getResources().getColor(R.color.AZUL));
                usuario.setTextColor(getResources().getColor(R.color.AZUL));
                break;
            case "AMARILLO":
                nom_usu.setTextColor(getResources().getColor(R.color.AMARILLO));//blanco
                text_pass.setTextColor(getResources().getColor(R.color.AMARILLO));
                usuario.setTextColor(getResources().getColor(R.color.AMARILLO));
                break;
            case "GRIS":
                nom_usu.setTextColor(getResources().getColor(R.color.GRIS));//blanco
                text_pass.setTextColor(getResources().getColor(R.color.GRIS));
                usuario.setTextColor(getResources().getColor(R.color.GRIS));
                break;
            case "ROJO":
                nom_usu.setTextColor(getResources().getColor(R.color.ROJO));//blanco
                text_pass.setTextColor(getResources().getColor(R.color.ROJO));
                usuario.setTextColor(getResources().getColor(R.color.ROJO));
                break;
        }
        nom_usu.setTextSize(Float.parseFloat(sizeTexto));
        text_pass.setTextSize(Float.parseFloat(sizeTexto));
        usuario.setTextSize(Float.parseFloat(sizeTexto));


    }

    //eliminamos de las preferencias al no estar chequeado recordar
    private void borrarPreferencias(){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String user = "";
        String pass = "";
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("user",user);
        editor.putString("pass",pass);
        editor.commit(); //guardamos la informacion
        usuario.setText(user);
        password.setText(pass);
    }

    //guardamos las preferencias de usuario al estar marcado el checbox
    private void guardarPreferencias(){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String user = usuario.getText().toString();
        String pass = password.getText().toString();
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("user",user);
        editor.putString("pass",pass);
        editor.commit(); //guardamos informacion
    }

    //funcion que controla que no están los campos vacíos
    private boolean comprobarDatos(){
        String nom = usuario.getText().toString();
        String pass = password.getText().toString();
        Boolean ok=true;

        if (nom.compareTo("") == 0) {
            Toast.makeText(getApplicationContext(), "El nombre no puede estar vacío", Toast.LENGTH_LONG).show();
            ok=false;
        }
        if (pass.compareTo("")==0){
            Toast.makeText(getApplicationContext(), "La password no puede estar vacía", Toast.LENGTH_LONG).show();
            ok=false;
        }

        return ok;
    }
}