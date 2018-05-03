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

public class ListController {


    private final Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;

    public ListController(Context context) {
        this.mContext = context;
        mDbHelper = new DatabaseHelper(mContext);
    }

    public ListController open() throws SQLException {
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


    public List<CategoriesListObject> getCategories(String locale, Context context) {
        String gjuha = "al";
        if (locale.equals("zz")) {
            gjuha = "en";
        } else if (locale.equals("de")) {
            gjuha = "de";
        } else if (locale.equals("tr")) {
            gjuha = "tr";
        }
        List<CategoriesListObject> returnList = new ArrayList<CategoriesListObject>();
        Cursor c = mDb.rawQuery("select tagu.*, (select count (*) from crosstagajet crstag where crstag.tagid=tagu._id) as description from tbltaguiajetit_" + gjuha + " tagu order by tagu.tagu;", null);
        if (c != null) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                CategoriesListObject obj = new CategoriesListObject(c.getInt(0), c.getString(1), context.getString(R.string.ekzistojne) + " " + c.getString(2) + " " + context.getString(R.string.numriiajeteve));
                returnList.add(obj);
                c.moveToNext();
            }
            c.close();
        }
        return returnList;

    }

    public List<CategoriesListObject> getCategoriesLike(String locale, Context context, String tagu) {
        String gjuha = "al";
        if (locale.equals("zz")) {
            gjuha = "en";
        } else if (locale.equals("de")) {
            gjuha = "de";
        } else if (locale.equals("tr")) {
            gjuha = "tr";
        }
        List<CategoriesListObject> returnList = new ArrayList<CategoriesListObject>();
        Cursor c = mDb.rawQuery("select tagu.*, (select count (*) from crosstagajet crstag where crstag.tagid=tagu._id) as description from tbltaguiajetit_" + gjuha + " tagu where tagu.tagu like '" + tagu + "%' order by tagu.tagu;", null);
        if (c != null) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                CategoriesListObject obj = new CategoriesListObject(c.getInt(0), c.getString(1), context.getString(R.string.ekzistojne) + " " + c.getString(2) + " " + context.getString(R.string.numriiajeteve));
                returnList.add(obj);
                c.moveToNext();
            }
            c.close();
        }
        return returnList;

    }

    public List<AyahListObject> getAyahsForCategory(String locale, Context context, String tagu) {
        String gjuha = "al";
        if (locale.equals("zz")) {
            gjuha = "en";
        } else if (locale.equals("de")) {
            gjuha = "de";
        } else if (locale.equals("tr")) {
            gjuha = "tr";
        }
        List<AyahListObject> returnList = new ArrayList<AyahListObject>();
        String sql = "select ank._id,ank.surja_emri || ' ' || aj.idajetit as ajetikuranor , ta.tagu , ank.ajeti as ajeti, substr(ank.ajeti,0,50) as ajshkurt from tblajetetkuranore aj left join tblajetetnekuran_"
                + gjuha
                + " ank on aj.ajetikuranor = ank.surja_id and aj.idajetit = ank.ajeti_id left join crosstagajet cta on aj._id=cta.ajetid left join tbltaguiajetit_"
                + gjuha
                + " ta on ta._id = cta.tagid where ta.tagu='"
                + tagu.replace("\'", "\'\'") + "'  order by ank.surja_id, ank.ajeti_id";
        Cursor c = mDb.rawQuery(sql, null);
        if (c != null) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                AyahListObject obj = new AyahListObject(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4));
                returnList.add(obj);
                c.moveToNext();
            }
            c.close();
        }
        return returnList;
    }


}
