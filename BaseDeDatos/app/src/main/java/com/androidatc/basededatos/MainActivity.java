package com.androidatc.basededatos;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnClickListener{

    private DatabaseAdapter adapter;

    private Button guardarUsuarios;
    private Button verUsuarios;
    private Button verPasswordUsuario;
    private Button borrarUsuario;
    private Button editarPassword;

    private EditText nombreUsuario;
    private EditText passwordUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. Inicializamos el adaptador
        adapter = new DatabaseAdapter(this);

        //2. Que metodo fue llamado?
        //3. Porque no fue llamado onCreate ni onUpgrade?

        /*
         * 3. Recordar
         * SQLite es bastante eficiente ya que evita la creación de la base de datos a no ser que
         * alguien trate de acceder a la BD por 1ra vez. Al inicializar MiHelper no creamos una BD
         * para poder acceder.
         *
         *  Para poder acceder a la BD el cual representa el archivo de la base de datos
         *  fisica almacenado en nuestro dispositivo es necesario llamar al metodo getWritableDatabase().
         *  De esta manera se llevara a cabo el ciclo de vida del SQLOpenHelper
         */

        nombreUsuario = (EditText) findViewById(R.id.nombre);
        passwordUsuario = (EditText) findViewById(R.id.password);

        guardarUsuarios = (Button) findViewById(R.id.guardar);
        guardarUsuarios.setOnClickListener(this);

        verUsuarios = (Button) findViewById(R.id.ver);
        verUsuarios.setOnClickListener(this);

        verPasswordUsuario = (Button) findViewById(R.id.verPassword);
        verPasswordUsuario.setOnClickListener(this);

        borrarUsuario = (Button) findViewById(R.id.borrarUsuario);
        borrarUsuario.setOnClickListener(this);

        editarPassword = (Button) findViewById(R.id.editarPassword);
        editarPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guardar:
                guardarUsuario();
                break;
            case R.id.ver:
                verUsuariosBd();
                break;
            case R.id.verPassword:
                verContraseñaUsuario();
                break;
            case R.id.borrarUsuario:
                borrarUsuarioBd();
                break;
            case R.id.editarPassword:
                editarPasswordUsuarioBd();
                break;
        }
    }

    private void guardarUsuario() {
        //4. Obtenemos los datos del usuario
        String nombre = nombreUsuario.getText().toString();
        String password = passwordUsuario.getText().toString();

        //5. llamamos al metodo insert del adaptador para insertar en la BD
        long id = adapter.insertUser(nombre, password);

        if(id < 0){
            Toast.makeText(this, "La carga no pudo ser realizada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "El usuario fue ingresado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }

    private void verUsuariosBd() {
        //6. Llamamos al metodo para ver los usuarios cargados
        String usuarios = adapter.getUsers();

        Toast.makeText(this, usuarios, Toast.LENGTH_SHORT).show();
    }

    private void verContraseñaUsuario(){
        //7. Llamamos al metodo para ver detalle de un usuario especifico
        String passwordUsuario = adapter.getUserPassword(nombreUsuario.getText().toString());

        Toast.makeText(this, passwordUsuario, Toast.LENGTH_SHORT).show();
    }

    private void borrarUsuarioBd(){
        //7. Llamamos al metodo para ver detalle de un usuario especifico
        int resultado = adapter.deleteUser(nombreUsuario.getText().toString());

        if(resultado > 0){
            Toast.makeText(this, "Usuario borrado con exito (" + resultado + ")", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Error al borrar usuario", Toast.LENGTH_SHORT).show();
        }

    }

    private void editarPasswordUsuarioBd(){
        //7. Llamamos al metodo para ver detalle de un usuario especifico
        int resultado = adapter.updateUserPassword(nombreUsuario.getText().toString(), passwordUsuario.getText().toString());

        if(resultado > 0){
            Toast.makeText(this, "Contraseña cambiada con exito", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Error al cambiar contraseña", Toast.LENGTH_SHORT).show();
        }

    }
}
