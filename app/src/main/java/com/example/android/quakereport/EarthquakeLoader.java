package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /** Tag for log messages */
    public static final String LOG_TAG = EarthquakeLoader.class.getSimpleName();

    /** Query URL */
    private String mUrl;

    /**
     * Contructs a new EarthquakeLoader
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading(){

        forceLoad();
    }

    /**
     *  A Background thread
     * @return list of earthquakes extracted
     */
    @Override
    public List<Earthquake> loadInBackground() {

        if(mUrl == null) {
            return null;
        }

        // Perform network request, parse the response, and extract list of earthquakes
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}
