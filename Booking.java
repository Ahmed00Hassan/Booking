
package flight_booking_system;

import java.util.ArrayList;

public class Booking {

    private String bookingReference;
    private String customer;
    private int flight;
    private ArrayList<Passenger> passengers;
    private int seatSelections;
    private boolean status;
    private String paymentStatus;


    public Booking(String bookingReference, String customer, int flight, 
                  ArrayList<Passenger> passengers, int seatSelections, 
                  boolean status, String paymentStatus) {
        this.bookingReference = bookingReference;
        this.customer = customer;
        this.flight = flight;
        this.passengers = passengers;
        this.seatSelections = seatSelections;
        this.status = status;
        this.paymentStatus = paymentStatus;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public String getCustomer() {
        return customer;
    }

    public int getFlight() {
        return flight;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public int getSeatSelections() {
        return seatSelections;
    }

    public boolean isStatus() {
        return status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String toFileString() {
        StringBuilder passengerData = new StringBuilder();
        for (Passenger p : passengers) {
            passengerData.append(p.toFileString()).append(";");
        }
        if (!passengers.isEmpty() && passengerData.length() > 0) {
            passengerData.setLength(passengerData.length() - 1); // Safer truncation
        }
        
        return String.join(",",
            bookingReference,
            customer,
            Integer.toString(flight),
            Integer.toString(seatSelections),
            Boolean.toString(status),
            paymentStatus,
            passengerData.toString()
        );
    }
    
    public static Booking fromFileString(String data) {
        if (data == null || data.isEmpty()) {
            System.out.println("Empty booking data");
            return null;
        }

        String[] parts = data.split(",", -1);
        if (parts.length < 6) {
            System.out.println("Invalid booking data - only " + parts.length + " fields found");
            return null;
        }

        try {
            ArrayList<Passenger> passengers = new ArrayList<>();
            if (!parts[6].isEmpty()) {
                String[] passengerEntries = parts[6].split(";");
                for (String entry : passengerEntries) {
                    if (!entry.isEmpty()) {
                        Passenger p = Passenger.fromFileString(entry);
                        if (p != null) passengers.add(p);
                    }
                }
            }

            return new Booking(
                parts[0],  // bookingReference
                parts[1],  // customer
                Integer.parseInt(parts[2].trim()),  // flight
                passengers,
                Integer.parseInt(parts[3].trim()),  // seatSelections
                Boolean.parseBoolean(parts[4].trim()),  // status
                parts[5]   // paymentStatus
            );
        } catch (Exception e) {
            System.out.println("Error parsing booking: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    
    public void addPassenger(Passenger passenger){   
    
    }
    
    public void calculateTotalPrice(){
    
    }
    
    
    public void confirmBooking(){   
    
    }
    
    
    public void cancelBooking(){   
    
    }
    
    
    public void generateItinerary(){
        
    }
    
    
}

