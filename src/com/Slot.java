package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Slot {

    public void showAvailableSlots(String date) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM slots s " +
                    "WHERE s.slot_id NOT IN " +
                    "(SELECT slot_id FROM bookings WHERE booking_date=?)";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setString(1, date);

            ResultSet rs = pst.executeQuery();

            System.out.println("\nAvailable Slots");

            while(rs.next()) {

                System.out.println(
                        rs.getInt("slot_id")
                        + " - "
                        + rs.getString("slot_time")
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}
