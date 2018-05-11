package Model;

/**
 * Created by ivasija on 10.05.2018.
 */

public class ListOfSurahsObject {
    private int id;
    private String surah;

    public ListOfSurahsObject(int id, String surah) {
        this.id = id;
        this.surah = surah;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurah() {
        return surah;
    }

    public void setSurah(String surah) {
        this.surah = surah;
    }
}
