package com.benjaminsanchezthethird.davidsnotebook;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Benjamin on 6/25/2018.
 */

public class CustomerOptionsDialog extends DialogFragment{

    Customer customer;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View cView = inflater.inflate(R.layout.customers_dialog_options, null);

        builder.setView(cView);

        //customer must be instantiated before any of this can take place

        Button displayAllmeas = (Button) cView.findViewById(R.id.button2);

        final MainActivity callingActivity = (MainActivity) getActivity();

        displayAllmeas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                callingActivity.displayMeasurementsActivity(customer);
                dismiss();
            }
        });


        return builder.create();

    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }


}

