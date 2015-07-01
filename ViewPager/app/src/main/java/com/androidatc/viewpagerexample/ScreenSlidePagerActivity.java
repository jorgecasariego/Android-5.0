package com.androidatc.viewpagerexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;


/**
 */
public class ScreenSlidePagerActivity extends FragmentActivity{
    /**
     * 1. El numero de paginas que mostrara este ejemplo
     */
    private static final int NUM_PAGES = 6;

    private String tabTitles[] = new String[] { "Pagina 1", "Pagina 2", "Pagina 3", "Pagina 4", "Pagina 5", "Pagina 6" };

    /**
     * 2. Page, el cual manejará animaciones y permitirá desplazamientos horizontales para
     * acceder a la pagina previa y la pagina siguiente
     */
    private ViewPager mPager;

    /**
     * 3. PagerAdapter el cual provee las paginas al viewPager
     */
    private PagerAdapter mPagerAdapter;

    public ScreenSlidePagerActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // 4. Instanciamos el ViewPager y el PagerAdapter
        mPager = (ViewPager) findViewById(R.id.pager);

        // 5. LLamamos a setPagerTransformer para aplicar una animación
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());

        // 7. Creamos un PagerAdapter del tipo ScreenSlidePagerAdapter
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        //8. A nuestro pager le pasamos el adaptador encargado de crear las vistas
        mPager.setAdapter(mPagerAdapter);

        //9. Vamos a darle a PagerSlidingTabStrip el ViewPager con el tab
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setViewPager(mPager);

        //10. Configuramos en los casos que haya cambios de tabs
        // Attach the page change listener to tab strip and **not** the view pager inside the activity
        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getApplicationContext(),
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        /*
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }
        });*/
    }

    /**
     * Manejamos el boton de ir hacia atras para irnos hacia abajo en la pila de fragments.
     * Si el usuario esta en el fragment inicial entonces vuelve atras en la pila de actividades
     */
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * 6. PagerAdapter simple que representa 5 objetos del tipo ScreenSlidePageFragment
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * 6.1 Por cada llamada a getItem creamos un fragment del tipo ScreenSlidePageFragment
         * que tiene como información en el Bundle el numero de pagina el cual luego será usado
         * como titulo
         */
        @Override
        public Fragment getItem(int position) {
            Fragment f = null;

            Log.e("Position", "position: " + position);
            if(position < 5) {
                //En este caso se crearan 5 fragments iguales con el titulo distinto
                f = ScreenSlidePageFragment.create(position);
            } else {
                //Y un fragmento con un texto distinto
                f = Fragmento6.nuevaInstancia(6, "Pagina 6");
            }

            return f;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        /**
         * Caso que le agreguemos Tabs
         * Metodo par aobtener el titulo
         */
        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }
}
