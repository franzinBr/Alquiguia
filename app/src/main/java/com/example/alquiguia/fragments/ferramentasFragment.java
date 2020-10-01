package com.example.alquiguia.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.alquiguia.CalculadoraResistoresActivity;
import com.example.alquiguia.LedRGBActivity;
import com.example.alquiguia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ferramentasFragment extends Fragment {

    public ferramentasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_ferramentas, container, false);

        Button btnLED = v.findViewById(R.id.btnLed);
        btnLED.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), LedRGBActivity.class);
                startActivity(intent);
            }
        });

        Button btnRes = v.findViewById(R.id.btnRes);
        final RadioButton rbSerie = v.findViewById(R.id.rbSerie);
        final RadioButton rbParalelo = v.findViewById(R.id.rbParalelo);


        btnRes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(rbSerie.isChecked())
                {
                    Toast.makeText(getContext(), getString(R.string.janelaCS), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), CalculadoraResistoresActivity.class);
                    intent.putExtra("tipo",0);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getContext(), getString(R.string.janelaCS), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), CalculadoraResistoresActivity.class);
                    intent.putExtra("tipo",1);
                    startActivity(intent);
                }


            }
        });

        return v;
    }
}
