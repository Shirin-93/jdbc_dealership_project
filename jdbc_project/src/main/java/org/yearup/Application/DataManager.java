package org.yearup.Application;

import org.yearup.data.DealerDao;
import org.yearup.models.Vehicle;

import java.util.Scanner;

public class DataManager
{
    private Scanner userInput = new Scanner(System.in);

    private DealerDao dealerDao;

    public DataManager(DealerDao dealerDao) {
        this.dealerDao = dealerDao;
    }

    public void run()
    {
        while(true)
        {
            String choice = displayHomeScreen();
            switch(choice)
            {
                case "1":
                    displayAllVehicles();
                    break;
                case"2":
                    displayByPrice();
                case"3":
                    displayByMakeModel();
                case"4":
                    displayByYearRange();
                case"5":
                    displayByColor();
                case"6":
                    displayByMilageRange();
                case"7":
                    displayByVin();
                case"8":
                    displayByType();
                case"A":
                    additionalFeatures();
                case"0":
                    System.out.println("Thanks for Visiting...");
                    System.out.println("Exiting...");


            }
        }

    }



    private String displayHomeScreen()
    {
        System.out.println();
        System.out.println("\t\t\t\tMake a selection:\n");
        System.out.println("1)Display all vehicles");
        System.out.println("2)Display vehicle by minimum and maximum price range");
        System.out.println("3)Display vehicle by make and model");
        System.out.println("4)Display vehicle by year range");
        System.out.println("5)Display vehicle by color");
        System.out.println("6)Display vehicle by Milage");
        System.out.println("7)Display vehicle by Vin");
        System.out.println("8)Display vehicle by type\n\n");
        System.out.println("Please make a selection: ");
        return userInput.nextLine().strip().toLowerCase();

    }
    private void displayAllVehicles()
    {
        System.out.println();
        System.out.println("Displaying all vehicles:\n");

        var vehicle = dealerDao.getAllVehicle();

        for (var vehicles :vehicle)
        {
            System.out.printf("%s %s %s %d %f %f\n", vehicles.getVin(),vehicles.getMake(),vehicles.getModel(),vehicles.getYear(),vehicles.getMiles(),vehicles.getPrice());
        }
    }
    private void displayByVin()
    {
        System.out.println();
        System.out.println("Enter a vin id");
        String vin = userInput.next();

        var vehicle = dealerDao.getByVin(vin);

            System.out.printf("%s %s %s\n",vehicle.getVin(),vehicle.getMake(),vehicle.getModel());



    }
    private void displayByPrice()
    {
        System.out.println();
        System.out.println("Enter the minimum price:");
        double minPrice = userInput.nextDouble();
        System.out.println("Enter the maximum price:");
        double maxPrice = userInput.nextDouble();

        var vehicle = dealerDao.getByPrice(minPrice,maxPrice);
        for (var vehicles : vehicle)
        {
            System.out.printf("%s %s %s %d\n",vehicles.getVin(),vehicles.getMake(),vehicles.getModel(),vehicles.getPrice());

        }
    }

    private void displayByMakeModel()
    {
        System.out.println();
        System.out.println("Enter the make you want to display:");
        String make = userInput.nextLine();
        System.out.println("Enter the model you want to display:");
        String model = userInput.nextLine();

        var vehicle = dealerDao.getByMakeModel(make,model);
        for (var vehicles: vehicle)
        {
            System.out.printf("%d %s %s %s %s\n",vehicles.getDealershipId(),vehicles.getMake(),vehicles.getModel(),
                                vehicles.getColor(),vehicles.getPrice());
        }
    }

    private void displayByYearRange()
    {
        System.out.println();
        System.out.println("Enter the year of vehicle:");
        int year = userInput.nextInt();

        var vehicle = dealerDao.getByYear(year);
        for(var vehicles : vehicle )
        {
            System.out.printf("%d %d %s %s %s %s",vehicles.getDealershipId(),vehicles.getYear(),vehicles.getMake(),vehicles.getModel());
        }

    }

    private void displayByColor()
    {
        System.out.println();
        System.out.println("Enter the color of the vehicle:");
        String color = userInput.nextLine();

        var vehicle = dealerDao.getByColor(color);
        for (var vehicles : vehicle)
        {
            System.out.printf("%s %s %s\n",vehicles.getMake(),vehicles.getModel(),vehicles.getColor());
        }
    }

    private void displayByMilageRange()
    {
        System.out.println();
        System.out.println("Enter the minimum milage range:");
        int min = userInput.nextInt();
        System.out.println("Enter the maximum milage range:");
        int max = userInput.nextInt();

        var vehicle = dealerDao.getByMilage(min,max);
        for( var vehicles : vehicle)
        {
            System.out.printf("%s %s %d",vehicles.getMake(),vehicles.getModel(),vehicles.getMiles());
        }
    }

    private void displayByType()
    {
        System.out.println();
        System.out.println("Enter the vehicle type:");
        String type = userInput.nextLine();

        var vehicle = dealerDao.getByType(type);
        for (var vehicles : vehicle)
        {
            System.out.printf("%s %s %s",vehicles.getMake(),vehicles.getModel(),vehicles.getType());
        }
    }
    private void additionalFeatures()
    {
        System.out.println();
        System.out.println("You have these options to choose:\n\n");
        System.out.println("a)add a car to the list");
        System.out.println("b)Update a car in the list");
        System.out.println("c)Delete a car from the list");
        System.out.println("Make a selection:");

        String choice= userInput.nextLine();
        switch(choice)
        {
            case"a":
                addVehicle();
                break;
            case"b":
                updateVehicle();
                break;
            case "c":
                deleteVehicle();
            case "x":
                displayHomeScreen();
                break;
            default:
                System.out.println("Invalid option please try again");
        }

    }

    private void deleteVehicle()
    {
        System.out.println();
        System.out.println("Enter the vin id:");
        String vin = userInput.nextLine();

        var vehicle = dealerDao.getByVin(vin);

        addVehicle();

        System.out.println();
        System.out.println("Are you sure? (y/n)");
        String answer = userInput.nextLine().strip();

        if(answer.equalsIgnoreCase("y"))
        {
            dealerDao.delete(vin);

            System.out.println(vehicle.getMake()+ vehicle.getModel() + "has been deleted");
        }
    }

    private void updateVehicle()
    {
        System.out.println();
        System.out.println("Enter vin of car to be updated:");
        String vin = userInput.nextLine();

        var vehicle = dealerDao.getByVin(vin);
        addVehicle();

        System.out.println("Enter new make name:");
        String make = userInput.nextLine();
        System.out.println("Enter new model name:");
        String model = userInput.nextLine();

        vehicle.setMake(make);
        vehicle.setModel(model);

        dealerDao.update(vin,vehicle);

        addVehicle();
    }

    private void addVehicle()
    {
        System.out.println();
        System.out.println("Enter a vin number:");
        String vin = userInput.nextLine();
        System.out.println("Enter the make:");
        String make = userInput.nextLine();
        System.out.println("Enter the model:");
        String model = userInput.nextLine();
        System.out.println("Enter the color:");
        String color = userInput.nextLine();
        System.out.println("Enter vehicle year:");
        int year = userInput.nextInt();
        System.out.println("Enter the miles:");
        double milage = userInput.nextDouble();
        System.out.println("Enter the price");
        double price = userInput.nextDouble();

        Vehicle vehicle = new Vehicle()
        {{
            setVin(vin);
            setMake(make);
            setModel(model);
            setColor(color);
            setYear(year);
            setMiles(milage);
            setPrice(price);
        }};
        var newVehicle = dealerDao.create(vehicle);

        addVehicle();
    }


}
