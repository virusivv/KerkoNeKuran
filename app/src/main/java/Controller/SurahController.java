package Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.kerko.ne.kuran.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.AyahListObject;
import Model.CategoriesListObject;
import Model.ListOfSurahsObject;
import Model.SurahObject;
/**
 * Created by ivasija on 10.05.2018.
 */

public class SurahController {

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;

    public SurahController(Context context) {
        this.mContext = context;
        mDbHelper = new DatabaseHelper(mContext);
    }

    public SurahController open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            mSQLException.printStackTrace();
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }


    public SurahObject getAyahsForSurah(String locale, Context context, int surja, String tabela) {
        if (locale.equals("") || surja<1)
            return null;
        SurahObject returnObject = null;
        Cursor c = mDb.rawQuery("select * from "+tabela+" where surja_id = " + surja, null);
        if (c != null) {
            c.moveToFirst();
            ArrayList<String> ajetet = new ArrayList<String>();
            for (int i = 0; i < c.getCount(); i++) {
                if (returnObject == null) {
                    returnObject = new SurahObject(c.getInt(c.getColumnIndex("_id")), c.getString(c.getColumnIndex("surja_emri")), new ArrayList<String>());
                    ajetet.add(c.getString(c.getColumnIndex("ajeti")));
                } else {
                    ajetet.add(c.getString(c.getColumnIndex("ajeti")));
                }
                c.moveToNext();
            }
            returnObject.setAjetet(ajetet);
            c.close();
        }
        return returnObject;
    }

    public ArrayList<ListOfSurahsObject> getSurahList(String locale, Context context) {

        if (locale.equals(""))
            return null;
        String gjuha = "al";
        if (locale.equals("zz")) {
            gjuha = "en";
        } else if (locale.equals("de")) {
            gjuha = "de";
        } else if (locale.equals("tr")) {
            gjuha = "tr";
        }

        ArrayList<ListOfSurahsObject> ReturnObject = new ArrayList<ListOfSurahsObject>();
        Cursor c = mDb.rawQuery("select * from tblsuretnekuran_"+gjuha, null);
        if (c != null) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                ReturnObject.add(new ListOfSurahsObject(c.getInt(c.getColumnIndex("_id")),c.getString(c.getColumnIndex("suret"))));
                c.moveToNext();
            }
            c.close();
        }
        return ReturnObject;
    }


}
