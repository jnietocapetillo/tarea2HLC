package com.example.gestion_usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity3 extends AppCompatActivity {

    ImageView listar;
    ImageView agregar;
    ImageView eliminar;
    ImageView buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listar = (ImageView) findViewById(R.id.imgListar);
        agregar = (ImageView) findViewById(R.id.imgAgregar);
        eliminar = (ImageView) findViewById(R.id.imgEliminar);
        buscar = (ImageView) findViewById(R.id.imgBuscar);

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),MainActivity4.class);
                startActivity(i);
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(i);
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),MainActivity5.class);
                startActivity(i);
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),MainActivity6.class);
                startActivity(i);
            }
        });
    }
}