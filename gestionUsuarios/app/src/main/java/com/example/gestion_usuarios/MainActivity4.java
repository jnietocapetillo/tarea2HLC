package com.example.gestion_usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    ListView list;
    ArrayList<String> lista;
    ArrayAdapter adaptador;
    DBAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        db = new DBAccess(this);
        list = (ListView) findViewById(R.id.listado);
        lista = db.listadoUsuarios();

        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);

        list.setAdapter(adaptador);

    }
}