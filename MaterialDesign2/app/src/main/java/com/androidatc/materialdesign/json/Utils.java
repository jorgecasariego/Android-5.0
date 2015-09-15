package com.androidatc.materialdesign.json;

import org.json.JSONObject;

/**
 * Created by jorgecasariego on 10/9/15.
 */
public class Utils {
    // Este metodo controla que la clave se enceuntre en el json. Depdendiendo de eso retorna true o false
    public static boolean contains(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key) ? true : false;
    }

}