package com.example.gestionActores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionActores.db.Actores;
import com.orm.SugarContext;

import Utilidades.Entidad.Actor;

public class MainActivity5 extends AppCompatActivity {

    EditText nombre;
    EditText tipo;
    EditText descripcion;
    Button aceptar;
    private Actores dbActor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        nombre = (EditText) findViewById(R.id.editTextNombre);
        tipo = (EditText) findViewById(R.id.editTextTipo);
        descripcion = (EditText) findViewById(R.id.editTextDesc);
        aceptar = (Button) findViewById(R.id.btAgregar);

        //inicializamos la biblioteca que gestionará los actores
        SugarContext.init(this);

        //Se usa el método findById para hacer consultas a la BD
        dbActor=Actores.findById(Actores.class,1);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarActor();
            }
        });

    }
    private void agregarActor(){
        if (!nombre.equals("") && !tipo.equals("") && !descripcion.equals("")){
            dbActor = new Actores(R.drawable.logo_registro, nombre.getText().toString(),tipo.getText().toString(),descripcion.getText().toString());

            long save = dbActor.save();
            Log.d("DB", "Long: " + save);

            Toast.makeText(this, "Actor añadido",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Debe rellenar todos los campos",Toast.LENGTH_SHORT).show();

    }
}