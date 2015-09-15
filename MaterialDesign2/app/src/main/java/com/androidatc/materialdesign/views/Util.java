package com.androidatc.materialdesign.views;

import android.widget.EditText;

/**
 * Created by jorgecasariego on 14/9/15.
 */
public class Util {
    public static boolean hasValidContents(EditText editText) {
        if (editText != null
                && editText.getText() != null
                && editText.getText().toString() != null
                && editText.getText().toString().trim().length() > 0) {
            return true;
        }
        return false;
    }
}
