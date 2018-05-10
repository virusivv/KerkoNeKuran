package Model;

import java.util.ArrayList;

/**
 * Created by ivasija on 10.05.2018.
 */

public class SurahObject {
    private int id;
    private String emriSures;
    private ArrayList<String> ajetet = new ArrayList<>();

    public SurahObject(int id, String emriSures, ArrayList<String> ajetet) {
        this.id = id;
        this.emriSures = emriSures;
        this.ajetet = ajetet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmriSures() {
        return emriSures;
    }

    public void setEmriSures(String emriSures) {
        this.emriSures = emriSures;
    }

    public ArrayList<String> getAjetet() {
        return ajetet;
    }

    public void setAjetet(ArrayList<String> ajetet) {
        this.ajetet = ajetet;
    }
}