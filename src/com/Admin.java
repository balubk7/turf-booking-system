package com;

import java.sql.*;
import java.util.Scanner;

public class Admin {

    Scanner sc = new Scanner(System.in);

    public boolean adminLogin() {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            System.out.print("Admin Email: ");
            String email = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            String query =
                    "SELECT * FROM users " +
                    "WHERE email=? AND password=? " +
                    "AND role='ADMIN'";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if(rs.next()) {

                status = true;

                System.out.println(
                        "Admin Login Successful"
                );

            } else {

                System.out.println(
                        "Invalid Admin Credentials"
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    public void viewAllBookings() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT b.booking_id,u.name,s.slot_time,b.booking_date " +
                    "FROM bookings b " +
                    "JOIN users u ON b.user_id=u.id " +
                    "JOIN slots s ON b.slot_id=s.slot_id";

            PreparedStatement pst =
                    con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();

            System.out.println("\nAll Bookings");

            while(rs.next()) {

                System.out.println(
                        rs.getInt("booking_id")
                        + " | "
                        + rs.getString("name")
                        + " | "
                        + rs.getString("slot_time")
                        + " | "
                        + rs.getDate("booking_date")
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}
