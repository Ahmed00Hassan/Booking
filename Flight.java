
package flight_booking_system;

public class Flight {


    private String flightID;
    
    private String airline;
    
    private String source;//مكان الاقلاع
    
    private String destination;
    
    private String departureTime;
    
    private String arrivalTime;
    
    private int totalSeats;
    
    private int availableSeats;
    
    private int availableSeats_economy;
    
    private int availableSeats_business;
    
    private int availableSeats_firstclass;
    
    private double economyPrice;
    
    private double businessPrice;
    
    private double firstClassPrice;

    
    public Flight(String flightID, String airline, String source, String destination,
                    String departureTime, String arrivalTime,
                    int totalSeats, int availableSeats, double economyPrice,
                    double businessPrice, double firstClassPrice) {
        
        this.flightID = flightID;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
        this.firstClassPrice = firstClassPrice;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getFlightID() {
        return flightID;
    }

    public String getAirline() {
        return airline;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getAvailableSeats_economy() {
        return availableSeats_economy;
    }

    public int getAvailableSeats_business() {
        return availableSeats_business;
    }

    public int getAvailableSeats_firstclass() {
        return availableSeats_firstclass;
    }

    public double getEconomyPrice() {
        return economyPrice;
    }

    public double getBusinessPrice() {
        return businessPrice;
    }

    public double getFirstClassPrice() {
        return firstClassPrice;
    }
    

    public String toFileString() {
        return flightID + "," + airline + "," + source + "," + destination + "," +
               departureTime + "," + arrivalTime + "," + totalSeats + "," +
               availableSeats + "," + economyPrice + "," +
               businessPrice + "," + firstClassPrice;
    }
    public static Flight fromFileString(String data){
        System.out.println("reading: ");
        String[] parts = data.split(",",-1);
        if (parts.length<11) {
            System.out.println("invalid flight data");
            return null;
        }
                
                String flightID = parts[0];
                String airline = parts[1];
                String source = parts[2];
                String destination = parts[3];
                String departureTime = parts[4];
                String arrivalTime = parts[5];
                int totalSeats = Integer.parseInt(parts[6]);
                int availableSeats = Integer.parseInt(parts[7]);
                double economyPrice = Double.parseDouble(parts[8]);
                double businessPrice = Double.parseDouble(parts[9]);
                double firstClassPrice = Double.parseDouble(parts[10]);
                
                
        return new Flight(flightID, airline, source, destination, departureTime,
                            arrivalTime, totalSeats, availableSeats, economyPrice,
                                businessPrice, firstClassPrice);
    }
    
    
    
    
    public double calculatePrice(String seatType) { 
    if(seatType.equals("economy "))
        return this.economyPrice;
    
    else if(seatType.equals("business "))    
        return this.businessPrice;
    
    else if(seatType.equals("first class"))
        return this.firstClassPrice;
    else{
        System.out.println("enter a valid type : ");
        return 0;
    } 
        
    }
    
    public boolean checkAvailability(String seatType){
    if(seatType.equals("economy")){
        return availableSeats_economy>0;
    }
    else if(seatType.equals("business")){
        return availableSeats_business>0;    
    }   
    else if(seatType.equals("first class")){
        return   availableSeats_firstclass>0;
    }   
    else{
        {
            System.out.println("enter a valid type");
            return false;
        }
        
    }
        
    }
    
    
    
    public boolean reserveSeat(String seatType) { // booking seat
    if (availableSeats_economy<=0&&seatType.equals("economy")){
        System.out.println("there is no available seats ");
        return false;
    }    
    else if (availableSeats_business<=0&&seatType.equals("business")){
        System.out.println("there is no available seats ");
        return false;
    }    
    else if (availableSeats_firstclass<=0&&seatType.equals("first class")){
        System.out.println("there is no available seats ");
        return false;
    }    
        
    
    else if(seatType.equals("economy")&&availableSeats_economy>0){
        System.out.println("reserved successfully ");
        availableSeats_economy--;
        
        return false; 
    }
    else if(availableSeats_business>0&&seatType.equals("business")){
        System.out.println("reserved successfully ");
        availableSeats_business--;
        return false; 
 }
    else if(availableSeats_firstclass>0&&seatType.equals("first class")){
        System.out.println("reserved successfully ");
        availableSeats--;
        return false; 
    }
    else{
        System.out.println("enter a valid type !");
        return false;
    }
        
    }
    
    public void displayDetails() {
        System.out.println("flight id "+this.flightID);
        System.out.println("airline "+this.airline);
        System.out.println("source "+this.source);
        System.out.println("destination "+this.destination);
        System.out.println("departure time "+this.departureTime);
        System.out.println("arrival time "+this.arrivalTime);
        System.out.println("available seats "+this.availableSeats);
        System.out.println("economy Price "+this.economyPrice);
        System.out.println("business price "+this.businessPrice);
        System.out.println("first class price "+this.firstClassPrice);
        
    }    

}
