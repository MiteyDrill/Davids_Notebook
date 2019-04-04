package com.benjaminsanchezthethird.davidsnotebook;

import java.io.Serializable;

/**
 * Created by Benjamin on 6/25/2018.
 */

//holds basic measurements
public class Measurements implements Serializable {

    double mHeight;
    double mWidth;
    char mHeightType; //Height in: cm[c], feet[f], inch[i]...
    char mWidthType; //Width in: cm[c], f[f], inch[i]...

    String description; //What is being measured

    //default constructor
    Measurements(){
        mHeight = -1;
        mWidth = -1;
        mHeightType = 'c';
        mWidthType = 'c';
        this.description = "None provided....";
    }

    Measurements(double height, double width, char heightType, char widthType, String desc){
        this.mHeight = height;
        this.mWidth = width;
        this.mHeightType = heightType;
        this.mWidthType = widthType;

        this.description = desc;
    }

    public double getmHeight() {
        return mHeight;
    }

    public void setmHeight(double mHeight) {
        this.mHeight = mHeight;
    }

    public double getmWidth() {
        return mWidth;
    }

    public void setmWidth(double mWidth) {
        this.mWidth = mWidth;
    }

    public char getmHeightType() {
        return mHeightType;
    }

    public void setmHeightType(char mHeightType) {
        this.mHeightType = mHeightType;
    }

    public char getmWidthType() {
        return mWidthType;
    }

    public void setmWidthType(char mWidthType) {
        this.mWidthType = mWidthType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
