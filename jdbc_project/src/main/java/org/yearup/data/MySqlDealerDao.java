package org.yearup.data;

import org.yearup.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDealerDao implements DealerDao
{
    private DataSource dataSource;
    public MySqlDealerDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public List<Vehicle> getAllVehicle()
    {
        List<Vehicle>vehicles = new ArrayList<>();
        String sql = """
                SELECT *
                FROM vehicles;
                """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ) {
            ResultSet row = statement.executeQuery();

            while (row.next()) {
                String vin = row.getString("vin");
                String make = row.getString("make");
                String model = row.getString("model");
                int year = row.getInt("year");
                double milage = row.getDouble("miles");
                double price = row.getDouble("price");
                Vehicle vehicle = new Vehicle() {
                    {
                        setVin(vin);
                        setMake(make);
                        setModel(model);
                        setYear(year);
                        setMiles(milage);
                        setPrice(price);

                    }
                };
                vehicles.add(vehicle);
            }
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    @Override
    public Vehicle getByVin(String vin)
    {
        String sql = """
                SELECT V.vin
                        ,make
                        ,model
                        ,i.dealership_Id
                        FROM vehicles as V
                        INNER JOIN inventory AS i
                        	ON V.vin = i.vin
                        WHERE V.vin = ?;
                """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1,vin);

            ResultSet row = statement.executeQuery();

            if(row.next()) {
                //int vin = row.getInt("vin");
                String make = row.getString("make");
                String model = row.getString("model");
                String color = row.getString("color");
                int dealershipId = row.getInt("dealership_id");

                Vehicle vehicle = new Vehicle() {{
                    setVin(vin);
                    setMake(make);
                    setModel(model);
                    setDealershipId(dealershipId);
                }};
                return vehicle;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            //throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Vehicle> getByPrice(double minPrice,double maxPrice)
    {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
                SELECT make
                	, model
                    , price
                FROM vehicles
                WHERE price BETWEEN ? AND ?;
                 """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setDouble(1,minPrice);
            statement.setDouble(2,maxPrice);

            ResultSet row = statement.executeQuery();

            while(row.next()) {
                String make = row.getString("make");
                String model = row.getString("model");
                double price = row.getDouble("price");

                Vehicle vehicle = new Vehicle() {{
                    setMake(make);
                    setModel(model);
                    setPrice(price);
                }};
                vehicles.add(vehicle);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> getByMakeModel(String make, String model)
    {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
                 SELECT make
                 	,model
                 FROM vehicles
                 WHERE make = ?;
                              
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, make);
            statement.setString(2, model);
            ResultSet row = statement.executeQuery();

            while (row.next()) {
                make = row.getString("make");
                model = row.getString("model");

                String finalMake = make;
                String finalModel = model;
                Vehicle vehicle = new Vehicle() {{
                    setMake(finalMake);
                    setModel(finalModel);
                }};
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    }
    @Override
    public List<Vehicle> getByColor(String color)
    {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
                SELECT make
                	,model
                    ,color
                    ,dealership_id
                FROM vehicles as V
                INNER JOIN inventory as i
                	ON V.vin = i.vin;
                """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1,color);

            ResultSet row = statement.executeQuery();

            while(row.next()) {
                String make = row.getString("make");
                String model = row.getString("model");
                color = row.getString("color");
                int dealershipId = row.getInt("dealership_id");

                String finalColor = color;
                Vehicle vehicle = new Vehicle() {{
                    setMake(make);
                    setModel(model);
                    setColor(finalColor);
                }};
                vehicles.add(vehicle);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> getByYear(int year)
    {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
                SELECT make
                	, model
                    ,year
                FROM vehicles;
                """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1,year);

            ResultSet row = statement.executeQuery();

            while(row.next())
            {
                String make = row.getString("make");
                String model = row.getString("model");
                year = row.getInt("year");

                int finalYear = year;
                Vehicle vehicle = new Vehicle() {{
                    setMake(make);
                    setModel(model);
                    setYear(finalYear);
                }};
                vehicles.add(vehicle);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> getByMilage(int min, int max)
    {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
                SELECT make
                	,model
                    ,miles
                FROM vehicles
                WHERE miles <= ?;
                """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1,min);
            statement.setInt(2,max);

            ResultSet row = statement.executeQuery();

            while(row.next()) {
                String make = row.getString("make");
                String model = row.getString("model");
                int milage = row.getInt("miles");

                Vehicle vehicle = new Vehicle() {{
                    setMake(make);
                    setModel(model);
                    setMiles(milage);
                }};
                vehicles.add(vehicle);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    }

    @Override
    public Vehicle getBySalesId(int salesId)
    {
        return null;
    }

    @Override
    public List<Vehicle> getByDealershipId(int dealershipId) {
        return null;
    }

    @Override
    public List<Vehicle> getByType(String type)
    {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
                SELECT make
                	, model
                    ,type
                From vehicles
                WHERE type =?;
                """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1,type);

            ResultSet row = statement.executeQuery();

            while(row.next()) {
                String make = row.getString("make");
                String model = row.getString("model");
                type = row.getString("type");


                Vehicle vehicle = new Vehicle() {{
                    setMake(make);
                    setModel(model);
                   // setType(type);
                }};
                vehicles.add(vehicle);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    }

    @Override
    public Vehicle create(Vehicle vehicle)
    {
        String sql = """
                INSERT INTO vehicles
                	(vin	\s
                    , make
                    ,model
                    ,color
                    , year
                    ,MILES
                    ,price)
                VALUES(?,?,?,?,?,?,?);
                """;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setString(1,vehicle.getVin());
            statement.setString(2,vehicle.getMake());
            statement.setString(3,vehicle.getModel());
            statement.setString(4,vehicle.getColor());
            statement.setInt(5,vehicle.getYear());
            statement.setDouble(6,vehicle.getMiles());
            statement.setDouble(7,vehicle.getPrice());

            statement.executeUpdate();

        }
        catch(SQLException ex)
        {

        }
        return null;
    }

    @Override
    public void update(String vin, Vehicle vehicle)
    {
        String sql = """
                UPDATE vehicles
                SET make = ?
                    , model = ?
                WHERE  vin = ?;
                """;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setString(1,vehicle.getMake());
            statement.setString(2,vehicle.getModel());

            statement.executeUpdate();

        }
        catch (SQLException ex)
        {

        }

    }

    @Override
    public void delete(String vin)
    {
        String sql = """
                DELETE FROM vehicles
                WHERE vin = ?;
                """;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setString(1, vin);

            statement.executeUpdate();

        }
        catch (SQLException ex)
        {

        }
    }
}
