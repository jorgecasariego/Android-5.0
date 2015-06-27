package com.androidatc.ejemploparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jorgecasariego on 27/6/15.
 *
 * Diferencias entre Parcelable y Serializable
 * 1. Parcelable es mas rapido que la interfaz Serializable
 * 2. Parcelable requiere mas tiempo para implementar que serializable
 * 3. la interfaz serializable es mas facil de implementar
 * 4. serializable crea bastantes objetos temporales para el proceso de Reflection
 * 5. Arreglos y listas Parcelable pueden compartirse por Intents
 * 6. Con Parcelable implementas Serializacion
 */
public class Estudiante implements Parcelable {
    String nombre;
    int edad;
    String correo;

    @Override
    public int describeContents() {
        return 0;
    }

    //Guardamos los datos del estudiante en un objeto del tipo Parcelable
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.e("Estudiante", "writeToParcel");

        dest.writeString(nombre);
        dest.writeInt(edad);
        dest.writeString(correo);
    }

    //Constructor que inicializa el objeto Estudiante
    public Estudiante(String nombre, int edad, String correo) {
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
    }

    //Recuperamos los datos del estudiante desde el objeto Parcel
    //Este constructor es invocado por el metodo createFromParcel(Parcel source)
    //del objeto CREATOR
    private Estudiante(Parcel p){
        Log.e("Estudiante", "Estudiante(parcel)");

        this.nombre = p.readString();
        this.edad = p.readInt();
        this.correo = p.readString();
    }

    public static final Parcelable.Creator<Estudiante> CREATOR = new Parcelable.Creator<Estudiante>(){
        @Override
        public Estudiante createFromParcel(Parcel source) {
            Log.e("Estudiante", "createFromParcel");
            return new Estudiante(source);
        }

        @Override
        public Estudiante[] newArray(int size) {
            Log.e("Estudiante", "newArray");
            return new Estudiante[size];
        }
    };
}
