package com.kerko.ne.kuran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import Controller.SurahController;
import Model.SurahObject;

public class QuranActivity extends AppCompatActivity {

    String type="";
    SurahObject objekti=null;
    int surjaEZgjedhur=1;
    String tabela="";
    ArrayList<String> Suret=new ArrayList<String>();
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
        SurahController kontrolleri =new SurahController(this);
        kontrolleri.open();
        objekti=kontrolleri.getAyahsForSurah("al",this,surjaEZgjedhur,tabela);
        StringBuilder sb=new StringBuilder();
        Suret=kontrolleri.getSurahList("al",this);
        kontrolleri.close();
        for(int i=0;i<objekti.getAjetet().size();i++){
            sb.append(" {"+(i+1)+"} "+objekti.getAjetet().get(i));
        }
        TextView txt=(TextView)findViewById(R.id.txtPershkrimi);
        txt.setText(sb.toString());

        Spinner spinner = new Spinner(this);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Suret); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

    }
}
