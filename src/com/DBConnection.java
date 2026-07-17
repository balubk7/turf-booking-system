package com;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static String URL =
            "jdbc:mysql://localhost:3306/turf_management";

    static String USER = "root";

    static String PASSWORD = "root";

    public static Connection getConnection() {

        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch(Exception e) {

            e.printStackTrace();
        }

        return con;
    }
}
