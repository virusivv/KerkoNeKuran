package Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.kerko.ne.kuran.R;

import java.util.ArrayList;
import java.util.List;

import Model.AyahListObject;
import Model.CategoriesListObject;
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
                    returnObject = new SurahObject(c.getInt(0), c.getString(3), new ArrayList<String>());
                    ajetet.add(c.getString(1));
                } else {
                    ajetet.add(c.getString(1));
                }
                c.moveToNext();
            }
            returnObject.setAjetet(ajetet);
            c.close();
        }
        return returnObject;
    }


}
