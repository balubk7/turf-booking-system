package com;

import java.util.Scanner;

public class TurfManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        User user = new User();

        Booking booking = new Booking();

        Admin admin = new Admin();

        while(true) {

            System.out.println(
                    "\n===== TURF MANAGEMENT SYSTEM ====="
            );

            System.out.println("1. Register");
            System.out.println("2. User Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            sc.nextLine();

            switch(choice) {

                case 1:

                    user.register();
                    break;

                case 2:

                    int userId = user.login();

                    if(userId != -1) {

                        int option;

                        do {

                            System.out.println(
                                    "\n1. View Slots"
                            );

                            System.out.println(
                                    "2. Book Slot"
                            );

                            System.out.println(
                                    "3. Booking History"
                            );

                            System.out.println(
                                    "4. Cancel Booking"
                            );

                            System.out.println(
                                    "5. Logout"
                            );

                            System.out.print(
                                    "Enter Choice: "
                            );

                            option = sc.nextInt();

                            sc.nextLine();

                            switch(option) {

                                case 1:

                                    System.out.print(
                                            "Enter Date (YYYY-MM-DD): "
                                    );

                                    String date = sc.nextLine();

                                    new Slot()
                                            .showAvailableSlots(date);

                                    break;

                                case 2:

                                    booking.bookSlot(userId);

                                    break;

                                case 3:

                                    booking.bookingHistory(userId);

                                    break;

                                case 4:

                                    booking.cancelBooking(userId);

                                    break;

                                case 5:

                                    System.out.println("Logout");

                                    break;

                                default:

                                    System.out.println(
                                            "Invalid Choice"
                                    );
                            }

                        } while(option != 5);
                    }

                    break;

                case 3:

                    boolean adminStatus =
                            admin.adminLogin();

                    if(adminStatus) {

                        admin.viewAllBookings();
                    }

                    break;

                case 4:

                    System.exit(0);

                default:

                    System.out.println("Invalid Choice");
            }
        }
    }
}
