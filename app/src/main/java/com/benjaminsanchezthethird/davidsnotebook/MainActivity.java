package com.benjaminsanchezthethird.davidsnotebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;

public class MainActivity extends AppCompatActivity {

    CustomerDatabase database = null;
    Customer newlyCreatedCustomer;
    CustomerListAdapter customerListAdapter;

    File inFile = null;

    public void createCustomerListAdapter(){
        customerListAdapter = new CustomerListAdapter(this, database.getCustomerList());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkFile();

        //creates list adapter and such
        createCustomerListAdapter();
        ListView customerListView = (ListView) findViewById(R.id.listViewCustomer);

        //displays list using adapter
        customerListView.setAdapter(customerListAdapter);

        //handles what happens when an item is clicked on listview
        customerListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {

                //gets customer position in list view
                Customer tempCust = (Customer)adapter.getItemAtPosition(position);

                //creates custom dialog based on that customer
                CustomerOptionsDialog cDial = new CustomerOptionsDialog();

                cDial.setCustomer(tempCust);

                cDial.show(getFragmentManager(), "");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.customer_menu, menu);
        // return true so that the menu pop up is opened

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.addCustomer:
                createCustomer();
                break;
        }

        return true;
    }

    public void displayMeasurementsActivity(Customer cCust){
        Intent measIntent = new Intent(this, display_measurements.class);
        //send object here...   `
        measIntent.putExtra("customerVal", cCust);
        int req_code_child = 0;

        saveData(this.database);

        this.startActivityForResult(measIntent, req_code_child);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {

        if(resultCode == 5
                ) {
            Customer myObject = (Customer) data.getExtras().getSerializable("returningCustomer");

            database.updateCustomer(myObject);
        }

        saveData(this.database); //save here

    }

    void getCustomerFromDialog(Customer customer){

        newlyCreatedCustomer = new Customer(customer.getName(), customer.getDescription(), customer.getId());

        database.createNewCustomer(newlyCreatedCustomer);


        customerListAdapter.notifyDataSetChanged();

        Toast.makeText(this, "New customer created", Toast.LENGTH_SHORT).show();

        saveData(this.database);

    }

    void createCustomer(){

        //open dialog

        DialogAddCustomer customerDialog = new DialogAddCustomer();

        customerDialog.show(getFragmentManager(), "");
    }


    //save database...
    public void saveData(CustomerDatabase data){

        //saves database...

        FileOutputStream outputStream;

        try {
            File saveFile = new File(this.getFilesDir(), "/customer_data.dat");

            outputStream = new FileOutputStream(saveFile);

            ObjectOutputStream objectOutStream = new ObjectOutputStream(outputStream);

            objectOutStream.writeObject(data);

            objectOutStream.close();

        } catch (FileNotFoundException e1){
            e1.printStackTrace();
        } catch (IOException e1){
            e1.printStackTrace();
        }


    }


    public void loadData(){

        FileInputStream inStream = null;

        File inFile = new File(this.getFilesDir(), "/customer_data.dat");

        try {

            inStream = new FileInputStream(inFile);

            ObjectInputStream objectInputStream = new ObjectInputStream(inStream);

            this.database = (CustomerDatabase) objectInputStream.readObject();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e1){
            e1.printStackTrace();
        } catch (OptionalDataException e){
            e.printStackTrace();
        } catch (StreamCorruptedException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        if(database == null){
            Toast.makeText(this, "Failed to read from file...", Toast.LENGTH_SHORT);
        }

    }

    public void checkFile(){

        File checkFile = new File(this.getFilesDir(), "/customer_data.dat");
        //if file exists read
        if(checkFile.exists()) {
            loadData();
        } else {
            database = new CustomerDatabase(); //new customerdatabase
        }

    }


}
