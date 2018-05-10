package Controller;

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


    public SurahObject getCategories(String locale, Context context, String surja) {
        if (locale.equals("") || surja.equals(""))
            return null;
        String gjuha = "al";
        if (locale.equals("zz")) {
            gjuha = "en";
        } else if (locale.equals("de")) {
            gjuha = "de";
        } else if (locale.equals("tr")) {
            gjuha = "tr";
        }
        SurahObject returnObject = null;
        Cursor c = mDb.rawQuery("select * from tblajetetnekuran_" + gjuha + " where surja_emri = '" + surja + "'", null);
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

}
