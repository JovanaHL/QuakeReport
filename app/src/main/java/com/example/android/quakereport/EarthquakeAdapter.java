package com.example.android.quakereport;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    // Helper methods to format date and time to display in TextView below
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    // Helper method that uses switch case to determine the color (using its int resource id)
    // based on its magnitude
    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);

    }

    // View that helps display in list
    public EarthquakeAdapter(Activity context, List<Earthquake> earthquakes){
        super(context, 0, earthquakes);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);


        // Magnitude (Decimal, ex: "7.2")
        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitude = formatter.format(currentEarthquake.getMagnitude());
        TextView magTextView = (TextView) listItemView.findViewById(R.id.magnitude_num);
        magTextView.setText(magnitude);

        // Set background to color based on magnitude
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();
        // Get the color to change it to
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        // Offset (ex: 70km NW of) and Place/Location (ex: Shizunai, Japan)
        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.offset);
        TextView placeTextView = (TextView) listItemView.findViewById(R.id.place_name);
        String place = currentEarthquake.getPlace();

        // If the string contains an offset (which contains "of") then split
        // into two parts, first: offset, second: primary location
        // If doesn't contain offset, the string is already the primary location
        if(place.contains("of")) {
            String[] parts = place.split("(?<=of)");
            String offset = parts[0];
            String primaryLocation = parts[1];
            offsetTextView.setText(offset);
            placeTextView.setText(primaryLocation);
        }
        else {
            offsetTextView.setText("Near the");
            placeTextView.setText(place);
        }


        // Date (ex: "Mar 3, 1984")
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_time);
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        // Time in hours
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeTextView.setText(formattedTime);


        return listItemView;
    }

}
