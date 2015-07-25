package com.androidatc.dailyfortune.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * //1. Extender de Application
 *
 * Cuando arrancamos una aplicación Android, el sistema operativo crea un nuevo ‘hilo’ de
 * procesamiento para la aplicación. En ese hilo, llamado hilo principal, es donde se
 * ejecutan todas las operaciones del programa, incluidas las tareas relacionadas con la
 * actualización del interfaz de usuario. Es por ello, que cuando realizamos una operación
 * que consuma un tiempo apreciable, dejamos momentaneámente congelado el interfaz de usuario.
 *
 * Durante ese tiempo el interfaz no responderá a las acciones como pulsar botones, actualizaciones de
 * pantalla, etc. En suma, es como si se quedara temporalmente colgada la aplicación.
 *
 * Ejemplos de este tipo de operaciones pueden ser el acceso a ficheros de la tarjeta de memoria,
 * las consultas a través de internet o la inicialización de objetos complejos.
 *
 * Con el fin de que el interfaz de la aplicación atienda adecuadamente en todo momento a los
 * eventos generados en el interfaz de usuario, es conveniente que dichas operaciones se hagan
 * en otro hilo de procesamiento diferente del hilo principal
 *
 * Created by jorgecasariego on 24/7/15.
 */
public class AppController extends Application {
    private static final String TAG = "AppController";
    private static AppController mInstance = new AppController();
    //Este metodo debe tener la propiedad synchronized, ya que la instancia
    //sera accedida desde varios hilos por lo que es necesario evitar bloqueos
    //de acceso
    public static synchronized AppController getInstance() {
        return mInstance;
    }
    //2. El uso de Volley comienza con la creacion de una cola de peticiones
    //   La representacion logica de este elemento es la clase RequestQueue
    //   Este objeto se encarga de gestionar automaticamente el envio de las
    //   peticiones, la administracion de hilos, la creacion de cache y la
    //   publicacion de resultados en la UI
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        //3
        mInstance = this;
    }

    // 4. Este metodo obtiene la isntancia de la cola de peticiones que se usara a
    // a traves de toda la aplicación
    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    // 5. Para agregar una nueva peticion debemos acceder al metodo addToRequestQueue
    // una vez la isntancia esté creada
    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

    //6. Cancelamos todas las peticiones con la etiqueta tag
    public void cancelPendingRequest(Object tag){
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }


}
