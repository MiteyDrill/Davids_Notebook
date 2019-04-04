package com.benjaminsanchezthethird.davidsnotebook;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Benjamin on 6/25/2018.
 */

public class CustomerDatabase implements Serializable {

    ArrayList<Customer> customerList;

    CustomerDatabase(){
        customerList = new ArrayList<>();
    }

    //create customer
    public void createNewCustomer(Customer customer){
        customer.setId(customerList.size());
        Customer cust = new Customer(customer.getName(), customer.getDescription(), customer.getId());

        customerList.add(cust);
    }

    //add measurement for a customer
    public void addMeasurement(int customerID, Measurements measurement){

        Customer tempCust = customerList.get(customerID); //grab customer at that index

        //manipulate customer
        tempCust.addMeasurement(measurement);

        customerList.set(customerID, tempCust);

    }

    public void updateCustomer(Customer updCust){

        customerList.set(updCust.getId(), updCust);

    }

    public int getCustomerAmount(){
        return customerList.size();
    }

    public ArrayList<Customer> getCustomerList(){
        return this.customerList;
    }

}
