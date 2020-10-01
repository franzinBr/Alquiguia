package com.example.alquiguia.fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alquiguia.HistoricoActivity;
import com.example.alquiguia.MainActivity;
import com.example.alquiguia.R;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class quatroBandasFragment extends Fragment {

    private Spinner pBanda;
    private Spinner sBanda;
    private Spinner tBanda;
    private Spinner qBanda;
    private TextView txtResistencia;
    private LinearLayout l1,l2,l3,l4;

    private int primeiro;
    private int segundo;
    private double multiplicador;
    private double resistencia;
    private String tolerancias[], tolerancia;




    public quatroBandasFragment() {
        // Required empty public constructor
    }


    public void calculaResistencia(int id, int posicao)
    {


        switch (id)
        {
            case R.id.pBanda:
                primeiro = (posicao+1) * 10;
                break;
            case R.id.sBanda:
                segundo = posicao;
                break;
            case R.id.tBanda:
                if(posicao < 10)
                {
                    multiplicador = Math.pow(10.0, Double.valueOf(posicao));
                }
                else
                {
                    multiplicador = 10.0 / Math.pow(10.0, Double.valueOf(posicao-8));
                }
                break;
            case R.id.qBanda:
                tolerancia = tolerancias[posicao];
                break;
        }

        String cor1 = pBanda.getSelectedItem().toString();
        String cor2 = sBanda.getSelectedItem().toString();
        String cor3 = tBanda.getSelectedItem().toString();
        String cor4 = qBanda.getSelectedItem().toString();


        String resTxt = "";
        resistencia = (double)(primeiro + segundo);
        if(multiplicador <= 100)
        {
            resistencia *= multiplicador;
            resTxt = String.valueOf(resistencia)+"\u2126"+" \u00B1"+tolerancia+"%" ;
        }
        else
        {
            String sufix = "";

            if(multiplicador >= 1000.0 && multiplicador <= 100000.0)
            {
                sufix = "K";
                multiplicador /= 1000.0;
            }
            else if(multiplicador >= 1000000.0 && multiplicador <= 100000000.0)
            {
                sufix = "M";
                multiplicador /= 1000000.0;
            }
            else {
                sufix = "G";
                multiplicador = 1;
            }

            resTxt = String.valueOf((int)(resistencia*multiplicador))+sufix+"\u2126"+" \u00B1"+tolerancia+"%" ;
        }

        txtResistencia.setText(resTxt);

        ((MainActivity) getActivity()).atualizaBD(cor1,cor2,cor3,cor4,resTxt);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_quatro_bandas, container, false);


        final Map<String,Integer> cores = new HashMap<String,Integer>();
        cores.put(getResources().getStringArray(R.array.cores)[0], getResources().getColor(R.color.Preto));
        cores.put(getResources().getStringArray(R.array.cores)[1], getResources().getColor(R.color.Marrom));
        cores.put(getResources().getStringArray(R.array.cores)[2], getResources().getColor(R.color.Vermelho));
        cores.put(getResources().getStringArray(R.array.cores)[3], getResources().getColor(R.color.Laranja));
        cores.put(getResources().getStringArray(R.array.cores)[4], getResources().getColor(R.color.Amarelo));
        cores.put(getResources().getStringArray(R.array.cores)[5], getResources().getColor(R.color.Verde));
        cores.put(getResources().getStringArray(R.array.cores)[6], getResources().getColor(R.color.Azul));
        cores.put(getResources().getStringArray(R.array.cores)[7], getResources().getColor(R.color.Violeta));
        cores.put(getResources().getStringArray(R.array.cores)[8], getResources().getColor(R.color.Cinza));
        cores.put(getResources().getStringArray(R.array.cores)[9], getResources().getColor(R.color.Branco));
        cores.put(getResources().getStringArray(R.array.cores)[10], getResources().getColor(R.color.Dourado));
        cores.put(getResources().getStringArray(R.array.cores)[11], getResources().getColor(R.color.Prata));

        tolerancias = getResources().getStringArray(R.array.tolerancia);
        primeiro = 1;
        segundo = 0;
        multiplicador = 1;
        tolerancia = "1";
        final boolean[] atualiza = {false};



        l1 = v.findViewById(R.id.listra1);
        l2 = v.findViewById(R.id.listra2);
        l3 = v.findViewById(R.id.listra3);
        l4 = v.findViewById(R.id.listra4);


        pBanda = v.findViewById(R.id.pBanda);
        sBanda = v.findViewById(R.id.sBanda);
        tBanda = v.findViewById(R.id.tBanda);
        qBanda = v.findViewById(R.id.qBanda);

        txtResistencia = v.findViewById(R.id.resistencia);

        Button btnHistorico = v.findViewById(R.id.btnHistorico);
        btnHistorico.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), HistoricoActivity.class);
                startActivity(intent);
            }
        });



        final ArrayAdapter<CharSequence> pAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.cores_primeiro, android.R.layout.simple_spinner_item );
        pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> sAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.cores_digitos, android.R.layout.simple_spinner_item );
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> tAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.cores, android.R.layout.simple_spinner_item );
        tAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> qAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.cores_tolerancia, android.R.layout.simple_spinner_item );
        qAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pBanda.setAdapter(pAdapter);
        sBanda.setAdapter(sAdapter);
        tBanda.setAdapter(tAdapter);
        qBanda.setAdapter(qAdapter);

        pBanda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(atualiza[0])
                     calculaResistencia(adapterView.getId(), i);

                String cor = pBanda.getSelectedItem().toString();
                l1.setBackgroundColor(cores.get(cor));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sBanda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(atualiza[0])
                    calculaResistencia(adapterView.getId(), i);

                String cor = sBanda.getSelectedItem().toString();
                l2.setBackgroundColor(cores.get(cor));
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tBanda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(atualiza[0])
                    calculaResistencia(adapterView.getId(), i);

                String cor = tBanda.getSelectedItem().toString();
                l3.setBackgroundColor(cores.get(cor));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        qBanda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(atualiza[0]){
                    calculaResistencia(adapterView.getId(), i);
                }

                atualiza[0] = true;


                String cor = qBanda.getSelectedItem().toString();

                if(cores.containsKey(cor))
                {
                    l4.setBackgroundColor(cores.get(cor));
                }
                else
                {
                    l4.setBackgroundColor(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Inflate the layout for this fragment
        return v;
    }
}
