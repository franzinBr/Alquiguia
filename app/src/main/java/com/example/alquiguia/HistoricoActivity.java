package com.example.alquiguia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HistoricoActivity extends AppCompatActivity {

    private SQLiteDatabase banco;
    private ListView listViewHistorico;
    private ArrayList<HistoricoLista> historicos;
    private ArrayAdapter adapt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);


        try {
            banco = openOrCreateDatabase("resicolorBD", MODE_PRIVATE, null);
            Cursor cursor = banco.rawQuery("SELECT * FROM historico", null);
            cursor.moveToFirst();


            historicos = new ArrayList<HistoricoLista>();

            while (!cursor.isAfterLast())
            {
                HistoricoLista his = new HistoricoLista(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                historicos.add(his);
                cursor.moveToNext();
            }

            listViewHistorico = findViewById(R.id.listaHistorico);

            adapt = new HistoricoAdapter(this,historicos);

            listViewHistorico.setAdapter(adapt);


        }catch (Exception e)
        {
            e.printStackTrace();
        }



    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), getString(R.string.historico), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        banco.close();
    }


    public void limparHistorico(View view)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(R.string.alert_title);
        alerta.setTitle(R.string.alert_msg);

        alerta.setPositiveButton(getString(R.string.alert_r1), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                banco.execSQL("DELETE FROM historico");
                historicos.clear();
                adapt.notifyDataSetChanged();

            }
        });

        alerta.setNegativeButton(getString(R.string.alert_r2), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });
        alerta.create();
        alerta.show();

    }
}
