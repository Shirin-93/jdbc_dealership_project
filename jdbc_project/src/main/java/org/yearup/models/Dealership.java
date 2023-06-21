package org.yearup.models;

import java.util.ArrayList;
public class Dealership
{
    private String name;
    private String address;
    private String phone;
    private int salesId;
    private String customerName;
    private double salesPrice;
    private int recordingFee;
    private int processingFee;
    private double salesTax;
    private int leaseId;
    private double endingValue;
    private double leaseFee;
    private double monthlyPayment;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public int getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(int recordingFee) {
        this.recordingFee = recordingFee;
    }

    public int getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(int processingFee) {
        this.processingFee = processingFee;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public int getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public double getEndingValue() {
        return endingValue;
    }

    public void setEndingValue(double endingValue) {
        this.endingValue = endingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone, int salesId, String customerName, double salesPrice, int recordingFee, int processingFee, double salesTax, int leaseId, double endingValue, double leaseFee, double monthlyPayment, ArrayList<Vehicle> vehicle) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.salesId = salesId;
        this.customerName = customerName;
        this.salesPrice = salesPrice;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.salesTax = salesTax;
        this.leaseId = leaseId;
        this.endingValue = endingValue;
        this.leaseFee = leaseFee;
        this.monthlyPayment = monthlyPayment;


        this.inventory = new ArrayList<>();
    }

    //method
    public Vehicle getByVin(String vin)
    {
        for(Vehicle vehicle: inventory)
        {
//
                return vehicle;

        }return null;
    }
    public ArrayList<Vehicle>getByMakeModel(String make, String model)
    {
        ArrayList<Vehicle>searchResults = new ArrayList<>();
        make = make.strip().toLowerCase();
        model = model.strip().toLowerCase();

        for(Vehicle vehicle: inventory)
        {
            if(vehicle.getMake().toLowerCase().contains(make)&&
               vehicle.getModel().toLowerCase().contains(model))
            {
                searchResults.add(vehicle);
            }
        }return searchResults;
    }
    public ArrayList<Vehicle>getByMilage(int min, int max)
    {
        ArrayList<Vehicle>searchResults = new ArrayList<>();
        for(Vehicle vehicle: inventory)
        {
            if(vehicle.getMiles()>= min && vehicle.getMiles() <= max)
            {
                searchResults.add(vehicle);
            }
        }return searchResults;
    }
    public ArrayList<Vehicle> getAllVehicles()
    {
        return inventory;
    }
    public ArrayList<Vehicle> getByPrice(double min, double max)
    {
        ArrayList<Vehicle> searchResults = new ArrayList<>();
        for(Vehicle vehicle :inventory)
        {
            if(vehicle.getPrice() >= min && vehicle.getPrice()<= max)
            {
                searchResults.add(vehicle);
            }
        }return searchResults;

    }

    public ArrayList<Vehicle> getByYear(int min, int max)
    {
        ArrayList<Vehicle> searchResults = new ArrayList<>();
        for(Vehicle vehicle : inventory)
        {
            if(vehicle.getYear() >= min && vehicle.getYear() <= max)
            {
                searchResults.add(vehicle);
            }
        }return searchResults;
    }

    public double getMonthlyPayment()
    {

        return 0;
    }
}
