package com.example.alquiguia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.alquiguia.fragments.ferramentasFragment;
import com.example.alquiguia.fragments.quatroBandasFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private SmartTabLayout abas;
    private ViewPager conteudo;
    private SQLiteDatabase banco;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        abas = findViewById(R.id.viewpagertab);
        conteudo = findViewById(R.id.viewpager);

        banco = openOrCreateDatabase("resicolorBD", MODE_PRIVATE, null);
        banco.execSQL("CREATE TABLE IF NOT EXISTS historico" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, primeira VARCHAR(10), segunda VARCHAR(10), terceira VARCHAR(10), quarta VARCHAR(10), resistencia VARCHAR(255))");



        FragmentPagerItemAdapter adaptador = new FragmentPagerItemAdapter
                (
                        getSupportFragmentManager(),
                        FragmentPagerItems.with(this)
                                .add(R.string.titulo_quatro, quatroBandasFragment.class)
                                .add(R.string.titulo_ferramentas, ferramentasFragment.class)
                                .create()
                );
        conteudo.setAdapter(adaptador);
        abas.setViewPager(conteudo);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        banco.close();
    }

    public void atualizaBD(String primeira, String segunda, String terceira, String quarta, String resistencia)
    {
       // Toast.makeText(MainActivity.this, "Method called From Fragment"+primeira, Toast.LENGTH_SHORT).show();
        try {
            banco.execSQL("INSERT INTO historico (primeira, segunda, terceira, quarta, resistencia) VALUES('"+primeira+"','"+segunda+"','"+terceira+"','"+quarta+"','"+resistencia+"') ");
        } catch (SQLException e)
        {

        }


    }
}
