package com.example.gestionActores;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Utilidades.Entidad.Actor;
import com.example.gestionActores.db.Actores;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nombre;
    EditText password;
    TextView texto_nombre;
    TextView texto_pass;
    Button acceder;
    Button registro;
    DBAccess db;
    Actores dbActor;
    ConstraintLayout fondo;

        //cargamos el menu de preferencias
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_pref, menu);
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

        nombre = (EditText) findViewById(R.id.editNombreUsuario);
        password = (EditText) findViewById(R.id.editPassword);
        texto_nombre = (TextView) findViewById(R.id.txtNombre);
        texto_pass = (TextView) findViewById(R.id.txtPass);
        acceder = (Button) findViewById(R.id.btAcceder);
        registro = (Button) findViewById(R.id.btRegistro);
        db = new DBAccess(this);
        fondo = (ConstraintLayout) findViewById(R.id.fondo);

        cargarPreferencias();
        //inicializamos la biblioteca de los actores
        SugarContext.init(this);

        //Se usa el método findById para hacer consultas a la BD
        dbActor=Actores.findById(Actores.class,1);

        //si no hay nada rellenamos con datos iniciales
        if (dbActor.count(Actores.class) == 0)
        {
            agregarActores();
        }
        else {

            long numero = dbActor.count(Actores.class);
            int mostrar = (int)numero;
            Toast.makeText(getApplicationContext(), "numero de registros "+mostrar, Toast.LENGTH_LONG).show();
        }
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
    private void agregarActores(){

            dbActor = new Actores(R.drawable.sheldon,"Sheldon Cooper","Fue un niño superdotado proveniente del este de Texas. " +
                    "Pasó del quinto año de primaria a la universidad, tiene dos doctorados y una Maestría. Suele ir vestido con un suéter y encima una camiseta " +
                    "con logotipos de superhéroes. Es un fanático empedernido de Star Trek, en especial de Spock, haciendo creer a sus amigos que su aguda audición se " +
                    "debe a su oído vulcaniano. Su superhéroe favorito es Flash. En cuanto a su familia, su padre murió cuando era adolescente y su madre es algo conservadora pero de buen carácter aunque a Sheldon no le gusta como es ella (aunque Leonard quisiera tenerla por madre). " +
                    "Tiene un hermano mayor y una hermana melliza. Comparte aspectos de la personalidad con la madre de Leonard lo que ocasiona una muy buena relación entre ellos y, debido a las diferencias entre Sheldon y su madre, puede llegar a sentirse avergonzado de ella; lo mismo le sucede a Leonard, " +
                    "por lo que se asume que nacieron con \"las madres equivocadas\". Debido a su cociente intelectual, su objetivo en la vida es estudiar a exactitud y llegar a resolver la teoría de cuerdas a fin de obtener un Premio Nobel de Física (aunque en la séptima temporada, decide cambiar de campo de estudios)."
                    , "Principal");
        long save = dbActor.save();
        Log.d("DB", "Long: " + save);

        dbActor =new Actores(R.drawable.amy,"Amy Farrah Fowler","Es la versión femenina de Sheldon. Debido a su trabajo, realiza experimentos con animales y, a veces, con Sheldon." +
                "Se caracteriza por vestirse con ropas de apariencia de una persona mayor, con gafas. Tiene ojos celestes, y el pelo castaño lacio. Su primera aparición fue en el episodio \"The Lunar Excitation\" ya " +
                "que ingresa en la serie en el último capítulo de la tercera temporada. Su objetivo en la vida es ser amada y correspondida por un hombre y cree que ese alguien es Sheldon, con quien tiene su primera cita, " +
                "su primer beso y su primera vez como pareja. Como objetivo de la educación, Amy resulta ser muy anticuada y algo perturbante en sus conversaciones, teniendo algunas tendencias homosexuales al tratarse de Penny, debido a que ella la considera su \"amiguis\"." +
                "Es algo rara pero similar a Bernadette. Al lado de Penny, tiene varias experiencias divertidas. Amy se tiene que mudar con Sheldon debido a una inundación en su casa, aunque tras haber reparado los daños de esta, Amy sigue viviendo con él pues se siente cómoda estando con Sheldon, aunque lo mantiene en secreto. Al enterarse de esto, Sheldon lo toma bien y deciden vivir juntos." +
                "Del grupo de amigos, es la más inapropiada de todos. Es la tercera mujer del grupo, después de Penny y Bernadette. Aunque tiene muchas discrepancias y coincidencias con Bernadette, Amy tiene una buena relación de amistad con Howard, amistad que se ve reforzada al trabajar en un proyecto juntos. Para Amy, su mejor amiga o \"amiguis\" es Penny.", "Principal");

        save = dbActor.save();
        Log.d("DB", "Long: " + save);

        dbActor = new Actores(R.drawable.leonard,"Leonard Hofstadter","Es proveniente de New Jersey. Obtuvo su doctorado a los 24 años de edad en la Universidad de Princeton." +
                "Generalmente se lo ve usando una chaqueta o campera junto a una remera de mangas cortas y un pantalón, por no olvidarse de sus distintivas gafas (que él mismo considera \"lentes de ñoño\"). " +
                "Le apasionan los cómics y los videojuegos. Además es intolerante a la lactosa y padece miopía. Su cociente intelectual es de 173. En cuanto a su familia, sus padres son científicos divorciados, lo que crea un odio constantemente demostrado de la madre de Leonard hacia su padre. Su madre es totalmente estricta debido a su carrera de psiquiatra con especialidad en neurociencia, lo que hace que Leonard se sienta incómodo (aunque para Sheldon, es la mejor madre del mundo a diferencia de la suya). Su hermano menor Michael es catedrático de Derecho en Harvard y su hermana acaba de conseguir hacer crecer un páncreas humano en un gibón adolescente " +
                "(tal y como relata su propia madre en el 15° episodio de la segunda temporada), lo que hace a Howard cuestionarse sobre sus planes de vida. Su objetivo en la vida es enamorar a su vecina Penny, de la cual recibe ambiguas respuestas hasta que finalmente, para sorpresa de sus amigos, inician una relación de noviazgo en la tercera temporada durante meses, hasta que se separan, pero siguen siendo amigos. Casualmente retoman su relación a finales de la quinta temporada para seguir juntos en adelante; se comprometen en la séptima y se casan a principios de la novena temporada." +
                "Como objetivo de la educación, su madre lo somete a muchas pruebas de experimento social pero a él nunca le gustó eso por lo que siempre fue experimental y radical en sus vivencias y con sus amigos. Es más sociable que sus amigos. Es sincero, detallista y práctico. Al igual que Rajesh y Howard, nunca ha tenido éxito con las mujeres (por ser tímido y nerd), aunque él quiere conocer a otras personas, pero ha tenido brevemente lo que considera novias estables, entre ellas la némesis de Sheldon: Leslie Winkle y se cuentan también a Stephanie Barnett (una doctora que conoció por Howard), Priya Kootrapalli (hermana de Rajesh) y a Joyce Kim (una infiltrada espía norcoreana)." +
                "Debido a que Penny fue su primera novia, adquiere cierto nivel de experiencia con las mujeres por sobre sus amigos.", "Principal");
        save = dbActor.save();
        Log.d("DB", "Long: " + save);

        dbActor = new Actores(R.drawable.penny,"Penny","Es proveniente de Nebraska. Es camarera en el restaurante \"The Cheesecake Factory\", hasta " +
                "que deja ese trabajo en la séptima temporada para empezar a ser actriz, aunque no tiene mucha suerte al principio. A partir de la novena temporada empieza a trabajar " +
                "en una empresa farmacéutica. Suele usar todo tipo de vestimenta. Algunas veces vestidos atrevidos de gala y otras con ropa simple pero moderna. Es una chica rubia, joven, guapa y algo alocada, viviendo el sueño americano con metas de ser actriz." +
                "Su familia tiene un problema disfuncional. Su hermano mayor estuvo en la cárcel por sus relaciones con el narcotráfico. A su padre le agrada Leonard porque no es un perdedor como los demás novios de Penny. Curiosamente, Penny es el único personaje de toda la serie del cual se desconoce su apellido." +
                "Debido a su actitud libertina, su objetivo en la vida es ser una gran actriz de Hollywood, pero luego desiste al conocer mejor a su amigo y novio, Leonard. Como objetivo de la educación, sus padres la educaron de manera convencional lo que la hace muy distinta de sus amigos siendo la única que tuvo una buena juventud." +
                "Es alegre, divertida y liberal pero muy insegura de sí misma. Sus decisiones la han llevado a cometer varios errores. Por su manera de llevarse con sus amigos, se vuelve el amor platónico de Leonard. Además, Penny genera un gran cambio sobre todos sus amigos hacia un buen rumbo social. Sus relaciones sentimentales siempre han sido malas, ya que, como ella las describe: \"O solo salgo con idiotas insensibles, o eran geniales y yo los convertí en idiotas insensibles\", lo que la hace ver como alguien experta en ese tema.", "Principal");

        save = dbActor.save();
        Log.d("DB", "Long: " + save);

        dbActor = new Actores(R.drawable.howard,"Howard Wolowitz","Es judío con apariencia anticuada para su época. Es el único de los cuatro amigos que no tiene un doctorado (solo tiene un máster), " +
                "lo cual desata las burlas de Sheldon. Suele vestirse con ropa ajustada, psicodélica y algo retro. Su peinado no es nada alejado de su vestimenta. Es alérgico a los cacahuetes (maníes). En cuanto a su familia, su padre lo dejó cuando era adolescente. Su madre tiene una actitud abusiva, molesta, irritante e incómoda, lo que hace que Howard se sienta avergonzado de ella en todo momento. Su madre muere en la temporada 8 y conoce a su medio hermano que resulta ser oceanógrafo." +
                "Sus situaciones llevan a que su objetivo en la vida sea tener una buena esposa y una buena familia; objetivo conseguido con su novia y después esposa, Bernadette. Como objetivo de la educación, su padre era bueno con él pero debido a su distanciamiento, careció de una buena figura paterna, mientras que su madre hace que él sea un sujeto inestable socialmente." +
                "Es muy presumido pero con malos resultados. Cree que todas las mujeres se sienten atraídas por él, pero la realidad no puede ser más distinta. Es un poco pervertido. Debido a sus presunciones, sus amigos llegan a avergonzarlo fácilmente.Sus relaciones amorosas suelen terminar en su desastre o incomodidad por parte de las mujeres. Muchas de ellas terminan con él porque tiene una actitud repulsiva. Estuvo saliendo con una compañera de Penny, Bernadette, con quien parecía que lo único que tenían en común era el odio hacia sus respectivas madres. Esta relación terminó al final de la tercera temporada, aunque se reanudó al darse otra oportunidad al principio de la cuarta temporada. " +
                "Al final de la cuarta temporada se comprometen, y finalmente se casan en el último episodio de la quinta temporada. Después de su boda se lo vio (para impresionar a su futuro suegro) yendo al espacio y en los primeros episodios de la sexta temporada se lo ve como asistente de carga en la Estación Espacial. En la 9 temporada se descubre que va a ser padre y en la temporada 11 ya tiene una niña y un niño.", "Principal");

        save = dbActor.save();
        Log.d("DB", "Long: " + save);

        dbActor = new Actores(R.drawable.bernadette,"Bernadette Rostenkowski","Tiene un doctorado. Se caracteriza por usar gafas y tener vestidos casuales pero similares. Es rubia, guapa y de baja estatura. " +
                "Cómicamente, su voz es muy aguda. Su objetivo en la vida es ser una mujer independiente a través de su trabajo, lo cual hace al obtener su doctorado trabajando en una farmacéutica. Como objetivo de la educación, su padre era sobreprotector por ser su hija predilecta. Esto hace que Bernadette sea amable y considerada con todos. Es el único personaje principal de religión católica." +
                "Es dulce, amable, sincera, tierna y amorosa con todos. Al conocer a su suegra, Bernadette deja de ser dulce y tiene ciertos lapsos de desquicio y alto estrés, lo que incluso supera e intimida a cualquier persona, amigo o no.A diferencia de Howard, Bernadette tuvo algunos novios, algunos más altos y más preparados que Howard, lo que hace que este se sienta intimidado por los exnovios de su esposa. Conoció a Howard debido a un pacto que este acordó con Leonard (si uno conseguía novia, ella le debía presentar una amiga al que no tenía novia). Durante un lapso fue la novia de Howard, pero en el episodio \"The Plimpton Stimulation\" él confiesa que terminaron. No obstante, " +
                "en el cuarto episodio de la cuarta temporada \"The Hot Troll Deviation\" deciden darse otra oportunidad. Tras descubrirse por fin el motivo por el que habían cortado: una \"infidelidad\" de Howard a través de un juego en línea, al mantener sexo virtual con otro jugador (conserje del Instituto de Tecnología de California), Howard se disculpa con ayuda de Penny y Bernadette le perdona, con lo cual vuelven a ser pareja. En el capítulo veinte de la cuarta temporada, todos asumen que ellos tienen problemas en la relación, pero finalmente ella acepta la propuesta de matrimonio de Howard y se casan justo antes de que Howard fuera al espacio.", "Principal");

        save = dbActor.save();
        Log.d("DB", "Long: " + save);

        dbActor = new Actores(R.drawable.raj,"Raj Koothrappali","Es hindú (este personaje representa el estereotipo de un hindú que reside en Estados Unidos). " +
                "Es graduado de Harvard. Suele usar prendas modernas aunque su tez morena resalta su origen. En cuanto a su familia, proviene de una familia adinerada. Su padre es ginecólogo " +
                "y su hermana Priya es abogada. Su objetivo en la vida es poder tener una novia sin que lo cuestione por su actitud. Cuando Raj deja de ser tímido, llega a salir con muchas mujeres pero siente fracasar por ser muy romántico o cursi.Como objetivo de la educación, sus padres lo educan en torno a las costumbres hindúes pero esto le desagrada mucho y como consecuencia padece un raro trastorno (mutismo selectivo) que le impide hablar con las mujeres, a excepción de su madre y su hermana, o con hombres en apariencia afeminados; solo se comunica con ellas si está ebrio (o cree estarlo). Como es amigo de Howard, sus padres creen que Raj es homosexual y que Howard es su pareja." +
                "Es igual de presumido que Howard aunque suele verse más desesperado. Debido a su timidez extrema con las mujeres, presume de ser un galán pero, al igual que Howard, termina fácilmente ridiculizado." +
                "En cuanto a sus relaciones amorosas, no tiene un buen inicio. Su primera novia fue Lucy, quien era una chica similar a él pero debido a su trastorno social de ansiedad, termina con él. A raíz de esto, Raj se siente rechazado por todas las mujeres pero, de repente, conoce a Emily Sweeney, quien termina siendo la primera mujer con quien tiene relaciones sexuales durante una relación sentimental y, al mismo tiempo, su primera novia oficial. De los cuatro amigos, es el que mayor éxito tiene con las mujeres a tal punto que Penny, Bernadette y Amy reconocen que es romántico, detallista y muy amable cuando se habla de amor.", "Principal");
        save = dbActor.save();
        Log.d("DB", "Long: " + save);

        dbActor = new Actores(R.drawable.stuart,"Stuart Bloom","Tiene estudios en una escuela de Arte. Es medio nerd, fanático de los cómics y muy buen dibujante. Es el dependiente de la tienda de cómics." +
                "Su objetivo en la vida es poder ser admitido en un círculo social, ya que tiene mala suerte para hacer amigos o tener una novia.Como objetivo de la educación, Stuart es despreocupado en su vida personal.Tiene una actitud combinada de Howard y Raj. Puede ser amable pero tiene cierto temor al momento de hacer las cosas.El hecho de dibujar tan bien le sirvió para invitar a salir por primera vez a Penny (ella había ido a la tienda de cómics y le regala su retrato a cambio de su número telefónico). Aconsejado por Leonard (quien se moría de celos y quiso aconsejarlo mal), Stuart la lleva a cenar y actúa muy lentamente. Pero la táctica funciona ya que terminan en el auto besándose. " +
                "En el mejor momento Stuart dice «¡Ohhh… Penny…!», y ella le contesta «¡Oh… Leonard…!> . Hasta ahí llega la relación entre ambos.Su mayor logro es trabajar para Howard como enfermero de su madre al final de la séptima temporada. Cuando su tienda se incendia, él se queda sin trabajo y sin hogar, hasta que Howard le ofrece trabajar como enfermero de su madre, diciéndole que tendrá dinero y hogar. A él le termina gustando el trabajo y se lleva bien con la madre de Howard.", "Secundario");

        save = dbActor.save();
        Log.d("DB", "Long: " + save);

        dbActor = new Actores(R.drawable.leslie,"Leslie Winkle (Sara Gilbert)","es una físico experimental que tiene sexo casual con Howard y Leonard. No se lleva bien con Sheldon y se burla frecuentemente de él. ", "Secundario");
        save = dbActor.save();
        Log.d("DB", "Long: " + save);

        dbActor = new Actores(R.drawable.emily,"Emily Sweenney","En la temporada 7 conoce a Raj y en la temporada 8 Raj la presenta como su novia. Desarrolla aversión hacia Penny por haber tenido «algo» con Koothrappali. " +
                "Rajesh deja la relación en episodio 15 de la temporada 9 The Valentino Submergence, debido a que conoció a otra chica que le trae dudas acerca de su relación con Emily, y su posterior ruptura un día antes de San Valentín.", "Secundario");

        save = dbActor.save();
        Log.d("DB", "Long: " + save);

        dbActor = new Actores(R.drawable.mary,"Mary Cooper","Es una cristiana devota de Texas. Tiene dos hijos además de Sheldon, incluyendo la hermana melliza de este, llamada Missy Cooper . Sheldon tiene una relación muy distante con ella, puesto que son tan " +
                "diferentes que no llegan a entenderse bien. No es intelectual ni superdotada pero es muy perspicaz y la única persona capaz de controlar a Sheldon cuando sus amigos no saben qué hacer y la llaman para que acuda a resolver la situación con su autoridad. Es muy buena, amable y cariñosa, " +
                "y se lleva realmente bien con Leonard. ", "Secundario");

        save = dbActor.save();
        Log.d("DB", "Long: " + save);

        dbActor = new Actores(R.drawable.madreleonard,"Beverly Hofstadter","la madre de Leonard. Es una psiquiatra y neurocientífica insensible y pedante. Mantiene una relación extremadamente distante con Leonard pero se lleva muy bien con Sheldon, " +
                "ya que comparten características de personalidad similares. Evalúa psicológicamente a todos constantemente, lo que desata vergüenza por parte de Leonard. Por ejemplo, le preguntó a Howard y a Koothrappali si ya habían \"aceptado\" sus latentes sentimientos homosexuales. " +
                "En su segunda aparición, Penny la emborracha, lo que causa que bese a Sheldon.", "Secundario");

        save = dbActor.save();
        Log.d("DB", "Long: " + save);

        Toast.makeText(getApplicationContext(), "Se ha cargado la base de datos", Toast.LENGTH_LONG).show();
    }

    //cargamos las preferencias
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void cargarPreferencias(){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String user = preferencias.getString("user","");
        String pass = preferencias.getString("pass","");

        nombre.setText(user);
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
                nombre.setTextColor(getResources().getColor(R.color.BLANCO));//blanco
                texto_nombre.setTextColor(getResources().getColor(R.color.BLANCO));
                texto_pass.setTextColor(getResources().getColor(R.color.BLANCO));
                break;
            case "VERDE":
                nombre.setTextColor(getResources().getColor(R.color.VERDE));//blanco
                texto_pass.setTextColor(getResources().getColor(R.color.VERDE));
                texto_nombre.setTextColor(getResources().getColor(R.color.VERDE));
                break;
            case "AZUL":
                nombre.setTextColor(getResources().getColor(R.color.AZUL));//blanco
                texto_pass.setTextColor(getResources().getColor(R.color.AZUL));
                texto_nombre.setTextColor(getResources().getColor(R.color.AZUL));
                break;
            case "AMARILLO":
                nombre.setTextColor(getResources().getColor(R.color.AMARILLO));//blanco
                texto_pass.setTextColor(getResources().getColor(R.color.AMARILLO));
                texto_nombre.setTextColor(getResources().getColor(R.color.AMARILLO));
                break;
            case "GRIS":
                nombre.setTextColor(getResources().getColor(R.color.GRIS));//blanco
                texto_pass.setTextColor(getResources().getColor(R.color.GRIS));
                texto_nombre.setTextColor(getResources().getColor(R.color.GRIS));
                break;
            case "ROJO":
                nombre.setTextColor(getResources().getColor(R.color.ROJO));//blanco
                texto_pass.setTextColor(getResources().getColor(R.color.ROJO));
                texto_nombre.setTextColor(getResources().getColor(R.color.ROJO));
                break;
        }
        nombre.setTextSize(Float.parseFloat(sizeTexto));
        texto_pass.setTextSize(Float.parseFloat(sizeTexto));
        texto_nombre.setTextSize(Float.parseFloat(sizeTexto));


    }

}