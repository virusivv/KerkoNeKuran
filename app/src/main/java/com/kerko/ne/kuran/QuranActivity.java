package com.kerko.ne.kuran;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Controller.SurahController;
import Controller.SurahSpinnerAdapter;
import Model.ListOfSurahsObject;
import Model.SurahObject;

public class QuranActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener {

    String type="";
    SurahObject objekti=null;
    String tabela="";
    ArrayList<ListOfSurahsObject> Suret=new ArrayList<ListOfSurahsObject>();
    SurahSpinnerAdapter spinAdapter;
    SurahController kontrolleri;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private static int NUM_PAGES = 5;
    int totalPageNumber=1;



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
        objekti=kontrolleri.getAyahsForSurah("al",this,1,tabela);
        kontrolleri.close();
        StringBuilder sb=new StringBuilder();

        if(objekti.getAjetet().size()%10>0)
            NUM_PAGES=((int)objekti.getAjetet().size()/10)+1;
        else
            NUM_PAGES=(int)objekti.getAjetet().size()/10;
        Spinner spinner = (Spinner)findViewById(R.id.spnSurahs);
        spinAdapter = new SurahSpinnerAdapter(this,
                android.R.layout.simple_spinner_item,
                Suret);
        spinner.setAdapter(spinAdapter);
        spinner.setOnItemSelectedListener(this);


        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
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


    public String get10ItemsForPage(int pageNumber){
        StringBuilder sb=new StringBuilder();
        if(pageNumber<=NUM_PAGES){
            for(int i=pageNumber * 10;i<pageNumber * 10+10;i++)
                if(i<objekti.getAjetet().size())
                    sb.append(objekti.getAjetet().get(i)+" {"+(i+1)+"} ");
            /*if(pageNumber>1)
                for(int i=(pageNumber+1)*10-10;i<(pageNumber+1)*10-1;i++){
                    if(i<=objekti.getAjetet().size())
                        sb.append(objekti.getAjetet().get(i)+" {"+(i+1)+"} ");
                }
            else if(pageNumber==1)
                for(int i=10;i<=19;i++) {
                    if (i < objekti.getAjetet().size())
                        sb.append(objekti.getAjetet().get(i)+" {"+(i+1)+"} ");
                }
            else {
                for (int i = 0; i <= 9; i++)
                    if (i < objekti.getAjetet().size())
                        sb.append(objekti.getAjetet().get(i)+" {"+(i+1)+"} ");
            }*/
        }
        return sb.toString();
    }


    public void fillWithAyahsFromSurah(int surahID){
        kontrolleri.open();
        objekti=kontrolleri.getAyahsForSurah("al",this,surahID,tabela);
        kontrolleri.close();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<objekti.getAjetet().size();i++){
            sb.append(" {"+(i+1)+"} "+objekti.getAjetet().get(i));
        }
        if(objekti.getAjetet().size()%10>0)
            NUM_PAGES=((int)objekti.getAjetet().size()/10)+1;
        else
            NUM_PAGES=(int)objekti.getAjetet().size()/10;

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        TextView txt=(TextView)mPager.findViewById(R.id.txtPershkrimi);
        //txt.setText(sb.toString());
        //Toast.makeText(getApplicationContext(), txt.getTextSize()+" text size", Toast.LENGTH_SHORT).show();



    }
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.newInstance(position, get10ItemsForPage(position),objekti.getEmriSures());
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
