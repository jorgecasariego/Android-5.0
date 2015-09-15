package com.androidatc.materialdesign.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;

import com.androidatc.materialdesign.R;
import com.androidatc.materialdesign.adapters.AdapterRecyclerAnimators;
import com.androidatc.materialdesign.views.Util;

import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

/*
    Hay 2 tipos de animaciones posibles cuando utilizamos recyclerView:
    1. Cuando insertamos, borramos o movemos items dentro del recyler view
    2. Cuando cargamos datos dentro del recycler view dentro de un activity o fragment
 */
public class ActivityRecylerAnimators extends AppCompatActivity {

    //int containing the duration of the animation run when items are added or removed from the RecyclerView
    public static final int ANIMATION_DURATION = 2000;
    //edit text letting the user type item name to be added to the recylcerview
    private EditText mInput;
    //recyclerview showing all items added by the user
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private AdapterRecyclerAnimators mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_animators);
        setupToolbar();
        initViews();

    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initViews() {
        mInput = (EditText) findViewById(R.id.text_input);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerAnimatedItems);
        mAdapter = new AdapterRecyclerAnimators(this);

        //1. Esta es una de las maneras de animar rel recycler view
        //   Utilizando el DefaultItemAnimator
        //   DefaultItemAnimator animator = new DefaultItemAnimator();

        //2. La segunda manera es utilizando una librería que hace todo el trabjo por nosotros
        //    ya que la primera opcion solo tiene animaciones basicas
        //ScaleInAnimator animator = new ScaleInAnimator();

        //3. Set an animator on the RecyclerView that works only when items are added or removed
        FlipInTopXAnimator animator = new FlipInTopXAnimator();

        animator.setAddDuration(ANIMATION_DURATION);
        animator.setRemoveDuration(ANIMATION_DURATION);

        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Invoked after user hits the button to add an Item to the RecyclerView, check the contents of the EditText,
     * if it has valid contents, add the item to the Adapter of the RecyclerView
     *
     * @param view The Button that was clicked after user types text in the EditText
     */
    public void addItem(View view) {
        //check if the EditText has valid contents
        if (Util.hasValidContents(mInput)) {
            mAdapter.addItem(mInput.getText().toString());
        }
    }

}
