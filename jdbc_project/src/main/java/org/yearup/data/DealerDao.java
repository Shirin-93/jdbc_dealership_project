package org.yearup.data;

import org.yearup.models.Vehicle;

import java.util.List;

public interface DealerDao
{
    List<Vehicle> getAllVehicle();
    Vehicle getByVin(String vin);
    List<Vehicle> getByPrice(double minPrice,double maxPrice);
    List<Vehicle> getByMakeModel(String make, String model);
    List<Vehicle> getByColor(String color);
    List<Vehicle> getByYear(int year);
    List<Vehicle> getByMilage(int min, int max);
    Vehicle  getBySalesId(int salesId);
    List<Vehicle> getByDealershipId(int dealershipId);
    List<Vehicle> getByType(String type);



    Vehicle create (Vehicle vehicle);
    void update(String vin,Vehicle vehicle );
    void delete(String vin);

}
