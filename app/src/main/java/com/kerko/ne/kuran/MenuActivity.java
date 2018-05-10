package com.kerko.ne.kuran;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.Random;

import Controller.CategoriesListAdapter;
import Controller.DatabaseHelper;

public class MenuActivity extends AppCompatActivity {

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ImageView imgview = (ImageView) findViewById(R.id.main_rl_menu_activity);
        int bgNumber = new Random().nextInt((16 - 1) + 1) + 1;
        switch (bgNumber) {
            case 1:
                imgview.setImageResource(R.drawable.bg1);
                break;
            case 2:
                imgview.setImageResource(R.drawable.bg2);
                break;
            case 3:
                imgview.setImageResource(R.drawable.bg3);
                break;
            case 4:
                imgview.setImageResource(R.drawable.bg4);
                break;
            case 5:
                imgview.setImageResource(R.drawable.bg5);
                break;
            case 6:
                imgview.setImageResource(R.drawable.bg6);
                break;
            case 7:
                imgview.setImageResource(R.drawable.bg7);
                break;
            case 8:
                imgview.setImageResource(R.drawable.bg8);
                break;
            case 9:
                imgview.setImageResource(R.drawable.bg9);
                break;
            case 10:
                imgview.setImageResource(R.drawable.bg10);
                break;
            case 11:
                imgview.setImageResource(R.drawable.bg11);
                break;
            case 12:
                imgview.setImageResource(R.drawable.bg12);
                break;
            case 13:
                imgview.setImageResource(R.drawable.bg13);
                break;
            case 14:
                imgview.setImageResource(R.drawable.bg14);
                break;
            case 15:
                imgview.setImageResource(R.drawable.bg15);
                break;
            case 16:
                imgview.setImageResource(R.drawable.bg16);
                break;
        }


        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        mDBHelper.openDataBase();
    }


    public void btnKategoriteClicked(View view) {
        Intent intent = new Intent(this, ListCategoriesActivity.class);
        startActivity(intent);
    }

    public void btnLexoClicked(View view) {
        Intent intent = new Intent(this, QuranActivity.class);
        if(view.getId()==R.id.btnLexoGjuhe){
            intent.putExtra("cka","gjuhet");
        }
        else if(view.getId()==R.id.btnLexoLatinisht){
            intent.putExtra("cka","latin");
        }
        else if(view.getId()==R.id.btnLexoArabisht){
            intent.putExtra("cka","arab");
        }
        startActivity(intent);
    }
}
