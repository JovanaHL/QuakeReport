package com.example.android.quakereport;

public class Earthquake {

    private Double magnitude;
    private String place;
    private Long time;
    private String mUrl;

    public Earthquake(Double mag, String plc, Long dt, String theURL){
        magnitude = mag;
        place = plc;
        time = dt;
        mUrl = theURL;
    }

    public Double getMagnitude(){
        return magnitude;
    }

    public String getPlace(){
        return place;
    }

    public Long getTimeInMilliseconds(){ return time; }

    public String getUrl(){return mUrl;}

}
