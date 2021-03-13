package com.example.gestionActores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DBAccess extends SQLiteOpenHelper {
    //declaramos la estructura de la base de datos usuarios

    //Database name
    private static final String DB_NAME = "db_gestUsu";

    //Table name
    private static final String DB_TABLE_NAME = "db_usuarios";

    //Database version
    private static final int DB_VERSION = 1;

    //Columns
    private static final String Nombre_COLUMN = "nombre";

    private static final String Apellidos_COLUMN = "apellidos";

    private static final String Email_COLUMN = "email";

    private static final String Usuario_COLUMN = "usuario";

    private static final String Pasword_COLUMN = "password";

    //Application Context
    private Context mContext;

    //Con el constructor, si no existe la base de datos la crea sino se conecta.
    //En el caso de que se hiciese una actualización y se cambiase la versión,
    // el constructor llamaría al método onUpgrade para actualizar los cambios de la base de datos.
    public DBAccess(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " +DB_TABLE_NAME+ "("
                +Nombre_COLUMN+ " TEXT,"+Apellidos_COLUMN+" TEXT,"+Email_COLUMN+" TEXT,"+Usuario_COLUMN+" TEXT,"+Pasword_COLUMN+" TEXT)";

        //Lanzamos la consulta con execSQL
        db.execSQL(CREATE_USER_TABLE);

        // Los mensajes LOG sirven para que el programación, durante el desarrollo pueda recibir mensajes
        // tecnicos sobre el funcionamiento del programa sin que el usuario las pueda ver.
        // Estos mensajes aparecen en la pestaña Logcat de Android studio.
        Log("Tablas creadas");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void Log(String msg){
        Log.d("DB", msg);
    }
    public void toast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    //metodo para insertar una nueva fila
    public void insert(String nombre, String apellidos, String email, String usuario, String password){

        //Pedimos acceso de escritura en la base de datos.
        SQLiteDatabase db = this.getWritableDatabase();


        // Contenedor clave,valor -> columna, valor de entrada registro
        ContentValues values = new ContentValues();

        values.put(Nombre_COLUMN, nombre);
        values.put(Apellidos_COLUMN, apellidos);
        values.put(Email_COLUMN, email);
        values.put(Usuario_COLUMN,usuario);
        values.put(Pasword_COLUMN, password);

        //Insertamos a través del método insert, cuyos parametro son:
        //nombre de la tabla
        //nullColumnHack permite indicar si hay una columna cuyo valor pueda ser nulo.
        //valores asociados a la inserción.
        if(db.insert(DB_TABLE_NAME,null,values) != -1){
            toast("Registro insertado");
        }else{
            toast("Error: Registro no insertado!!!");
        }

        //Se cierra la conexión de la base de datos
        db.close();

    }
    public ArrayList listadoUsuarios(){
        ArrayList<String> lista = new ArrayList<>();

        //Pedimos acceso de lectura de la BD.
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols =new String[]{ "nombre","apellidos","email","usuario"};

        Cursor cursor = db.query(DB_TABLE_NAME,cols,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                lista.add("    "+cursor.getString(0)+"        "+cursor.getString(1)+"      "+cursor.getString(2)+"      "+cursor.getString(3));
            }while(cursor.moveToNext());
        }
        return lista;
    }

    //comprobar si existe el usuario
    public Boolean existeUsuario(String email){
        Boolean existe = false;

        //Pedimos acceso de lectura de la BD.
        SQLiteDatabase db = this.getReadableDatabase();

        String seleccion = "email LIKE ?";
        String[] cols =new String[]{ "usuario"};
        String[] args = new String[]{email};
        Cursor cursor = db.query(DB_TABLE_NAME,cols,seleccion,args,null,null,null);

        if(cursor.moveToFirst()){
            if (cursor.getColumnCount()==0)
                existe = false;
            else
                existe = true;

        }

        //Cerramos el cursor
        if(cursor != null) {
            cursor.close();
        }

        //Cerramos la base de datos.
        db.close();
        return existe;
    }

    //recuperar datos en la BD.
    public String[] getUsuario(String email){

        String result[]= new String[]{"","",""};

        //Pedimos acceso de lectura de la BD.
        SQLiteDatabase db = this.getReadableDatabase();

        //Realizamos la consulta a través del método 'query', cuyo significado de los
        // parámetros tenemos en los apuntes. Este método devuelve un cursor que nos
        // permite recorrer las tuplas del resultado.

        String[] cols = new String[]{ "nombre","apellidos","usuario" };
        String selection = "email LIKE ?"; //-> el caracter interrogación será sustituido por los valores del array 'args' en orden de aparición
        String[] args = new String[]{email};

        //guardamos la consulta en un cursor
        Cursor cursor = db.query(DB_TABLE_NAME,cols,selection,args,null,null,null);

        //Movemos el iterador al primer elemento (si existe devuelve true sino false)
        if(cursor.moveToFirst()) {
            //Cogemos el valor referente a la posicion de la columna
            result[0] = cursor.getString(0);
            result[1] = cursor.getString(1);
            result[2] = cursor.getString(2);
        }
        //Cerramos el cursor
        if(cursor != null) {
            cursor.close();
        }

        //Cerramos la base de datos.
        db.close();

        return result;

    }

    //consulta usuario. Devuelve true en caso de que exista
    public Boolean buscaUsuario(String usuario, String password){
        Boolean result = false;
        Boolean existe = false;

        //Pedimos acceso de lectura de la BD. y nos traemos el usuario y password de los registros
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = new String[]{"usuario","password"} ;
        String seleccion = "usuario LIKE ? AND password LIKE ?";
        String args[] = new String[]{usuario,password};
        Cursor cursor = db.query(DB_TABLE_NAME,cols,seleccion,args,null, null, null);

        if(cursor.moveToFirst()){

            if(cursor.moveToFirst()){
                if (cursor.getColumnCount()==0)
                    existe = false;
                else
                    existe = true;

            }

        }
        //Cerramos el cursor
        if(cursor != null) {
            cursor.close();
        }

        //Cerramos la base de datos.
        db.close();

        return existe;
    }

    //funcion que elimina el usuario pasado por parámetros
    public Boolean delUsuario(String email){
        Boolean result = false;
        String where = "email=?";
        String[] args = new String[]{email};
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(DB_TABLE_NAME,where,args);
            result = true;

        return result;
    }
}
