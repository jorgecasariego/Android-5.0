package com.androidatc.androidui;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


public class ImDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Si no queremos que muestre el titulo de la actividad
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_im_dialog);
    }

}
