
package flight_booking_system;
import java.util.*;
import java.io.*;

public class Agent extends User {
    
    private int agentId;
    private String department;
    private double commission;

    public Agent() {
    }

    public Agent(int agentId, String department, double commission) {
        this.agentId = agentId;
        this.department = department;
        this.commission = commission;
    }

    public Agent(int agentId, String department, double commission, String userId, String userName, String name, String email, String password, boolean contactInfo) {
        super(userId, userName, name, email, password, contactInfo);
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
         User user =FileManager.authenticateUser(email, password, "customer");
         
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
         
        
         if (email !=null && loggedInUser.getEmail().equals(email)&&
                 password !=null&&loggedInUser.getPassword().equals(password)) {
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
        Customer newCustomer = new Customer();
        newCustomer.creatBooking();
    }
     
}
