package com.androidatc.detailfragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Created by jorgecasariego on 5/6/15.
 */
public class DetailActivity extends Activity {
    public static String os_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }

        setContentView(R.layout.detail_activity);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String nombre = extras.getString(os_name);
            DetailFragment detailFragment = (DetailFragment)getFragmentManager().findFragmentById(R.id.detail_fragment);
            detailFragment.setTexto(nombre);
        }
    }
}
