
package flight_booking_system;


public class Passenger {

    private String passengerId;
    private String name;
    private String passportNumber;
    private String dateOfBirth;
    private String specialRequests;

    public Passenger(String passengerId, String name, String passportNumber, String dateOfBirth, String specialRequests) {
        this.passengerId = passengerId;
        this.name = name;
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
        this.specialRequests = specialRequests;
    }
    
    public String toFileString() {
        return passengerId + "|" + name + "|" + passportNumber +"|"
                + dateOfBirth + "|" +specialRequests;
    }
    public static Passenger fromFileString(String data){
        System.out.println("reading: ");
        String[] parts =data.split("//",-1);
        if (parts.length <5) {
            System.out.println("invalid passenger data");
            return null;
        }
        
                String passengerId = parts[0];
                String name = parts[1];
                String passportNumber = parts[2];
                String dateOfBirth = parts[3];
                String specialRequests = parts[4];
                
                
        return new Passenger(passengerId, name, passportNumber, dateOfBirth, specialRequests);
    }

    
    public void updateinfo(){
        
    }


    public void getPassengerDetails(){
        System.out.println("name: "+name);
        System.out.println("passenger Id: "+passengerId);
        System.out.println("passport number: "+passportNumber);
        System.out.println("date of birth: "+dateOfBirth);
        System.out.println("special requests: "+specialRequests);
    }
}
