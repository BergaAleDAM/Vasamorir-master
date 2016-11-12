package com.example.alejandro.vasamorir;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity  implements
        View.OnClickListener, DatePickerDialog.OnDateSetListener {


    public final int REQUEST_CODE = 1000;
    public static int muerte = 0;
    public static String[] tipo = {
            " .Muerto por sobredosis, después de que te toque la loteria. El dia  ",
            " .Infarto de tuyocardio, no debiste tomarte 23 redbull. El dia   ",
            " .Haciendo cosas marranotas, amigo, moriste a lo grande. El dia  ",
            " .Como un mundundi, atropellado por un gitano de 13 años. El dia  ",
            " .Programando como un campeón. El dia  "
    };
    public static String cadenamuerte ;
    private EditText fromDateEtxt;
    private DatePickerDialog fromDatePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCallActivity = (Button) findViewById(R.id.resultado);
        btnCallActivity.setOnClickListener(this);

        /*DATE PICKER*/
        fromDateEtxt = (EditText) findViewById(R.id.et_fecha);
        fromDateEtxt.setFocusable(false);
        fromDateEtxt.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, this, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        /*OPCIONES*/
        int anio = newCalendar.get(Calendar.YEAR)+60;
        int dia, mes;

        if(newCalendar.get(Calendar.MONTH)<2){
            mes = newCalendar.get(Calendar.MONTH) + 3;
        }else{
            mes = newCalendar.get(Calendar.MONTH);
        }
        if(newCalendar.get(Calendar.DAY_OF_MONTH)<20){
            dia = newCalendar.get(Calendar.MONTH) + 6;
        }else{
            dia = newCalendar.get(Calendar.MONTH);
        }
        /*Cadena de la fecha de muerte*/
        cadenamuerte = Integer.toString(dia) + " /" + Integer.toString(mes) + " /" + Integer.toString(anio);

        /*Dependiendo de las opciones tipo[MUERTE] cambiara*/
        CheckBox drugs = (CheckBox) findViewById(R.id.ckdrugs);
        drugs.setOnClickListener(this);
        CheckBox alco = (CheckBox) findViewById(R.id.ckalcohol);
        alco.setOnClickListener(this);
        Spinner trabajito = (Spinner) findViewById(R.id.sptrabajo);
        if (drugs.isChecked()) {
            if (alco.isChecked()) {
                muerte = 0;
            }else {
                muerte = 1;

            }
        }else if (alco.isChecked()) {
            muerte = 2;
        }else{
           if(trabajito.equals("Funcionario")){
                muerte =3;
            }else{

                muerte = ((int) Math.random() * 4); 
            }

        }
    }

    /*Si marcamos el datepicker se abrira el date picker y no resultado*/
    @Override
    public void onClick (View v)
    {
        if(v == fromDateEtxt) {
            fromDatePickerDialog.show();
        }else {


        Intent intent = new Intent (this, Resultado.class);

        EditText etInput = (EditText) findViewById(R.id.etnombre);
        intent.putExtra ("nombre", etInput.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);

    }
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


        Calendar newDate = Calendar.getInstance();
        newDate.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if ((requestCode == this.REQUEST_CODE) && (resultCode == RESULT_OK))
        {
            Button btnCallActivity = (Button) findViewById (R.id.resultado);
            btnCallActivity.setText (data.getStringExtra("saludo"));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}