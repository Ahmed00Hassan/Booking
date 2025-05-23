
package flight_booking_system;
import java.util.*;
import java.io.*;

public class Agent extends User {
    
    private String agentId;
    private String department;
    private double commission;

    public Agent() {
    }

    public Agent(String agentId, String department, double commission) {
        this.agentId = agentId;
        this.department = department;
        this.commission = commission;
    }

    public Agent(String userId, String username, String name, String email, String password, boolean contactInfo, double commission) {
        super(userId, username, name, email, password, contactInfo);
        this.agentId = agentId;
        this.department = department;
        this.commission = commission;
    }

    public Agent(String userId, String userName, String name, String email, String password, boolean contactInfo) {
        super(userId, userName, name, email, password, contactInfo);
    }



    public static Agent fromFileString(String line) {
        String[] parts = line.split(",");
        return new Agent(
            parts[0],
            parts[1],
            parts[2],
            parts[3],
            parts[4],
            Boolean.parseBoolean(parts[5])
        );
    }
    public String toString(){
        return getUserId()+","+getUserName()+","+getName()+","+getEmail();
    }


    @Override
     public void login(){
         System.out.print("Enter your email: ");
         String email =input.nextLine();
         System.out.print("Enter your password: ");
         String password =input.nextLine();
         User user =FileManager.authenticateUser(email, password, "agent");
         
        if (user !=null) {
            System.out.println("welcome back "+user.getName());
            this.loggedInUser =user;
            
        }else System.out.println("invalid credentials for "+email);
     }
    @Override
     public void logout(){
         System.out.print("Enter your email: ");
         String email =input.nextLine();
         System.out.print("Enter your password: ");
         String password =input.nextLine();
         
        
         if (email !=null && loggedInUser.getEmail().equals(email)) {
             System.out.println("Goodbye "+loggedInUser.getName());
             
        }else System.out.println(email+" not active yet!");
     }
    
    public void manageFlights() {
        
        List<Flight> flights = FileManager.loadFlights();
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }

        System.out.println("Available Flights:");
        for (Flight f : flights) {
            f.displayDetails();
            System.out.println("-------------------");
        }
    }

     
    public void createBookingForCustomer(){
        System.out.print("Enter customer username: ");
        String username = input.nextLine();

        Customer customer = null;
        for (User u : FileManager.loadUsers()) {
            if (u.getClass().getSimpleName().equalsIgnoreCase("Customer")&&
                    u.getUserName().equalsIgnoreCase(username)) {
                customer = (Customer) u;
                break;
            }
        }

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        Customer newCustomer = new Customer();
        newCustomer.creatBooking();
    }
    
    public void modifyBooking(){
        System.out.print("Enter booking reference: ");
        String ref =input.nextLine();
        
        List<Booking> bookings = FileManager.loadBookings();
        for(Booking b : bookings){
             if (b.getBookingReference().equalsIgnoreCase(ref)) {
                 System.out.print("new status (pending/confirmed/cancelled): ");
                 b.setStatus(input.nextLine());
                 System.out.print("new paument status (paid/pending/failed): ");
                 b.setPaymentStatus(input.nextLine());
                 
                 try    (PrintWriter writer =new PrintWriter(new FileWriter(FileManager.BOOKINGS_FILE))){
                     
                     for(Booking bk : bookings){
                         writer.println(bk.toFileString());
                     }
                 } catch(IOException e){
                     System.out.println("error saving booking");
                 }
                 
                 System.out.println("booking update"); return;
            }
        }
        System.out.println("booking not found!");
    }
    
    
    public void generateReports() {
        List<Booking> bookings = FileManager.loadBookings();
        double totalSales = 0;

        for (Booking b : bookings) {
            totalSales += b.calculateTotalPrice();
        }

        System.out.println("Total bookings: " + bookings.size());
        System.out.println("Total sales: " + totalSales);
        System.out.println("Your commission: " + (totalSales * commission / 100));
    }

     
}
