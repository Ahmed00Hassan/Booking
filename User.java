
package flight_booking_system;

import java.util.Scanner;


public abstract class User {

Scanner input =new Scanner(System.in);
protected String userId;
protected String userName;
protected String name;
protected String email;
protected String password;
protected boolean contactInfo;
static User loggedInUser;

    public User() {
    }


    public User(String userId, String userName, String name, String email, String password, boolean contactInfo) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactInfo = contactInfo;
    }

    public void setPassword() {
        while (true) {
            System.out.print("Enter your password (with letters and numbers) ");
            String pass = input.nextLine();
            if (pass.length() <6){
                System.out.println("password must be at leats 6 characters");
                continue;

            }else{ 
                this.password = pass;
                    return;
            }
        }
    }


    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    protected String getName() {
        return this.name;
    }

    protected String getEmail() {
        return this.email;
    }

    protected String getPassword() {
        return this.password;
    }

    public boolean isContactInfo() {
        return contactInfo;
    }



    public String toFileString(){
        return userId +","+userName +","+name+","+email+","+password+","+contactInfo;
    }


    public abstract void login();

    public abstract void logout();
}
