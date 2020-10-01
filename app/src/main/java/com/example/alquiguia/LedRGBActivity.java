package com.example.alquiguia;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class LedRGBActivity extends AppCompatActivity {

    private ImageView colorImg;
    private View colorView;
    private Bitmap bitmap;
    private SeekBar sbTensao;
    private TextView txtTensao, txtRed, txtGreen, txtBlue;
    private int r, g, b, tensao;



    public void calcularResistores()
    {
        float corrente = (float) 0.02;

        float tensaoRed = (float) ((r*2.0)/255);
        float tensaoGreen = (float) ((g*3.2)/255);
        float tensaoBlue = (float) ((b*3.2)/255);

        float resistorRed = (tensao - tensaoRed)/corrente;
        float resistorGreen = (tensao - tensaoGreen)/corrente;
        float resistorBlue = (tensao - tensaoBlue)/corrente;

        txtRed.setText(getString(R.string.txtRed)+": "+Math.round(resistorRed)+"\u2126");
        txtGreen.setText(getString(R.string.txtGreen)+": \t\t\t\t\t\t"+Math.round(resistorGreen)+"\u2126");
        txtBlue.setText(getString(R.string.txtBlue)+": \t\t\t\t\t\t\t"+Math.round(resistorBlue)+"\u2126");

       // txtRed.setText(String.valueOf(r));
        //txtGreen.setText(String.valueOf(g));
        //txtBlue.setText(String.valueOf(b));



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_r_g_b);


        txtRed = findViewById(R.id.txtRed);
        txtGreen = findViewById(R.id.txtGreen);
        txtBlue= findViewById(R.id.txtBlue);

        sbTensao = findViewById(R.id.sbTensao);
        txtTensao = findViewById(R.id.txtTensao);
        colorView = findViewById(R.id.viewColor);
        colorImg = findViewById(R.id.colorImg);
        colorImg.setDrawingCacheEnabled(true);
        colorImg.buildDrawingCache(true);


        colorImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE )
                {
                    bitmap = colorImg.getDrawingCache();


                   if ((int)motionEvent.getX()<bitmap.getWidth() && (int)motionEvent.getX()>0 && (int)motionEvent.getY()<bitmap.getHeight() && (int)motionEvent.getY()>0)
                    {
                        int pixel = bitmap.getPixel((int) motionEvent.getX(), (int) motionEvent.getY());

                        r = Color.red(pixel);
                        g = Color.green(pixel);
                        b = Color.blue(pixel);

                        colorView.setBackgroundColor(Color.rgb(r, g, b));

                        calcularResistores();
                    }



                }
                return true;
            }
        });


        tensao = sbTensao.getProgress()+5;
        txtTensao.setText(getString(R.string.txtTen)+": "+tensao+"V");

        sbTensao.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tensao = sbTensao.getProgress()+5;
                txtTensao.setText(getString(R.string.txtTen)+": "+tensao+"V");
                calcularResistores();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), getString(R.string.janelaRGB), Toast.LENGTH_SHORT).show();

    }
}
