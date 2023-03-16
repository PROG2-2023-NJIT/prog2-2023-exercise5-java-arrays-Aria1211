package prog2.exercise5.flight.booking.system;

/**
 * Hello world!
 *
 */
import java.util.Scanner;

public class Main 
{
    public static void main( String[] args )
    {
        Scanner s = new Scanner(System.in);
        
        System.out.println("Please enter the total number of people: ");
        int size = s.nextInt();
        FlightBooking fb = new FlightBooking(size);
        
        fb.reserveTickets(size);
        fb.displayTripDetails();
        s.close();
    }
}