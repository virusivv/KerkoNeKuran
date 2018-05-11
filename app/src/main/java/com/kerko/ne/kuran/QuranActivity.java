package com.kerko.ne.kuran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Controller.SurahController;
import Controller.SurahSpinnerAdapter;
import Model.ListOfSurahsObject;
import Model.SurahObject;

public class QuranActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String type="";
    SurahObject objekti=null;
    String tabela="";
    ArrayList<ListOfSurahsObject> Suret=new ArrayList<ListOfSurahsObject>();
    SurahSpinnerAdapter spinAdapter;
    SurahController kontrolleri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);
        type = getIntent().getStringExtra("cka");
        if (type.equals(""))
            this.finish();

        if(type.equals("gjuhet"))
        {
            tabela="tblajetetnekuran_al";
        }
        else if(type.equals("latin"))
        {
            tabela="tblkuranlatin";
        }else if(type.equals("arab"))
        {
            tabela="tblajetetnekuran_ar";
        }
        else
            finish();


        kontrolleri =new SurahController(this);
        kontrolleri.open();
        Suret=kontrolleri.getSurahList("al",this);
        kontrolleri.close();
        StringBuilder sb=new StringBuilder();

        Spinner spinner = (Spinner)findViewById(R.id.spnSurahs);
        spinAdapter = new SurahSpinnerAdapter(this,
                android.R.layout.simple_spinner_item,
                Suret);
        spinner.setAdapter(spinAdapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> adapterView, View view,
                               int position, long id) {
        fillWithAyahsFromSurah(spinAdapter.getItem(position).getId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void fillWithAyahsFromSurah(int surahID){
        kontrolleri.open();
        objekti=kontrolleri.getAyahsForSurah("al",this,surahID,tabela);
        kontrolleri.close();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<objekti.getAjetet().size();i++){
            sb.append(" {"+(i+1)+"} "+objekti.getAjetet().get(i));
        }
        TextView txt=(TextView)findViewById(R.id.txtPershkrimi);
        txt.setText(sb.toString());
        Toast.makeText(getApplicationContext(), txt.getTextSize()+" text size", Toast.LENGTH_SHORT).show();
    }

}
