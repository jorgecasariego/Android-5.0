package com.androidatc.materialdesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidatc.materialdesign.pojo.Information;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jorgecasariego on 6/7/15.
 *
 * Pasos para manejar los click en Recycler
 * 1. Create a class that extends RecyclerView.OnItemTouchListener
 *
 * 2. Create an interface inside that class that supports click and indicates the View that was
 *    clicked and the position where it was clicked
 *
 * 3. Create a GestureDetector to detect ACTION_UP single tap and long press event
 *
 * 4. Return true from the singleTap to indicate your GestureDetector has consume the event
 *
 * 5. Find the childView containing the coordinates specified by the MotionEvent and if the
 *    childView is not null and the listener is not null either, fire a long click event
 *
 * 6. Use the onInterceptTouchEvent of your RecyclerView to check if the childView is not null,
 *    the listener is not null and the gesture detector consumed the touch event
 *
 * 7. If above condition holds true, fire the event
 */
public class NavigationDrawerFragment extends Fragment{

    private RecyclerView recyclerView;
    public static final String PREF_FILE_NAME = "testPref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private InformationAdapter adapter;

    private boolean mUserLearnedDrawer;
    private boolean mFromSaveInstanceState;

    //Vista que usaremos para asociar con el NavigationDrawer
    private View containerView;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Obtenemos el valor booleano para saber si el usuario ya sabe que existe un navigationDrawer.
        //Si es verdadero entonces ya no hace abrir el naviagtionDrawer, si es falso le mostramos al usuario
        mUserLearnedDrawer = Boolean.valueOf(readToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));

        //Si saveInstanceState es distinto de null significa que vino de una ratacion de pantalla
        //En ese caso no queremos que muestre el navigationDrawer
        if(savedInstanceState != null){
            mFromSaveInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        //Asociamos el recyclerView con su ID
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new InformationAdapter(getActivity(), getData());

        //Tecnica 2 utilizada para el click
        //En este caso le decimos que el fragment es el encargado de implementar el onClick de la
        //lista del RecyclerView
        //adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /**
         * Tecnica 3 de manejos de clicks
         *
         * Esta es la manera de manejar los eventos de clicks y longClicks con un GestureDectector personalizado
         * afuera de nuestro Adaptador y dentro de nuestra actividad o fragment usando estos metodos
         */
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getActivity(), "onClick " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View v, int position) {
                Toast.makeText(getActivity(), "onLongClick " + position, Toast.LENGTH_SHORT).show();
            }
        }));

        return  layout;
    }

    //  cargamos el objeto Information con los datos que se van a mostrar en el Recycler View
    //  Este objeto luego lo usaremos en InformationAdapter al crear cada una de las celdas
    public static List<Information> getData(){
        List<Information> data = new ArrayList<Information>();
        int[] iconos = {R.drawable.ic_number1, R.drawable.ic_number2, R.drawable.ic_number3, R.drawable.ic_number4};
        String[] titulos = {"Titulo 1","Titulo 2","Titulo 3","Titulo 4"};

        for (int i=0; i<titulos.length && i< iconos.length; i++){
            Information j = new Information(iconos[i], titulos[i]);
            data.add(j);
        }

        return  data;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {

        //Asociamos el containerView al navigationDrawer que pasamos como parametro del metodo setUp()
        //luego este containerView usaremos en el metodo openDrawer para abrir el NavigationDrawer cuando pulsamos sobre el icono
        containerView = getActivity().findViewById(fragmentId);

        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                //SI el usuario nunca vio el drawer entonces esta es la primera vez que ve
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer +"");
                }
                //Con esto hacemos que el actionBar se dibuje de nuevo
                getActivity().invalidateOptionsMenu();
            }


            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //Con esto hacemos que el actionBar se dibuje de nuevo
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //Esto hacemos para que tenga un efecto de transparencia mientras abre el slide
                if(slideOffset < 0.6){
                    toolbar.setAlpha(1-slideOffset);
                }

            }
        };

        /**
         * La idea del NavigationDrawer es la siguiente: Si el usuario inicia por primera vez la aplicación
         * entonces le mostramos que existe un navigationDrawer. Si no no mostramos porque ya sabe que existe
         */
        if (!mUserLearnedDrawer && !mFromSaveInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }

        //El listener nos dice si el NavigationDrawer fue abierto o cerrado
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //Con esto hacemos que el navigationDrawe aparezca en la pantalla (icono hamburguesa)
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences(Context context, String preferencesName, String preferencesValues){
        SharedPreferences sp = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(preferencesName, preferencesValues);
        editor.apply();
    }

    public static String readToPreferences(Context context, String preferencesName, String defaultValue){
        SharedPreferences sp = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(preferencesName, defaultValue);
    }

    /**
     * **********************************************************************
     * 1. Create a class that extends RecyclerView.OnItemTouchListener
     * **********************************************************************
     *
     * Tecnica 3 de manejos de clicks
     *
     * Esta es la manera de manejar los eventos de clicks y longClicks con un GestureDectector personalizado
     * afuera de nuestro Adaptador y dentro de nuestra actividad o fragment usando estos metodos
     */
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
            Log.d("Material Design Ejemplo", "constructor RecyclerTouchListener invocado");
            this.clickListener = clickListener;

            /**
             * Lo que hacemos aqui es crear una instancia de gestureDetector para manejar el evento de click
             * con onSingleTapUp y longClick con el metodo onLongPress
             */
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                /**
                 *
                 * En ambos metodos usamos el objeto MotionEvent para obtener las coordenadas llamando a
                 * getX y getY para recuperar la vista hija que fue clickeada dentro del RecyclerView y así lanzar
                 * los eventos al objeto clickListener el cual implementa la interfaz personalizada el cual soporta los eventos
                 * de click y long click
                 */
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    Log.d("Material Design Ejemplo", "onSingleTapUp");
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());

                    if(child != null && clickListener != null){
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                    Log.d("Material Design Ejemplo", "onLongPress");

                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());

            if(child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child, rv.getChildPosition(child));
            }


            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.d("Material Design Ejemplo", "onTouchEvent: " + e);
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    /**
     *
     ****************************************************************************
     * 2. Create an interface inside that class that supports click and indicates
     *    the View that wasclicked and the position where it was clicked
     ****************************************************************************
     */
    public static interface ClickListener{
        public void onClick(View v, int position);
        public void onLongClick(View v, int position);
    }



    /**
     *  Utilizado para la tecnica 2 de click
     *
     @Override
    public void itemClicked(View v, int position) {
        startActivity(new Intent(getActivity(), SubActivity.class));
    }
     */
}
