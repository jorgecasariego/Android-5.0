package com.androidatc.materialdesign.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.androidatc.materialdesign.R;
import com.androidatc.materialdesign.extras.SortListener;
import com.androidatc.materialdesign.fragments.FragmentBoxOffice;
import com.androidatc.materialdesign.fragments.FragmentDrawer;
import com.androidatc.materialdesign.fragments.FragmentSearch;
import com.androidatc.materialdesign.fragments.FragmentUpcomming;
import com.androidatc.materialdesign.fragments.MyFragment;
import com.androidatc.materialdesign.services.MyService;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import me.tatarka.support.job.JobInfo;
import me.tatarka.support.job.JobScheduler;


/**
 * Pasos para configurar ToolBar
 * 1. Usar Theme.AppCompat.Light.NoActionBar  para prevenir que el sistema muestre el action bar
 * 2. Definir app_bar.xml conteniendo un ToolBar
 * 3. Usar <include> en layout.xml para añadir el ToolBar
 * 4. Instanciar el toolbar usando findViewById dentro del Activity
 * 5. LLamar a setSupportActionBar y pasarle el objeto ToolBar
 * 6. Personalizar app:theme y app:popupTheme si es necesario
 * 7. Personzalizar las diferentes propiedades del ToolBar ya sea usando el objeto toolBar
 *    directamente o usando getSupportActionBar()
 */
public class MainActivity extends AppCompatActivity implements MaterialTabListener, OnClickListener{

    private static final int JOB_ID = 100;
    private Toolbar toolbar;
    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;
    //private SlidingTabLayout mTabs;
    private MaterialTabHost tabHost;

    public static final int MOVIES_SEARCH_RESULT = 0;
    public static final int MOVIES_HITS = 1;
    public static final int MOVIES_UPCOMING = 2;

    private static final String TAG_SORT_NAME = "sortName";
    private static final String TAG_SORT_DATE = "sortDate";
    private static final String TAG_SORT_RATINGS = "sortRatings";

    //Esta constante usamos para setear cada cuanto queremos que nuestro servicio busque datos nuevos
    private static final long POLL_FREQUENCY = 28800000;


    private JobScheduler mJobScheduler;

    private FragmentDrawer mDrawerFragment;

    private FloatingActionButton mFAB;
    private FloatingActionMenu mFABMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildFab();
        setupTabs();
        setupJob();
        setupDrawer();

    }

    private void buildFab() {
        // define the icon for the main floating action button
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.ic_action_new);


        mFAB = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setBackgroundDrawable(R.drawable.selector_button_red)
                .build();

        //define the icons for the sub action buttons
        ImageView iconSortName = new ImageView(this);
        iconSortName.setImageResource(R.drawable.ic_action_alphabets);
        ImageView iconSortDate = new ImageView(this);
        iconSortDate.setImageResource(R.drawable.ic_action_calendar);
        ImageView iconSortRatings = new ImageView(this);
        iconSortRatings.setImageResource(R.drawable.ic_action_important);

        //set the background for all the sub buttons
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_sub_button_gray));

        //build the sub buttons
        SubActionButton buttonSortName = itemBuilder.setContentView(iconSortName).build();
        SubActionButton buttonSortDate = itemBuilder.setContentView(iconSortDate).build();
        SubActionButton buttonSortRatings = itemBuilder.setContentView(iconSortRatings).build();

        buttonSortDate.setTag(TAG_SORT_DATE);
        buttonSortName.setTag(TAG_SORT_NAME);
        buttonSortRatings.setTag(TAG_SORT_RATINGS);

        buttonSortDate.setOnClickListener(this);
        buttonSortName.setOnClickListener(this);
        buttonSortRatings.setOnClickListener(this);

        //add the sub buttons to the main floating action button
        mFABMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonSortName)
                .addSubActionView(buttonSortDate)
                .addSubActionView(buttonSortRatings)
                .attachTo(mFAB)
                .build();
    }

    private void setupTabs() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);

        /*mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        mTabs.setDistributeEvenly(true);


        mTabs.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.accentColor));
        mTabs.setViewPager(mPager);*/



        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        //when the page changes in the ViewPager, update the Tabs accordingly
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);

            }
        });
        //Add all the Tabs to the TabHost
        for (int i = 0; i < mAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setIcon(mAdapter.getIcon(i))
                            .setTabListener(this));
        }



    }

    private void setupJob() {
        mJobScheduler = JobScheduler.getInstance(this);

        // EL problema que esta ocurriendo aqui es que estamos iniciando constructJob para poder
        // obtener todos los datos del servidor. Pero al mismo tiempo estamos obteniendo los datos
        // del servidor al llamar al fragment. Por lo que se va a estar llamando al mismo dato dos
        // veces. Y nosotros no queremos eso. Queremos que el fragment sea el encargado de cargar
        // los datos y luego inserte en la BD.
        // Para no hacer eso, creamos un Handler delayed que espere 30 segundos antes de llamar
        // al jobScheduler para que inicie el servicio
        // una vez que el fragment haya cargado todos los datos cada 10 minutos se van a cargar datos
        // nuevos el cual se inicia a partir de la llamada a constructJob
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                constructJob();
            }
        }, 30000);

    }

    private void setupDrawer() {
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //NavigationDrawerdFragment drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        //drawerFradgment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        //mDrawerFragment = (FragmentDrawer)
        //        getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        mDrawerFragment.setUp(
                R.id.fragment_navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),
                toolbar);


    }

    //Dependiendo de donde hizo click el usuario en el drawer vamos a cambiar la pantalla
    //A este metodo  se le llama desde el FragmentDrawer
    public void onDrawerItemClicked(int index) {
        mPager.setCurrentItem(index);
    }

    // A este metodo se le llama desde el FragmentDrawer cuando el drawer se esta deslizando
    //Nosotros vamos a utilizar este metodo para llamar al toggleTranslateFab para esconder el
    //boton mientras se desliza
    public void onDrawerSlide(float slideOffset) {
        toggleTranslateFAB(slideOffset);
    }

    private void toggleTranslateFAB(float slideOffset) {
        if (mFABMenu != null) {
            if (mFABMenu.isOpen()) {
                mFABMenu.close(true);
            }
            mFAB.setTranslationX(slideOffset * 200);
        }
    }

    private void constructJob(){
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, new ComponentName(this, MyService.class));

        builder.setPeriodic(POLL_FREQUENCY)
        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
        .setPersisted(true);

        mJobScheduler.schedule(builder.build());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.navigate) {
            startActivity(new Intent(this, SubActivity.class));
        }
        if (R.id.action_recycler_item_animations == id) {
            startActivity(new Intent(this, ActivityRecylerAnimators.class));
        }
        if (R.id.action_transition == id) {
            startActivity(new Intent(this, ActivityA.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
         mPager.setCurrentItem(materialTab.getPosition() );
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    //Este onClick es para el boton de favorito
    //Ordenamos la lista
    @Override
    public void onClick(View v) {
        //Obtenemos el fragment actual
        Fragment fragment = (Fragment) mAdapter.instantiateItem(mPager, mPager.getCurrentItem());

        if(fragment instanceof SortListener) {
            switch (v.getTag().toString()){
                case TAG_SORT_DATE:
                    ((SortListener) fragment).onSortByDate();
                    break;
                case TAG_SORT_NAME:
                    ((SortListener) fragment).onSortByName();

                    break;
                case TAG_SORT_RATINGS:
                    ((SortListener) fragment).onSortByRating();
                    break;
            }
        }

    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        int icons[] = {R.drawable.ic_action_home, R.drawable.ic_action_articles, R.drawable.ic_action_personal};
        String[] tabs;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            MyFragment myFragment = MyFragment.getInstance(position);

            return myFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //Esta es la manera de juntar una imagen con un texto
            //Drawable drawable = getResources().getDrawable(icons[position]);
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), icons[position]);
            drawable.setBounds(0,0,108,108);
            ImageSpan imageSpan = new ImageSpan(drawable);
            SpannableString spannableString = new SpannableString(" ");
            spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            return spannableString;
        }

        @Override
        public int getCount() {
            return 3 ;
        }
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter{

        int icons[] = { R.drawable.ic_action_home,
                        R.drawable.ic_action_articles,
                        R.drawable.ic_action_personal};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new Fragment();
            switch (position){
                case MOVIES_SEARCH_RESULT:
                    fragment = FragmentSearch.newInstance("", "");
                    break;
                case MOVIES_HITS:
                    fragment = FragmentBoxOffice.newInstance("", "");
                    break;
                case MOVIES_UPCOMING:
                    fragment = FragmentUpcomming.newInstance("", "");
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }

        public Drawable getIcon(int position) {
            return getResources().getDrawable(icons[position]);
        }
    }

    /*
    public static class MyFragment extends Fragment {
        private TextView textView;

        public static MyFragment getInstance(int position) {
            MyFragment myFragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myFragment.setArguments(args );

            return myFragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.my_fragment, container, false);

            textView = (TextView) layout.findViewById(R.id.position);

            Bundle bundle = getArguments();
            if(bundle != null){
                textView.setText("El numero de pagina es " + bundle.getInt("position"));
            }



            return layout;
        }
    }*/
}
