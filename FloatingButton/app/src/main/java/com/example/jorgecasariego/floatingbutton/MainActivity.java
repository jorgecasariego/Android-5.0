package com.example.jorgecasariego.floatingbutton;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements
        OnClickListener,
        AbsListView.OnScrollListener{

    private FloatingActionButton floatingActionButton;
    private int mPreviouVisibleItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Locale[] availableLocales = Locale.getAvailableLocales();
        List<String> locales = new ArrayList<>();

        for(Locale locale : availableLocales){
            locales.add(locale.getDisplayName());
        }

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, locales));


        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.hide(false);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                floatingActionButton.show(true);
                floatingActionButton.setShowAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.show_from_bottom));
                floatingActionButton.setHideAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.hide_to_bottom));
            }
        }, 300);

        floatingActionButton.setOnClickListener(this);

        listView.setOnScrollListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                startActivity(new Intent(MainActivity.this, FloatingMenuActivity.class));
                break;

        }

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem > mPreviouVisibleItem){
            floatingActionButton.hide(true);
        } else if (firstVisibleItem < mPreviouVisibleItem){
            floatingActionButton.show(true);
        }

        mPreviouVisibleItem = firstVisibleItem;
    }



}
