package com.androidatc.materialdesign.loggin;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jorgecasariego on 1/9/15.
 */
public class L {
    public static void m(String message) {
        Log.d("JORGE", "" + message);
    }

    public static void t(Context context, String message) {
        Toast.makeText(context, message + "", Toast.LENGTH_SHORT).show();
    }
    public static void T(Context context, String message) {
        Toast.makeText(context, message + "", Toast.LENGTH_LONG).show();
    }
}
