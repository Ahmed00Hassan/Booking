
package flight_booking_system;

    import java.util.*;

public class Flight_Booking_system {

    

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        User loggedInUser = null;
        BookingSystem system = new BookingSystem();
        Agent agent =new Agent();
        Customer customer =new Customer();
        Payment payment = new Payment();

        System.out.println("\tWelcome to the Flight Booking System");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        // ===== Sign Up =====
        if (option == 2) {
            System.out.println("Sign up as:");
            System.out.println("1. Customer");
            System.out.println("2. Agent");
            System.out.println("3. Administrator");
            System.out.print("Choose user type: ");
            int userType = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Full Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            String password;
            while (true) {
                System.out.print("Enter Password (min 6 letters): ");
                password = scanner.nextLine();
                if (password.length() >= 6)
                    break;
                System.out.println("Too short. Try again.");
            }

            System.out.print("Do you want to receive updates? (true/false): ");
            boolean contactInfo = Boolean.parseBoolean(scanner.nextLine());

            User newUser = null;

            switch (userType) {
                case 1:
                    newUser = new Customer(id, username, name, email, password, contactInfo);
                    break;
                case 2:
                    System.out.print("Enter commission: ");
                    double commission = scanner.nextDouble();
                    scanner.nextLine();
                    newUser = new Agent(id, username, name, email, password, contactInfo, commission);
                    break;
                case 3:
                    System.out.print("Enter security level: ");
                    String level = scanner.nextLine();
                    newUser = new Administrator(id, username, name, email, password, contactInfo, "ADMIN_" + id, level);
                    break;
                default:
                    System.out.println("Invalid user type.");
            }

            if (newUser != null) {
                FileManager.saveUser(newUser);
                System.out.println("Sign up successful!");
                loggedInUser = newUser;
            }
        }

        // ===== Login =====
        while (loggedInUser == null) {
            System.out.println("Login as:");
            System.out.println("1. Customer");
            System.out.println("2. Agent");
            System.out.println("3. Administrator");
            System.out.print("Enter your choice: ");
            int type = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String role = "";
            switch (type) {
                case 1: role = "Customer";
                break;
                
                case 2: role = "Agent";
                break;
                
                case 3: role = "Administrator"; 
                break;
                
                default: role = "";
            }

            loggedInUser = FileManager.authenticateUser(email, password, role);
            if (loggedInUser == null) {
                System.out.println("Invalid login. Try again.");
            }
        }

        System.out.println("Welcome " + loggedInUser.getClass().getSimpleName());

        // ===== Main Menu =====
        int choice;
        do {
            System.out.println("\nMenu:");
            if (loggedInUser instanceof Customer) {
                System.out.println("1. Search Flight");
                System.out.println("2. Book Flight");
                System.out.println("3. Make Payment");
                System.out.println("4. Cancel Booking");
                
            } else if (loggedInUser instanceof Administrator) {
                System.out.println("1. Add Flight");
                System.out.println("2. Remove Flight");
                System.out.println("3. View Flights");
                System.out.println("4. Generate Reports");
                
            } else if (loggedInUser instanceof Agent) {
                System.out.println("1. View Flights");
                System.out.println("2. Book for Customer");
            }
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (loggedInUser instanceof Customer) {
                        System.out.print("Enter source: ");
                        String source = scanner.nextLine();
                        System.out.print("Enter destination: ");
                        String destination = scanner.nextLine();
                        Flight found = BookingSystem.searchFlight(source, destination);
                        if (found != null) {
                            System.out.println("Flight found: " + found);
                        } else {
                            System.out.println("No flight found.");
                        }
                    } else if (loggedInUser instanceof Administrator) {
                        System.out.print("Enter flight ID: ");
                        String flightID = scanner.nextLine();
                        System.out.print("Enter airline name: ");
                        String airline = scanner.nextLine();
                        System.out.print("Enter source: ");
                        String source = scanner.nextLine();
                        System.out.print("Enter destination: ");
                        String destination = scanner.nextLine();
                        System.out.print("Enter departure time: ");
                        String departureTime = scanner.nextLine();
                        System.out.print("Enter arrival time: ");
                        String arrivalTime = scanner.nextLine();
                        System.out.print("Enter total number of seats: ");
                        int totalSeats = scanner.nextInt();
                        System.out.print("Enter available seats: ");
                        int availableSeats = scanner.nextInt();
                        System.out.print("Enter number of economy seats: ");
                        int economySeats = scanner.nextInt();
                        System.out.print("Enter number of business seats: ");
                        int businessSeats = scanner.nextInt();
                        System.out.print("Enter number of first class seats: ");
                        int firstClassSeats = scanner.nextInt();
                        System.out.print("Enter price for economy class: ");
                        double economyPrice = scanner.nextDouble();
                        System.out.print("Enter price for business class: ");
                        double businessPrice = scanner.nextDouble();
                        System.out.print("Enter price for first class: ");
                        double firstClassPrice = scanner.nextDouble();
                        scanner.nextLine();

                        Flight newFlight = new Flight(flightID, airline, source, destination, departureTime, arrivalTime,
                                totalSeats, availableSeats, economySeats, businessSeats, firstClassSeats,
                                economyPrice, businessPrice, firstClassPrice);

                        FileManager.addFlight(newFlight);
                        System.out.println("Flight added successfully.");
                    } else if (loggedInUser instanceof Agent) {
                        agent.manageFlights();
                    }
                    break;

                case 2:
                    if (loggedInUser instanceof Customer) {
                        customer.creatBooking();
                    } else if (loggedInUser instanceof Administrator) {
                        System.out.print("Enter Flight ID to remove: ");
                        String fid = scanner.nextLine();
                        FileManager.removeFlight(fid);
                    } else if (loggedInUser instanceof Agent) {
                        agent.createBookingForCustomer();
                    }
                    break;

                case 3:
                    if (loggedInUser instanceof Customer) {
                        System.out.print("Enter source: ");
                        String source = scanner.nextLine();
                        System.out.print("Enter destination: ");
                        String destination = scanner.nextLine();
                        System.out.print("Enter seat type (economy / business / first): ");
                        String seatType = scanner.nextLine().toLowerCase();
                        System.out.print("Enter number of seats: ");
                        int numberOfSeats = scanner.nextInt();
                        scanner.nextLine();
                        payment.processPayment(source, destination, seatType, numberOfSeats);
                    }
                    break;

                case 4:
                    if (loggedInUser instanceof Customer) {
                        customer.cancelBooking();
                    } else if (loggedInUser instanceof Administrator) {
                        agent.generateReports();
                    }
                    break;

                case 0:
                    if (loggedInUser instanceof Customer) {
                        ((Customer) loggedInUser).logout();
                    } else if (loggedInUser instanceof Agent) {
                        ((Agent) loggedInUser).logout();
                    } else if (loggedInUser instanceof Administrator) {
                        ((Administrator) loggedInUser).logout();
                    }
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }
}



    
