
package flight_booking_system;

import java.util.*;
import java.io.*;


public class Administrator extends User {
    
    Scanner input=new Scanner(System.in);
    private String adminId;
    private String securityLevel;

    public Administrator() {
    }

    public Administrator(String adminId, String securityLevel) {
        this.adminId = adminId;
        this.securityLevel = securityLevel;
    }

    public Administrator(String userId, String username, String name, String email, String password, boolean contactInfo, String adminId, String level) {
        super(userId, username, name, email, password, contactInfo);
        this.adminId = adminId;
        this.securityLevel = level;
    }
    

    public Administrator(String userId, String userName, String name, String email, String password, boolean contactInfo) {
        super(userId, userName, name, email, password, contactInfo);
    }

    public static Administrator fromFileString(String line) {
        String[] parts = line.split(",");
        return new Administrator(
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
         User user =FileManager.authenticateUser(email, password, "administrator");
         
        if (user !=null) {
            System.out.println("welcome back "+this.getName());
            
        }else System.out.println("invalid credentials for "+email);
     }
    @Override
     public void logout(){
         System.out.print("Enter your email: ");
         String email =input.nextLine();         
        
         if (email !=null && this.getEmail().equals(email)) {
             System.out.println("Goodbye "+this.getName());
             
        }else System.out.println(email+" not active yet!");
     }
    
    public void createUser(){
        System.out.print("user type(customer/agent/administrator): ");
        String type =input.nextLine();
        if (type.equalsIgnoreCase("customer")) {
            User newUser =new Customer();
            FileManager.saveUser(newUser);
            System.out.println("user "+newUser.getName()+" created successfully");
        
        }else if(type.equalsIgnoreCase("agent")){
            User newUser =new Agent();
            FileManager.saveUser(newUser);
            System.out.println("user "+newUser.getName()+" created successfully");
        
        }else if (type.equalsIgnoreCase("administrator")) {
            User newUser =new Administrator();
            FileManager.saveUser(newUser);
            System.out.println("user "+newUser.getName()+" created successfully");
        
        }else
            System.out.println("invalid user type!");
        
    }
    
    public void modifySystemSettings() {
        System.out.println("Modify Settings:");
        System.out.println("1. Clear Users File");
        System.out.println("2. Clear Flights File");
        int choice = Integer.parseInt(input.nextLine());

        switch (choice) {
            case 1:
                FileManager.clearFile(FileManager.USERS_FILE);
                System.out.println("Users file cleared.");
                break;
            case 2:
                FileManager.clearFile(FileManager.FLIGHTS_FILE);
                System.out.println("Flights file cleared.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    
    public void viewSystemLogs(){
        System.out.println("all users:");
        for (User u: FileManager.loadUsers()) {
            System.out.println(u.toFileString());
        }
    }
    
    public void manageUserAccess() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter email of the user to delete: ");
        String email = input.nextLine();

        List<User> users = FileManager.loadUsers();
        boolean found = false;

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getEmail().equalsIgnoreCase(email)) {
                users.remove(i);
                found = true;
                break;
            }
        }
        
        if (found) {
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(FileManager.USERS_FILE));
                for (User u : users) {
                    String type = u.getClass().getSimpleName();
                    writer.println(type + "," + u.toFileString());
                }
                writer.close();
                System.out.println("User deleted successfully.");
            } catch (IOException e) {
                System.out.println("Error while updating users file.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

}
