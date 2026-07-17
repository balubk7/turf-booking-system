package com;

import java.sql.*;
import java.util.Scanner;

public class Booking {

    Scanner sc = new Scanner(System.in);

    public void bookSlot(int userId) {

        try {

            Connection con =
                    DBConnection.getConnection();

            System.out.print("Enter Booking Date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            Slot slot = new Slot();

            slot.showAvailableSlots(date);

            System.out.print("Enter Slot ID: ");
            int slotId = sc.nextInt();
            sc.nextLine();

            String checkQuery =
                    "SELECT * FROM bookings " +
                    "WHERE slot_id=? AND booking_date=?";

            PreparedStatement checkPst =
                    con.prepareStatement(checkQuery);

            checkPst.setInt(1, slotId);
            checkPst.setString(2, date);

            ResultSet rs = checkPst.executeQuery();

            if(rs.next()) {

                System.out.println("Slot Already Booked");

            } else {

                String query =
                        "INSERT INTO bookings(user_id,slot_id,booking_date) " +
                        "VALUES(?,?,?)";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setInt(1, userId);
                pst.setInt(2, slotId);
                pst.setString(3, date);

                pst.executeUpdate();

                System.out.println("Booking Successful");
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public void bookingHistory(int userId) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT b.booking_id,s.slot_time,b.booking_date " +
                    "FROM bookings b " +
                    "JOIN slots s ON b.slot_id=s.slot_id " +
                    "WHERE b.user_id=?";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();

            System.out.println("\nBooking History");

            while(rs.next()) {

                System.out.println(
                        "Booking ID: "
                        + rs.getInt("booking_id")
                        + " | Slot: "
                        + rs.getString("slot_time")
                        + " | Date: "
                        + rs.getDate("booking_date")
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public void cancelBooking(int userId) {

        try {

            Connection con =
                    DBConnection.getConnection();

            bookingHistory(userId);

            System.out.print(
                    "\nEnter Booking ID to Cancel: "
            );

            int bookingId = sc.nextInt();

            String query =
                    "DELETE FROM bookings " +
                    "WHERE booking_id=? AND user_id=?";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setInt(1, bookingId);
            pst.setInt(2, userId);

            int rows = pst.executeUpdate();

            if(rows > 0) {

                System.out.println(
                        "Booking Cancelled Successfully"
                );

            } else {

                System.out.println(
                        "Invalid Booking ID"
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}
