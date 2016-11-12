package com.example.alejandro.vasamorir;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import static com.example.alejandro.vasamorir.MainActivity.muerte;
import static com.example.alejandro.vasamorir.MainActivity.tipo;
import static com.example.alejandro.vasamorir.MainActivity.cadenamuerte;


public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);


        Intent i = getIntent();
        String nombre = i.getStringExtra("nombre");
        /*Aqui pasa el tipo de muerte*/
        String decision = tipo[muerte];


        TextView tvTexto = (TextView) findViewById (R.id.tvresultadofinale);

        /*Juntamos todas las cadenas que tiene que mostrar*/
        StringBuilder cadena = new StringBuilder();
        cadena.append("Vas a morir, ");
        cadena.append(nombre);
        cadena.append(decision);
        cadena.append(cadenamuerte);
        String cadenaFinal = cadena.toString();
        tvTexto.setText(cadenaFinal);

    }

    @Override
    public void finish() {

        Intent intent = new Intent();
        TextView tvTexto = (TextView) findViewById (R.id.tvnombre);
        intent.putExtra ("saludo", tvTexto.getText().toString());
        setResult (this.RESULT_OK, intent);

        super.finish();
    }

}
