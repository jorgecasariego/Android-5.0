package com.androidatc.detailfragment;

import android.app.ListFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements MyListFragment.Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void Message(String os_name) {
        DetailFragment detailFragment = (DetailFragment)getFragmentManager().findFragmentById(R.id.detail_fragment);

        if(detailFragment != null && detailFragment.isInLayout()){
            detailFragment.setTexto(os_name);
        } else {
            Intent i = new Intent(getApplicationContext(), DetailActivity.class);
            Bundle extras = new Bundle();
            extras.putString(DetailActivity.os_name, os_name);
            i.putExtras(extras);
            startActivity(i);
        }

    }
}
