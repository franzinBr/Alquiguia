package com.example.alquiguia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HistoricoAdapter extends ArrayAdapter<HistoricoLista> {
    private final Context context;
    private final ArrayList<HistoricoLista> elementos;

    public HistoricoAdapter(Context context, ArrayList<HistoricoLista> elementos)
    {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha,parent, false);


        final Map<String,Integer> cores = new HashMap<String,Integer>();

        cores.put(getContext().getResources().getStringArray(R.array.cores)[0], getContext().getResources().getColor(R.color.Preto));
        cores.put(getContext().getResources().getStringArray(R.array.cores)[1], getContext().getResources().getColor(R.color.Marrom));
        cores.put(getContext().getResources().getStringArray(R.array.cores)[2], getContext().getResources().getColor(R.color.Vermelho));
        cores.put(getContext().getResources().getStringArray(R.array.cores)[3], getContext().getResources().getColor(R.color.Laranja));
        cores.put(getContext().getResources().getStringArray(R.array.cores)[4], getContext().getResources().getColor(R.color.Amarelo));
        cores.put(getContext().getResources().getStringArray(R.array.cores)[5], getContext().getResources().getColor(R.color.Verde));
        cores.put(getContext().getResources().getStringArray(R.array.cores)[6], getContext().getResources().getColor(R.color.Azul));
        cores.put(getContext().getResources().getStringArray(R.array.cores)[7], getContext().getResources().getColor(R.color.Violeta));
        cores.put(getContext().getResources().getStringArray(R.array.cores)[8], getContext().getResources().getColor(R.color.Cinza));
        cores.put(getContext().getResources().getStringArray(R.array.cores)[9], getContext().getResources().getColor(R.color.Branco));
        cores.put(getContext().getResources().getStringArray(R.array.cores)[10], getContext().getResources().getColor(R.color.Dourado));
        cores.put(getContext().getResources().getStringArray(R.array.cores)[11], getContext().getResources().getColor(R.color.Prata));


        LinearLayout primeiro = (LinearLayout) rowView.findViewById(R.id.primeiro);
        LinearLayout segundo = (LinearLayout) rowView.findViewById(R.id.segundo);
        LinearLayout terceiro = (LinearLayout) rowView.findViewById(R.id.terceiro);
        LinearLayout quarto = (LinearLayout) rowView.findViewById(R.id.quarto);
        TextView resistencia = (TextView) rowView.findViewById(R.id.resistencia);



        primeiro.setBackgroundColor(cores.get(elementos.get(position).getPrimeiro()));
        segundo.setBackgroundColor(cores.get(elementos.get(position).getSegundo()));
        terceiro.setBackgroundColor(cores.get(elementos.get(position).getTerceiro()));

        String cor = elementos.get(position).getQuarto();

        if(cores.containsKey(cor))
        {
            quarto.setBackgroundColor(cores.get(elementos.get(position).getQuarto()));
        }
        else
        {
            quarto.setBackgroundColor(0);
        }


        resistencia.setText(elementos.get(position).getResistencia());


        return rowView;
    }
}
