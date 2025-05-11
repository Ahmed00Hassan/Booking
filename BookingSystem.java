
package flight_booking_system;

import java.util.*;
import java.io.*;


public class BookingSystem {

    private List<User> users;
    private List<Flight> flights;
    private List<Booking> bookings;

    Scanner input = new Scanner(System.in);

    public BookingSystem() {
        users = FileManager.loadUsers();
        flights = FileManager.loadFlights();
        bookings = FileManager.loadBookings();
    }

    // 1. searchFlights()
    public void searchFlights() {
        System.out.print("Enter source city: ");
        String source = input.nextLine();
        System.out.print("Enter destination city: ");
        String destination = input.nextLine();

        boolean found = false;
        for (Flight flight : flights) {
            if (flight.getSource().equalsIgnoreCase(source) &&
                flight.getDestination().equalsIgnoreCase(destination)) {
                flight.displayDetails();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching flights found.");
        }
    }

    // 2. createBooking()
    public void createBooking(Customer customer) {
        System.out.print("Enter flight ID: ");
        String flightId = input.nextLine();

        Flight selectedFlight = null;
        for (Flight f : flights) {
            if (f.getFlightID().equalsIgnoreCase(flightId)) {
                selectedFlight = f;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Flight not found.");
            return;
        }

        System.out.print("Number of seats: ");
        int count = Integer.parseInt(input.nextLine());

        Booking booking = new Booking("BK" + System.currentTimeMillis(), customer, selectedFlight);

        for (int i = 1; i <= count; i++) {
            System.out.println("Passenger " + i);
            System.out.print("ID: ");
            String id = input.nextLine();
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Passport: ");
            String passport = input.nextLine();
            System.out.print("DOB: ");
            String dob = input.nextLine();
            System.out.print("Special Requests: ");
            String special = input.nextLine();
            System.out.print("Seat Type: ");
            String seatType = input.nextLine();

            Passenger p = new Passenger(id, name, passport, dob, special);
            booking.addPassenger(p, seatType);
            FileManager.savePassenger(p);
        }

        FileManager.saveBooking(booking);
        bookings.add(booking);
        System.out.println("Booking created successfully. Ref: " + booking.getBookingReference());
    }

    // 3. processPayment()
    public void processPayment(String bookingRef) {
        for (Booking booking : bookings) {
            if (booking.getBookingReference().equalsIgnoreCase(bookingRef)) {
                System.out.println("Total amount due: " + booking.calculateTotalPrice());
                System.out.print("Confirm payment? (yes/no): ");
                String confirm = input.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    booking.setPaymentStatus("paid");
                    booking.confirmBooking();

                    FileManager.saveBooking(booking);
                    System.out.println("Payment successful and booking confirmed.");
                } else {
                    System.out.println("Payment cancelled.");
                }
                return;
            }
        }
        System.out.println("Booking not found.");
    }

    // 4. generateTicket()
    public void generateTicket(String bookingRef) {
        for (Booking booking : bookings) {
            if (booking.getBookingReference().equalsIgnoreCase(bookingRef)) {
                System.out.println("TICKET: ");
                booking.generateItinerary();
                return;
            }
        }
        System.out.println("Booking not found.");
    }
}
