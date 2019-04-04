package com.benjaminsanchezthethird.davidsnotebook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Benjamin on 6/26/2018.
 */

public class DialogAddMeasurement extends DialogFragment {

    Measurements tempMeasure;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View cView = inflater.inflate(R.layout.dialog_add_measurement, null);

        builder.setView(cView);

        //get the calling activity
        final display_measurements callingMeasureActivity = (display_measurements) getActivity();

        //get the button for the switch case
        Button finishButton = (Button) cView.findViewById(R.id.buttonDone);

        finishButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                //create measure info here...

                //get view stuff
                char type = 'i';
                EditText descriptionText = (EditText) cView.findViewById(R.id.editTextMeasurementDescription);
                EditText heightText = (EditText) cView.findViewById(R.id.editTextGetHeightAmount);
                EditText widthText = (EditText) cView.findViewById(R.id.editTextGetWidthAmount);

                RadioGroup radioGroup = (RadioGroup) cView.findViewById(R.id.radioListTypes);

                //get type
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radioButtonCentimeters:
                        type = 'c';
                        break;
                    case R.id.radioButtonInches:
                        type = 'i';
                        break;
                    case R.id.radioButtonFeet:
                        type='f';
                        break;
                }

                //create measure and set the variables

                //change these to double in the future!!
                double height = Double.parseDouble(heightText.getText().toString());
                double width = Double.parseDouble(widthText.getText().toString());

                tempMeasure = new Measurements(height, width, type, type, descriptionText.getText().toString());

                callingMeasureActivity.customer.addMeasurement(tempMeasure); //meas is added

                callingMeasureActivity.measureAdapter.notifyDataSetChanged();


                dismiss();

            }

        });


        return builder.create();

    }
}
