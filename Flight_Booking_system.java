
package flight_booking_system;


public class Flight_Booking_system {


    public static void main(String[] args) {

        Customer c1 =new Customer();
//        Flight f1 =new Flight("0","0","0","0","0","0",0,0,200,550,1.0);
//        FileManager.saveFlight(f1);
//        c1.customerInfo();
//        FileManager.saveUser(c1);
//            c1.searchflights();
//c1.login();
//c1.logout();
        System.out.println("all users:");
        for (User u: FileManager.loadUsers()) {
            System.out.println(u.toFileString());
        }
        for (Flight f: FileManager.loadFlights()) {
            System.out.println(f.toFileString());
        }
        for (Booking b: FileManager.loadBookings()) {
            System.out.println(b.toFileString());
        }
        for (Passenger p: FileManager.loadPassengers()) {
            System.out.println(p.toFileString());
        }        
    }
}

    
