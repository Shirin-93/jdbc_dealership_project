package org.yearup;

import org.yearup.Application.DataManager;
import org.yearup.data.DealerDao;
import org.yearup.data.MySqlDealerDao;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args)
    {
        String baseUrl = "jdbc:mysql://localhost:3306/car_dealership";
        String username = "root";
        String password = "P@ssw0rd";

        BasicDataSource dataSource = new BasicDataSource()
        {{
            setUrl(baseUrl);
            setUsername(username);
            setPassword(password);

        }};

        DealerDao dealerDao = new MySqlDealerDao(dataSource);

        DataManager data = new DataManager(dealerDao);
        data.run();


    }
}


