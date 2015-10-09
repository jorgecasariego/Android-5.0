package com.enterprise.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private EditText mNumero1;
    private EditText mNumero2;
    private Button mSumarButton;
    private Button mRestarButton;
    private TextView mResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumero1 = (EditText) findViewById(R.id.numero1EditText);
        mNumero2 = (EditText) findViewById(R.id.numero2EditText);

        mSumarButton = (Button) findViewById(R.id.sumarButton);
        mRestarButton = (Button) findViewById(R.id.restarButton);

        mResultado = (TextView) findViewById(R.id.resultadoTextView);

        // setOnClickListener(View.OnClickListener l)
        // ******************************************
        // setOnclickListener: Register a callback to be invoked when this view is clicked.
        // Un callback en una referencia a una accion ejecutable. Que es pasada como argumento
        // a otra funcion. En este caso entonces el callback es el metodo onClick
        mSumarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numero1 = Integer.parseInt(mNumero1.getText().toString());
                int numero2 = Integer.parseInt(mNumero2.getText().toString());

                Integer resultado = sumar(numero1, numero2);

                mResultado.setText(resultado.toString());
            }
        });

        mRestarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numero1 = Integer.parseInt(mNumero1.getText().toString());
                int numero2 = Integer.parseInt(mNumero2.getText().toString());

                Integer resultado = restar(numero1, numero2);

                mResultado.setText(resultado.toString());
            }
        });
    }

    private Integer restar(int numero1, int numero2) {
        return numero1 -  numero2;
    }

    private int sumar(int numero1, int numero2) {
        return numero1 + numero2;
    }

}
