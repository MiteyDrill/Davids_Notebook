package com.benjaminsanchezthethird.davidsnotebook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Benjamin on 6/27/2018.
 */

public class DialogAddCustomer extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View cView = inflater.inflate(R.layout.dialog_add_customer, null);

        builder.setView(cView);

        //get the calling activity
        final MainActivity callingMainActivity = (MainActivity) getActivity();

        final EditText editTextGetName = (EditText) cView.findViewById(R.id.editTextGetCustomerName);
        final EditText editTextGetDescription = (EditText) cView.findViewById(R.id.editTextGetCustomerDescription);

        Button finishButton = (Button) cView.findViewById(R.id.buttonFinishCustomerDialog);

        finishButton.setOnClickListener(new View.OnClickListener(){

            //create customer
            Customer tempCust;

            @Override
            public void onClick(View v){

                tempCust = new Customer(editTextGetName.getText().toString(), editTextGetDescription.getText().toString(), -1);

                callingMainActivity.getCustomerFromDialog(tempCust);

                dismiss();
            }

        });

        return builder.create();

    }

}
