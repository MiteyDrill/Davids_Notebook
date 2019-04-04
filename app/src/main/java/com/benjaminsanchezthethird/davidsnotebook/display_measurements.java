package com.benjaminsanchezthethird.davidsnotebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//Measurements are all displayed here
public class display_measurements extends AppCompatActivity {

    Measurements meas;
    Customer customer;

    MeasurementListAdapter measureAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.measurement_menu, menu);

        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //open new dialog

        DialogAddMeasurement measDialog = new DialogAddMeasurement();

        switch(item.getItemId()){
            case R.id.addMeasurement:
                measDialog.show(getFragmentManager(), "");
                break;
            default:
                //I believe this returns to the MainActivity
                int resultCode = 5;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("returningCustomer", customer);
                setResult(resultCode, resultIntent);
                finish();
        }


        //send that cust
        // omer back to main activity upon return

        //update new customer in mainactivity

        return true;
    }

    public void getMeasurement(Measurements meas){
        this.meas = meas;
    }

    public void setCustomer(Customer cust){
        this.customer = cust;
    }

    public void createAdapter(){
        measureAdapter = new MeasurementListAdapter(this, customer.getMeasurementList());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_measurements);
        //grabs intent from main activity
        Intent mainActivityIntents = getIntent();
        Customer customer = (Customer) mainActivityIntents.getSerializableExtra("customerVal");
        setCustomer(customer);//make it a local variable outside of the class


        //Toast.makeText(display_measurements.this, customer.getName(), Toast.LENGTH_SHORT).show();

        ListView measurementList = (ListView) findViewById(R.id.listViewMeasurementDisplay);

        createAdapter();

        measurementList.setAdapter(measureAdapter);



        final TextView heightAmount = (TextView) findViewById(R.id.textViewMeasurementHeightAmount);
        final TextView widthAmount = (TextView) findViewById(R.id.textViewMeasurementWidthAmount);

        final TextView type1 = (TextView) findViewById(R.id.textViewMeasurementType);
        final TextView type2 = (TextView) findViewById(R.id.textViewMeasurementType2);
        //on measurement select
        measurementList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {

                //gets measure position in measure view
                Measurements tempMeasure = (Measurements) adapter.getItemAtPosition(position);

                String height = ""+tempMeasure.getmHeight();
                String width = ""+tempMeasure.getmWidth();
                heightAmount.setText(height);
                widthAmount.setText(width);

                switch(tempMeasure.getmHeightType()){
                    case 'i':
                        type1.setText("Inches");
                        type2.setText("Inches");
                        break;
                    case 'c':
                        type1.setText("Centimeters");
                        type2.setText("Centimeters");
                        break;
                    case 'f':
                        type1.setText("Feet");
                        type2.setText("Feet");
                        break;
                    default:
                        Toast.makeText(display_measurements.this, "Measurement display error occured", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    //incase back button is pressed early...
    @Override
    public void onBackPressed() {
        int resultCode = 5;
        Intent resultIntent = new Intent();
        resultIntent.putExtra("returningCustomer", customer);
        setResult(resultCode, resultIntent);
        finish();
    }
}
