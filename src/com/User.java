package com;

import java.sql.*;
import java.util.Scanner;

public class User {

    Scanner sc = new Scanner(System.in);

    public void register() {

        try {

            Connection con =
                    DBConnection.getConnection();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            String query =
                    "INSERT INTO users(name,email,password) VALUES(?,?,?)";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);

            pst.executeUpdate();

            System.out.println("Registration Successful");

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public int login() {

        int userId = -1;

        try {

            Connection con =
                    DBConnection.getConnection();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            String query =
                    "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if(rs.next()) {

                userId = rs.getInt("id");

                System.out.println(
                        "Welcome "
                        + rs.getString("name")
                );

            } else {

                System.out.println("Invalid Credentials");
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return userId;
    }
}
