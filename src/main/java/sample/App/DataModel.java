package sample.App;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class DataModel {
    static ArrayList<String> nameList = new ArrayList<String>();
    static ArrayList<Integer> scoreList = new ArrayList<Integer>();

    private final SimpleStringProperty name;
    private final SimpleIntegerProperty score;

    DataModel(String n, Integer sc) {
        this.name = new SimpleStringProperty(n);
        this.score = new SimpleIntegerProperty(sc);
    }


    public String getName(){
        return name.get();
    }
    public void setName(String n){
        name.set(n);
    }

    public int getScore(){
        return score.get();
    }
    public void setScore(String sc){
        name.set(sc);
    }
}
