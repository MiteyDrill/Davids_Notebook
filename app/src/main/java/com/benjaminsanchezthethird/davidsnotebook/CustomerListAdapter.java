package com.benjaminsanchezthethird.davidsnotebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Benjamin on 6/25/2018.
 */

public class CustomerListAdapter extends ArrayAdapter<Customer>{

    public CustomerListAdapter(Context context, ArrayList<Customer> customers){
        super(context, 0, customers);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Customer customer = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_view_customers, parent, false);
        }

        TextView nameText = (TextView) convertView.findViewById(R.id.textViewName);
        TextView descriptionText = (TextView) convertView.findViewById(R.id.textViewDescription);

        nameText.setText(customer.getName());
        descriptionText.setText(customer.getDescription());

        return convertView;
    }

}
