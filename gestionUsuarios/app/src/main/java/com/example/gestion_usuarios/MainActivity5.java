package com.example.gestion_usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {

    EditText correo;
    Button borrar;
    DBAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        correo = (EditText) findViewById(R.id.editNombreUsuario);
        borrar = (Button) findViewById(R.id.btEliminar);
        db = new DBAccess(this);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buscarCorreo;
                Boolean esta = false, ok = false;

                buscarCorreo = correo.getText().toString();
                esta = db.existeUsuario(buscarCorreo);

                if (esta){
                    if(db.delUsuario(buscarCorreo))
                        Toast.makeText(getApplicationContext(), "Usuario Eliminado.", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "No se ha podido eliminar.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}