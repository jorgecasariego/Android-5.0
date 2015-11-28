package com.androidatc.basededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jorgecasariego on 30/7/15.
 */

public class DatabaseAdapter {

    //2. Creamos las variables necesarias para la base de datos
    private static final String DATABASE_NAME = "androidatc_database";
    private static final String TABLE_NAME = "ANDROID_TABLE";
    private static final int DATABASE_VERSION = 2;              //Cambiamos la version al agregar nueva columna

    //Nombre de las columnas de la tabla
    private static final String UID = "_uid";
    private static final String NOMBRE = "nombre";

    //13. Ingresamos una nueva columna a la tabla
    private static final String PASSWORD = "password";

    private Context context;
    private MyHelper myHelper;

    //3. Creamos el constructor de la clase
    public DatabaseAdapter(Context context){
        this.context = context;
        myHelper = new MyHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

        //10. Crear metodo open para crear la base de datos
        open();
    }

    private void open() {
        SQLiteDatabase db;
        try{
            db =  myHelper.getWritableDatabase();
        } catch(SQLiteException e){
            db = myHelper.getReadableDatabase();
        }

    }

    //11. Metodo para insertar usuario a la BD
    public long insertUser(String nombre, String password) {
        SQLiteDatabase db = myHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NOMBRE, nombre);
        contentValues.put(PASSWORD, password);

        long id = db.insert(TABLE_NAME, null, contentValues);

        return id;
    }

    //12. Metodo para leer usuarios de la BD
    public String getUsers(){
        SQLiteDatabase db = myHelper.getWritableDatabase();

        //1. Vamos a seleccionar el id y el nombre del usuario de la tabla ANDROID_TABLE
        String columnas[] = {UID, NOMBRE, PASSWORD};

        //2. Creamos un cursor para movernos a traves de las filas
        Cursor cursor = db.query(TABLE_NAME,
                                 columnas,
                                 null, null, null, null, null
                                 );

        //3. Creamos un buffer para guardar los resultados y devolver luego a la actividad
        StringBuffer buffer = new StringBuffer();

        //4. Vamos a movernos a traves de los datos retornados por el query
        while (cursor.moveToNext()){
            //5. Obtenemos el id de las columnas de esta manera
            int cid = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String password = cursor.getString(2);

            //6. Tambien se podría obtener de esta manera
            //   Esta manera es mejor ya que si cambiamos el orden de las columnas no afecta en nada
            //int cid = cursor.getInt(cursor.getColumnIndex(UID));

            //7. Guardamos el resultado para luego devolver el String al MainActivity
            buffer.append(cid + " " + nombre + " " + password + "\n");
        }

        //8. Retornamos el buffer al Activity que llamo
        return buffer.toString();
    }

   /* public void prueba(){
        String table_name = "course_activity";
        String columns[] = {"course_activity.course_id",
                             "course_activity.activity_id",
                             "courses.name",
                             "activities.name",
                             };

        //2. Creamos un cursor para movernos a traves de las filas
        Cursor cursor = db.query(table_name,
                columns,
                null, null, null, null, null
        );

        final String MY_QUERY = "SELECT "
                + "course_activity.course_id, "
                + "course_activity.activity_id, "
                + "courses.name, "
                + "activities.name "
                + "FROM "
                + "course_activity "
                + "INNER JOIN activities ON course_activity.activity_id = activities._id "
                + "INNER JOIN courses ON course_activity.course_id = courses._id "
                + "WHERE course_activity.course_id =?";

        String courseID = String.valueOf(course_id);

        Cursor cursor = database.rawQuery(MY_QUERY, new String[] {courseID});
    }*/

    //13. Este metodo nos devuelve la contraseña de un usuario
    public String getUserPassword(String nombreUsuario){
        //1. EL select que estaríamos simulando es el siguiente:
        //   select password from TABLE_NAME where NOMBRE = 'nombreUsuario'

        SQLiteDatabase db = myHelper.getWritableDatabase();

        String columnas[] = {PASSWORD};
        Cursor cursor = db.query(TABLE_NAME,
                                 columnas,
                                 NOMBRE + "= '" + nombreUsuario + "'",
                                 null, null, null, null);

        String password = "";

        //Suponiendo que el usuario es unico sino recorremos en un while
        if(cursor.moveToNext()){
            password = cursor.getString(cursor.getColumnIndex(PASSWORD));
        }

        return password;

    }

    //14. Este metodo borra el usuario si existe y retorna true en caso exitoso, false en caso de fallo
    public int deleteUser(String nombreUsuario){
        SQLiteDatabase db = myHelper.getWritableDatabase();

        //Retorna el numero de filas afectadas, 0 en otro caso
        int resultado = db.delete(TABLE_NAME, NOMBRE + " = '" + nombreUsuario + "'", null);

        return resultado;

    }

    //14. Este metodo borra el usuario si existe y retorna true en caso exitoso, false en caso de fallo
    public int updateUserPassword(String nombreUsuario, String newPassword){
        SQLiteDatabase db = myHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        //Este será el nuevo valor de la columna que cumple el "Where"
        cv.put(PASSWORD, newPassword);

        int resultado = db.update(TABLE_NAME, cv, NOMBRE + " = '" + nombreUsuario + "'",  null);

        //Retorna el numero de filas afectadas
        return resultado;

    }

    private static class MyHelper extends SQLiteOpenHelper{
        private Context _context;

        //4. Creamos el query para la creacion de la base de datos
        private static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME
                                + " ("
                                + UID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                + NOMBRE + " VARCHAR(255), "
                                + PASSWORD + " VARCHAR(255) "
                                + " );";


        //6. Creamos el query para eliminar la tabla si existe
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


        //1. Constructor de la clase
        //   Contexto, nombre de la base de datos, nombre del cursor, numero de versión
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);

            //8. Creamos para utitlizar en el Toast
            this._context = context;
            Toast.makeText(_context, "MyHelper llamado", Toast.LENGTH_SHORT).show();

            //9. Crear un adaptador en el MainActivity y ver que pasa

        }

        // Metodo llamado para la creacion de tablas
        // *****************************************************************
        // onCreate es ejecutado solo si la base de datos no existe.
        // Si onCreate retorna "Exitosamente" se asume que la BD fue creada
        // con el numero de versión pasado en el constructor de la clase
        // *****************************************************************
        @Override
        public void onCreate(SQLiteDatabase db) {

            //5. Ejecutamos el query para crear la base de datos
            db.execSQL(CREATE_TABLE);
            Toast.makeText(_context, "onCreate llamado", Toast.LENGTH_SHORT).show();
        }

        // Metodo llamado para la actualizacion de tablas
        // *****************************************************************
        // onUpgrade es solo llamado cuando la BD existe pero el numero de
        // version de la BD es menor que el solicitado por el constructor
        // *****************************************************************
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Toast.makeText(_context, "onUpgrade llamado", Toast.LENGTH_SHORT).show();

            //7. Ejecutamos el drop table y luego llamamos al onCreate para que nos cree una nueva tabla
            db.execSQL(DROP_TABLE);
            onCreate(db);

        }
    }
}
