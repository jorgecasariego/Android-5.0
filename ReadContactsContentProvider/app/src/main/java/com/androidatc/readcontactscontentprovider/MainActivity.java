package com.androidatc.readcontactscontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactos = (TextView) findViewById(R.id.contactos);

        Cursor cursor = getContactos();

        while (cursor.moveToNext()){
            String nombre = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
            String telefono = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactos.append("Nombre: ");
            contactos.append(nombre);
            contactos.append(" - Telefono: ");
            contactos.append(telefono);
            contactos.append("\n");
        }
    }

    private Cursor getContactos() {
        // 1. Para obtener datos del contacto Utilizar Contacts.CONTENT_URI;
        // 2. Para obtener el numero de telefono utilizar Phone.CONTENT_URI;
        // 3. Para obtener direcciones de correo utilizar Email.CONTENT_URI
        // Tanto si elegimos 1 o 2 incluyen Contacts.DISPLAY_NAME en projection

        // Uri uri = ContactsContract.Contacts.CONTENT_URI;
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;



        //Cuales son las columnas que vamos a retornar por cada fila
        String [] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };


        /**
         * @param uri The URI, using the content:// scheme, for the content to
         *         retrieve.
         * @param projection A list of which columns to return. Passing null will
         *         return all columns, which is inefficient.
         * @param selection A filter declaring which rows to return, formatted as an
         *         SQL WHERE clause (excluding the WHERE itself). Passing null will
         *         return all rows for the given URI.
         * @param selectionArgs You may include ?s in selection, which will be
         *         replaced by the values from selectionArgs, in the order that they
         *         appear in the selection. The values will be bound as Strings.
         * @param sortOrder How to order the rows, formatted as an SQL ORDER BY
         *         clause (excluding the ORDER BY itself). Passing null will use the
         *         default sort order, which may be unordered.
         */
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        return cursor;

    }


}
