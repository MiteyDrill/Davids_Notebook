package com.benjaminsanchezthethird.davidsnotebook;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Benjamin on 6/25/2018.
 */
//serializable lets me send objects through intents... I guess
public class Customer implements Serializable {

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Measurements> getMeasurementList() {
        return measureList;
    }

    int id;
    String description;
    String name;
    ArrayList<Measurements> measureList = new ArrayList<>();

    Customer(){
        name = "Null";
        description = "Null";
        id = -1;

    }

    Customer(String name, String description, int id){
        this.name = name;
        this.id = id;
        this.description = description;
    }

    public void addMeasurement(Measurements measurement){
        measureList.add(measurement);
    }

}
