package com.example.gestionActores;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestionActores.db.Actores;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

import Utilidades.Adaptador.RecyclerViewAdapter;
import Utilidades.Entidad.Actor;

public class MainActivity3 extends AppCompatActivity {

    private RecyclerView  rcyView;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager manager;
    ImageView imagen;
    DBAccess db;
    Actores dbActor;



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //recogemos el click de la opcion del menu en la barra de aplicaciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();

        // if (id == R.menu.menu) {
        startActivity(new Intent(this, MainActivity5.class));
        //    return true;
        //}
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // poner actores de the big bang theory
        imagen = (ImageView) findViewById(R.id.imgLista);
        imagen.setImageResource(R.drawable.imagen_lista);
        db = new DBAccess(this);
        //inicializamos la biblioteca de los actores
        SugarContext.init(this);

        //Se usa el método findById para hacer consultas a la BD
        dbActor=Actores.findById(Actores.class,1);



        //debemos rellenar el arraylist desde nuestro SugarORM


        //averiguamos cuantos registros hay en la base de datos
        int registros = (int) dbActor.count(Actores.class);

        //cargamos la lista del reciclerview desde la base de datos

        final List<Actores> listaActores = dbActor.listAll(Actores.class);

        rcyView = (RecyclerView) findViewById(R.id.recyView);
        manager = new LinearLayoutManager(this);
        rcyView.setLayoutManager(manager);

        adaptador = new RecyclerViewAdapter(this, listaActores);

        ((RecyclerViewAdapter) adaptador).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity4.class);
                i.putExtra("nombre", listaActores.get(rcyView.getChildAdapterPosition(v)).getNombre());
                i.putExtra("descripcion", listaActores.get(rcyView.getChildAdapterPosition(v)).getDescripcion());
                i.putExtra("tipo", listaActores.get(rcyView.getChildAdapterPosition(v)).getTipo());
                i.putExtra("imagen", listaActores.get(rcyView.getChildAdapterPosition(v)).getImg());
                startActivity(i);

            }
        });
        rcyView.setAdapter(adaptador);

    }

}
