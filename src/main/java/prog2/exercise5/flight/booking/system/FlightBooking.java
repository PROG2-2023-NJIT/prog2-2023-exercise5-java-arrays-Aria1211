package prog2.exercise5.flight.booking.system;

import java.util.UUID;
import java.util.Random;
import java.time.LocalDate;
import java.time.temporal.*;
import java.util.Scanner;

public class FlightBooking {

    private final String flightCompany = "Flights-of-Fancy";
    private String flightID;
    private String[] passengerFullName;
    private String[] passengerGender;
    private int[] passengerAge;
    private String[] ticketNumber;
    private int size;
    private String name;
    private String gender;
    private int age;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int childPassengers;
    private int adultPassengers;
    private int totalPassengers;
    private double departingTicketPrice;
    private double returnTicketPrice;
    private double totalTicketPrice;
    private BookingClass bookingClass;
    private TripType tripType;
    private TripSource tripSource;
    private TripDestination tripDestination;
    private SourceAirport sourceAirport;
    private DestinationAirport destinationAirport;
    private long daysToAdd;

    enum BookingClass {
        FIRST, BUSINESS, ECONOMY;
    }
    
    enum TripType{
        ONE_WAY, RETURN;
    }
    
    enum TripSource {
        NANJING, BEIJING, SHANGHAI, OULU, HELSINKI, PARIS;
    }
    
    enum TripDestination {
        NANJING, BEIJING, SHANGHAI, OULU, HELSINKI, PARIS;
    }
    
    enum SourceAirport {
        NANJING_LUKOU_INTERNATIONAL_AIRPORT, BEIJING_CAPITAL_INTERNATIONAL_AIRPORT, SHANGHAI_PUDONG_INTERNATIONAL_AIRPORT, OULU_AIRPORT, HELSINKI_AIRPORT, PARIS_CHARLES_DE_DAULLE_AIRPORT;
    }
    
    enum DestinationAirport {
        NANJING_LUKOU_INTERNATIONAL_AIRPORT, BEIJING_CAPITAL_INTERNATIONAL_AIRPORT, SHANGHAI_PUDONG_INTERNATIONAL_AIRPORT, OULU_AIRPORT, HELSINKI_AIRPORT, PARIS_CHARLES_DE_DAULLE_AIRPORT;
    }


    public FlightBooking(int size) {
        passengerFullName = new String[size];
        passengerGender = new String[size];
        passengerAge = new int[size];
        ticketNumber = new String[size];
    }
    
    public String getFlightCompany() {
        return flightCompany;
    }
    
    public void setPassengerFullName(int index, String passengerFullName) {
        this.passengerFullName[index] = passengerFullName;
    }
    public String getPassengerFullName(int index) {
        return passengerFullName[index];
    }

    public void setPassengerGender(int index, String passengerGender) {
        this.passengerGender[index] = passengerGender;
    }
    public String getPassengerGender(int index) {
        return passengerGender[index];
    }

    public void setPassengerAge(int index, int passengerAge) {
        this.passengerAge[index] = passengerAge;
    }
    public int getPassengerAge(int index) {
        return passengerAge[index];
    }

    Random random = new Random();
    public void setTicketNumber(int index) {
        ticketNumber[index] = null;
        if (tripType == TripType.ONE_WAY)
        {
            ticketNumber[index] = "11";
        }
        if (tripType == TripType.RETURN)
        {
            ticketNumber[index] = "22";
        }
        if (bookingClass == BookingClass.FIRST)
        {
            ticketNumber[index] = ticketNumber[index] + "F";
        }
        if (bookingClass == BookingClass.BUSINESS)
        {
            ticketNumber[index] = ticketNumber[index] + "B";
        }
        if (bookingClass == BookingClass.ECONOMY)
        {
            ticketNumber[index] = ticketNumber[index] + "E";
        }
        for (int i = 0; i < 4; i++) {
            char f = (char) ((random.nextInt(26) + 65));
            ticketNumber[index] = ticketNumber[index] + f;
        }
        if ((tripSource == TripSource.NANJING && tripDestination == TripDestination.BEIJING) || (tripSource == TripSource.BEIJING && tripDestination == TripDestination.NANJING) || (tripSource == TripSource.OULU && tripDestination == TripDestination.HELSINKI) || (tripSource == TripSource.HELSINKI && tripDestination == TripDestination.OULU))
        {
            ticketNumber[index] = ticketNumber[index] + "DOM";
        }
        else
        {
            ticketNumber[index] = ticketNumber[index] + "INT";
        }
    }
    public String getTicketNumber(int index) {
        return this.ticketNumber[index];
    }
    
    
    public void reserveTickets(int j) {
        this.size = j;
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the details of all passengers");
        for (int i = 0; i < size; i++)
        {
            System.out.println("Please enter No."+ (i+1) + " passenger's full name");
            name = s.nextLine();
            setPassengerFullName(i, name);
            System.out.println("Please enter No."+ (i+1) + " passenger's gender");
            gender = s.nextLine();
            setPassengerGender(i, gender);
            System.out.println("Please enter No."+ (i+1) + " passenger's age");
            age = s.nextInt();
            setPassengerAge(i, age);
            s.nextLine();
        }

        System.out.println("Please enter your dedparture time[yyyy-MM-dd]:");
        String sdepart = s.nextLine();
        LocalDate date1 = LocalDate.parse(sdepart);
        System.out.println("Please enter your return time[yyyy-MM-dd]:");
        String sreturn = s.nextLine();
        LocalDate date2 = LocalDate.parse(sreturn);
        setDaysToAdd(date1, date2);
        setDepartureDate(date1);
        setReturnDate(date2);

        System.out.println("Please enter the number of the child: ");
        int child = s.nextInt();
        System.out.println("Please enter the number of the adult: ");
        int adult = s.nextInt();
        setChildPassengers(child);
        setAdultPassengers(adult);
        setTotalPassengers(child, adult);
        
        s.nextLine();
        System.out.println("Please select your seat:");
        System.out.println("1. First");
        System.out.println("2. Business");
        System.out.println("3. Economy");
        String a = s.nextLine();
        System.out.print("Your choice: ");
        setBookingClass(a);
        
        System.out.println("Please select your trip type:");
        System.out.println("1. One way");
        System.out.println("2. Return");
        System.out.print("Your choice: ");
        String b = s.nextLine();
        setTripType(b);

        System.out.println("Please select your trip source:");
        System.out.println("1. Nanjing");
        System.out.println("2. Beijing");
        System.out.println("3. Oulu");
        System.out.println("4. Helsinki");
        System.out.print("Your choice: ");
        String c = s.nextLine();
        setTripSource(c);
        setSourceAirport(c);

        System.out.println("Please select your trip destination:");
        System.out.println("1. Nanjing");
        System.out.println("2. Beijing");
        System.out.println("3. Oulu");
        System.out.println("4. Helsinki");
        System.out.print("Your choice: ");
        String d = s.nextLine();
        setTripDestination(c, d);
        setDestinationAirport(c, d);

        for (int i = 0; i < size; i++)
        {
            setTicketNumber(i);
        }

        setDepartingTicketPrice(child, adult);
        setReturnTicketPrice();
        setTotalTicketPrice();

        System.out.println("\n");

        s.close();
    }


    public void setDaysToAdd(LocalDate date1, LocalDate date2) {
        daysToAdd = date1.until(date2, ChronoUnit.DAYS);
    }
    public long getDaysToAdd() {
        return daysToAdd;
    }

    public void setDepartureDate(LocalDate departurDate) {
        this.departureDate = departurDate;
	}
    public LocalDate getDepartureDate() {
        return departureDate;
    }
	
	public void setReturnDate(LocalDate returnDate) {
		if (daysToAdd < 2)
        {
            this.returnDate = departureDate.plusDays(2);
        }
        if (daysToAdd >= 2)
        {
            this.returnDate = returnDate;
        }
	}
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setFlightID() {
        this.flightID = UUID.randomUUID().toString();
    }
    public String getFlightID() {
        return flightID;
    }


    public void setChildPassengers(int childPassengers) {
        this.childPassengers = childPassengers;
    }
    public int getChildPassengers() {
        return childPassengers;
    }

    public void setAdultPassengers(int adultPassengers) {
        this.adultPassengers = adultPassengers;
    }
    public int getAdultPassengers() {
        return adultPassengers;
    }

    public void setTotalPassengers(int child, int adult) {
        this.totalPassengers = child + adult;
    }
    public int getTotalPassengers() {
        return totalPassengers;
    }

    
    public void setDepartingTicketPrice(int childPassengers, int adultPassengers) {
        if ((tripSource == TripSource.NANJING && tripDestination == TripDestination.BEIJING) || (tripSource == TripSource.BEIJING && tripDestination == TripDestination.NANJING) || (tripSource == TripSource.OULU && tripDestination == TripDestination.HELSINKI) || (tripSource == TripSource.HELSINKI && tripDestination == TripDestination.OULU))
        {
            if (bookingClass == BookingClass.FIRST)
            {
                this.departingTicketPrice = (childPassengers + adultPassengers) * (300 + 0.1*300 + 0.05*300 + 250);
            }
            if (bookingClass == BookingClass.BUSINESS)
            {
                this.departingTicketPrice = (childPassengers + adultPassengers) * (300 + 0.1*300 + 0.05*300 + 150);
            }
            if (bookingClass == BookingClass.ECONOMY)
            {
                this.departingTicketPrice = (childPassengers + adultPassengers) * (300 + 0.1*300 + 0.05*300 + 50);
            }
        }
        else
        {
            if (bookingClass == BookingClass.FIRST)
            {
                this.departingTicketPrice = (childPassengers + adultPassengers) * (300 + 0.15*300 + 0.1*300 + 250);
            }
            if (bookingClass == BookingClass.BUSINESS)
            {
                this.departingTicketPrice = (childPassengers + adultPassengers) * (300 + 0.15*300 + 0.1*300 + 150);
            }
            if (bookingClass == BookingClass.ECONOMY)
            {
                this.departingTicketPrice = (childPassengers + adultPassengers) * (300 + 0.15*300 + 0.1*300 + 50);
            }
        }
    }
    public double getDepartingTicketPrice() {
        return departingTicketPrice;
    }
    
    public void setReturnTicketPrice() {
        if ((tripSource == TripSource.NANJING && tripDestination == TripDestination.BEIJING) || (tripSource == TripSource.BEIJING && tripDestination == TripDestination.NANJING) || (tripSource == TripSource.OULU && tripDestination == TripDestination.HELSINKI) || (tripSource == TripSource.HELSINKI && tripDestination == TripDestination.OULU))
        {
            if (bookingClass == BookingClass.FIRST)
            {
                this.returnTicketPrice = (childPassengers + adultPassengers) * (300 + 0.1*300 + 0.05*300 + 250);
            }
            if (bookingClass == BookingClass.BUSINESS)
            {
                this.returnTicketPrice = (childPassengers + adultPassengers) * (300 + 0.1*300 + 0.05*300 + 150);
            }
            if (bookingClass == BookingClass.ECONOMY)
            {
                this.returnTicketPrice = (childPassengers + adultPassengers) * (300 + 0.1*300 + 0.05*300 + 50);
            }
        }
        else
        {
            if (bookingClass == BookingClass.FIRST)
            {
                this.returnTicketPrice = (childPassengers + adultPassengers) * (300 + 0.15*300 + 0.1*300 + 250);
            }
            if (bookingClass == BookingClass.BUSINESS)
            {
                this.returnTicketPrice = (childPassengers + adultPassengers) * (300 + 0.15*300 + 0.1*300 + 150);
            }
            if (bookingClass == BookingClass.ECONOMY)
            {
                this.returnTicketPrice = (childPassengers + adultPassengers) * (300 + 0.15*300 + 0.1*300 + 50);
            }  
        }
    }
    public double getReturnTicketPrice() {
        return returnTicketPrice;
    }
    
    public void setTotalTicketPrice() {
        if (tripType == TripType.ONE_WAY)
        {
            this.totalTicketPrice = this.departingTicketPrice;
        }
        if (tripType == TripType.RETURN)
        {
            this.totalTicketPrice = this.departingTicketPrice * 2;
        }
    }
    public double getTotalTicketPrice() {
        return totalTicketPrice;
    }


    public void setBookingClass(String i) {
        switch (i)
        {
            case "1":
            bookingClass = BookingClass.FIRST;
            break;
            case "2":
            bookingClass = BookingClass.BUSINESS;
            break;
            case "3":
            bookingClass = BookingClass.ECONOMY;
            break;
            default:
            System.out.println("Error!!!");
        }
    }
    public BookingClass getBookingClass() {
        return bookingClass;
    }


    public void setTripType(String i) {
        switch(i)
        {
            case "1":
            tripType = TripType.ONE_WAY;
            break;
            case "2":
            tripType = TripType.RETURN;
            break;
            default:
            System.out.println("Error!!!");
        }
    }
    public TripType getTripType() {
        return tripType;
    }


    public void setTripSource(String i) {
        switch (i) 
        {
            case "1":
            tripSource = TripSource.NANJING;
            break;
            case "2":
            tripSource = TripSource.BEIJING;
            break;
            case "3":
            tripSource = TripSource.OULU;
            break;
            case "4":
            tripSource = TripSource.HELSINKI;
            break;
            default:
            System.out.println("Error!!!");
        }
    }
    public TripSource getTripSource() {
        return tripSource;
    }

    public void setSourceAirport(String i) {
        switch (i) 
        {
            case "1":
            sourceAirport = SourceAirport.NANJING_LUKOU_INTERNATIONAL_AIRPORT;
            break;
            case "2":
            sourceAirport = SourceAirport.BEIJING_CAPITAL_INTERNATIONAL_AIRPORT;
            break;
            case "3":
            sourceAirport = SourceAirport.OULU_AIRPORT;
            break;
            case "4":
            sourceAirport = SourceAirport.HELSINKI_AIRPORT;
            break;
            default:
            System.out.println("Error!!!");
        }
    }
    public SourceAirport getSourceAirport() {
        return sourceAirport;
    }


    public void setTripDestination(String i, String j) {
        switch (i)
        {
            case "1":
            switch (j)
            {
                case "1":
                System.out.println("Error!!!");
                break;
                case "2":
                tripDestination = TripDestination.BEIJING;
                break;
                case "3":
                tripDestination = TripDestination.OULU;
                break;
                case "4":
                tripDestination = TripDestination.HELSINKI;
                break;
            }
            break;
            case "2":
            switch (j)
            {
                case "1":
                tripDestination = TripDestination.NANJING;
                break;
                case "2":
                System.out.println("Error!!!");
                break;
                case "3":
                tripDestination = TripDestination.OULU;
                break;
                case "4":
                tripDestination = TripDestination.HELSINKI;
                break;
            }
            break;
            case "3":
            switch (j)
            {
                case "1":
                tripDestination = TripDestination.NANJING;
                break;
                case "2":
                tripDestination = TripDestination.BEIJING;
                break;
                case "3":
                System.out.println("Error!!!");
                break;
                case "4":
                tripDestination = TripDestination.HELSINKI;
                break;
            }
            break;
            case "4":
            switch (j)
            {
                case "1":
                tripDestination = TripDestination.NANJING;
                break;
                case "2":
                tripDestination = TripDestination.BEIJING;
                break;
                case "3":
                tripDestination = TripDestination.OULU;
                break;
                case "4":
                System.out.println("Error!!!");
                break;
            }
            break;
        }
    }
    public TripDestination getTripDestination() {
        return tripDestination;
    }

    public void setDestinationAirport(String i, String j) {
        switch (i)
        {
            case "1":
            switch (j) 
            {
                case "1":
                System.out.println("Error!!!");
                break;
                case "2":
                destinationAirport = DestinationAirport.BEIJING_CAPITAL_INTERNATIONAL_AIRPORT;
                break;
                case "3":
                destinationAirport = DestinationAirport.OULU_AIRPORT;
                break;
                case "4":
                destinationAirport = DestinationAirport.HELSINKI_AIRPORT;
                break;
            }
            break;
            case "2":
            switch (j)
            {
                case "1":
                destinationAirport = DestinationAirport.NANJING_LUKOU_INTERNATIONAL_AIRPORT;
                break;
                case "2":
                System.out.println("Error!!!");
                break;
                case "3":
                destinationAirport = DestinationAirport.OULU_AIRPORT;
                break;
                case "4":
                destinationAirport = DestinationAirport.HELSINKI_AIRPORT;
                break;
            }
            break;
            case "3":
            switch (j)
            {
                case "1":
                destinationAirport = DestinationAirport.NANJING_LUKOU_INTERNATIONAL_AIRPORT;
                break;
                case "2":
                destinationAirport = DestinationAirport.BEIJING_CAPITAL_INTERNATIONAL_AIRPORT;
                break;
                case "3":
                System.out.println("Error!!!");
                break;
                case "4":
                destinationAirport = DestinationAirport.HELSINKI_AIRPORT;
                break;
            }
            break;
            case "4":
            switch (j)
            {
                case "1":
                destinationAirport = DestinationAirport.NANJING_LUKOU_INTERNATIONAL_AIRPORT;
                break;
                case "2":
                destinationAirport = DestinationAirport.BEIJING_CAPITAL_INTERNATIONAL_AIRPORT;
                break;
                case "3":
                destinationAirport = DestinationAirport.OULU_AIRPORT;
                break;
                case "4":
                System.out.println("Error!!!");
                break;
            }
            break;

        }
    }
    public DestinationAirport getDestinationAirport() {
        return destinationAirport;
    }

    public void displayTripDetails() {
        if (daysToAdd < 2)
        {
            System.out.println("Thank you for booking your flights with " + flightCompany);
            System.out.println("You reserved a total of " + size + " tickets.");
            System.out.println("***************************************************");
            for (int i = 0; i < size; i++)
            {
                System.out.println("Here are the trip details for Passenger No." + (i + 1));
                System.out.println("Passenger's Ticket Number: " + ticketNumber[i]);
                System.out.println("Passenger's Full Name: " + passengerFullName[i]);
                System.out.println("Passenger's Age: " + passengerAge[i]);
                System.out.println("Passenger's Gender: " + passengerGender[i]);
                System.out.println("From: " + tripSource + "(" + sourceAirport + ")");
                System.out.println("To: " + tripDestination + "(" + destinationAirport + ")");
                System.out.println("The flight departs on: " + departureDate);
                System.out.println("And the return flight is on: " + returnDate);
                System.out.println("***************************************************");
            }
            System.out.println("The total ticket price is: " + totalTicketPrice);
            System.out.println("IMPORTANT NOTICE: As per our policy, the return date was changed because it was less than two days apart from your departure date.");
        }
        else
        {
            System.out.println("Thank you for booking your flights with " + flightCompany);
            System.out.println("You reserved a total of " + size + " tickets.");
            System.out.println("***************************************************");
            for (int i = 0; i < size; i++)
            {
                System.out.println("Here are the trip details for Passenger No." + (i + 1));
                System.out.println("Passenger's Ticket Number: " + ticketNumber[i]);
                System.out.println("Passenger's Full Name: " + passengerFullName[i]);
                System.out.println("Passenger's Age: " + passengerAge[i]);
                System.out.println("Passenger's Gender: " + passengerGender[i]);
                System.out.println("From: " + tripSource + "(" + sourceAirport + ")");
                System.out.println("To: " + tripDestination + "(" + destinationAirport + ")");
                System.out.println("The flight departs on: " + departureDate);
                System.out.println("And the return flight is on: " + returnDate);
                System.out.println("***************************************************");
            }
            System.out.println("The total ticket price is: " + totalTicketPrice);
        }
    }
}
