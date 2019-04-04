package com.benjaminsanchezthethird.davidsnotebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Benjamin on 6/25/2018.
 */

public class MeasurementListAdapter extends ArrayAdapter<Measurements> {

    public MeasurementListAdapter(Context context, ArrayList<Measurements> measurements){
        super(context, 0, measurements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Measurements measure = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_measurement_view, parent, false);
        }

        TextView descText = (TextView) convertView.findViewById(R.id.textViewMeasurementDescription);

        descText.setText(measure.getDescription());

        return convertView;
    }



}
