package com.example.alquiguia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class CalculadoraResistoresActivity extends AppCompatActivity {


    private EditText edResistencia1;
    private EditText edResistencia2;
    private TextView resultadoResEquivalente;
    private Button calcularResEquivalente;
    private int tipo;
    private MediaPlayer mediaPlayer;


    public void calcularSerie()
    {
        double r1Num = Double.parseDouble(edResistencia1.getText().toString());

        double r2Num = Double.parseDouble(edResistencia2.getText().toString());

        double resultado = r1Num + r2Num;

        resultadoResEquivalente.setText(String.valueOf(resultado));

    }

    public void calcularParalelo()
    {

        double r1Num = Double.parseDouble(edResistencia1.getText().toString());

        double r2Num = Double.parseDouble(edResistencia2.getText().toString());

        double resultado = (r1Num * r2Num) / (r1Num + r2Num);

        resultadoResEquivalente.setText(String.valueOf(resultado));

    }

    public void calculo(View View)
    {

        if(mediaPlayer != null)
        {
            mediaPlayer.start();
        }

        if(tipo == 0)
        {
            //calculo em serie
            calcularSerie();
        }
        else
        {
            // calculo em paralelo
            calcularParalelo();
        }



    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_resistores);

        edResistencia1 = findViewById(R.id.edResistencia1);
        edResistencia2 = findViewById(R.id.edResistencia2);
        resultadoResEquivalente = findViewById(R.id.resultadoResEquivalente);
        calcularResEquivalente = findViewById(R.id.calcularResEquivalente);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.choque);


        Bundle recebeDados = getIntent().getExtras();
        tipo = recebeDados.getInt("tipo");

        if(tipo == 1)
        {
            ImageView imgTipo = findViewById(R.id.imgTipo);
            imgTipo.setImageResource(R.drawable.paralelo);
        }



    }
}
