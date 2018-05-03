package Model;

/**
 * Created by ivasija on 24.04.2018.
 */

public class AyahListObject {
    int id;
    String ajetikuranor;
    String tagu;
    String ajeti;
    String ajshkurt;

    public AyahListObject(int id, String ajetikuranor, String tagu, String ajeti, String ajshkurt) {
        this.id = id;
        this.ajetikuranor = ajetikuranor;
        this.tagu = tagu;
        this.ajeti = ajeti;
        this.ajshkurt = ajshkurt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAjetikuranor() {
        return ajetikuranor;
    }

    public void setAjetikuranor(String ajetikuranor) {
        this.ajetikuranor = ajetikuranor;
    }

    public String getTagu() {
        return tagu;
    }

    public void setTagu(String tagu) {
        this.tagu = tagu;
    }

    public String getAjeti() {
        return ajeti;
    }

    public void setAjeti(String ajeti) {
        this.ajeti = ajeti;
    }

    public String getAjshkurt() {
        return ajshkurt;
    }

    public void setAjshkurt(String ajshkurt) {
        this.ajshkurt = ajshkurt;
    }


}
