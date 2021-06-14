package com.ss.utopia.main;

import com.ss.utopia.domain.*;
import com.ss.utopia.service.AdminServices;
import com.ss.utopia.service.TravelerServices;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    Scanner scanner = new Scanner(System.in);
    AdminServices adminServices = new AdminServices();
    TravelerServices travelerServices = new TravelerServices();

    /**
     * manage flights section
     */
    public void printManageFlights() throws SQLException {
        System.out.println("----------------");
        System.out.println("Manage Flights:");
        System.out.println("1) Add flight:");
        System.out.println("2) Update flights:");
        System.out.println("3) Delete flights:");
        System.out.println("4) Read flights:");
        System.out.println("5) Previous menu:");
        this.handleFlights();
    }

    public void handleFlights() throws SQLException {
        String ans = scanner.nextLine().trim();
        switch (ans) {
            case "1":
                this.addFlight();
                scanner.nextLine();
                printManageFlights();
                break;

            case "2":
                updateFlight();
                scanner.nextLine();
                printManageFlights();
                break;

            case "3":
                this.deleteFlight();
                scanner.nextLine();
                printManageFlights();
                break;

            case "4":
                readFlights();
                break;
            case "5":
                printAdminMenu();
                break;
        }
    }

    public void addFlight() throws SQLException {
        System.out.println("----------------");
        System.out.println("Adding a new flight:");

        System.out.println("Please enter route id");
        int routeId = scanner.nextInt();

        System.out.println("Please enter airplane id");
        int airplaneId = scanner.nextInt();

        System.out.println("Please enter departure time, format: YYYY-MM-DD HH:MI:SS ");
        scanner.nextLine();
        Timestamp departure = Timestamp.valueOf(scanner.nextLine().trim());


        System.out.println("Please enter the amount of reserved seats: ");
        int reservedSeats = scanner.nextInt();

        System.out.println("Please enter seat price: ");
        float seatPrice = scanner.nextFloat();
        adminServices.addFlight(new Flight(-1, routeId, airplaneId, departure, reservedSeats, seatPrice));
        System.out.println("Flight added.");
    }

    public void deleteFlight() throws SQLException {
        System.out.println("----------------");
        System.out.println("Deleting a flight:");
        System.out.println("Please enter the flight ID");
        int id = scanner.nextInt();
        adminServices.deleteFlight(id);
        System.out.println("Flight deleted");
    }

    public void updateFlight() throws SQLException {
        System.out.println("----------------");
        System.out.println("Updating Flight Information: ");
        System.out.println("Please enter the flight id you would like to update");
        int id = scanner.nextInt();
        System.out.println("Please enter the new parameters to update:");
        System.out.println("Please enter route id");
        int routeId = scanner.nextInt();

        System.out.println("Please enter airplane id");
        int airplaneId = scanner.nextInt();

        System.out.println("Please enter departure time, format: YYYY-MM-DD HH:MI:SS ");
        scanner.nextLine();
        Timestamp departure = Timestamp.valueOf(scanner.nextLine().trim());

        System.out.println("Please enter the amount of reserved seats: ");
        int reservedSeats = scanner.nextInt();

        System.out.println("Please enter seat price: ");
        float seatPrice = scanner.nextFloat();
        adminServices.updateFlight(new Flight(id, routeId, airplaneId, departure, reservedSeats, seatPrice));
        System.out.println("Flight updated.");
    }

    public void readFlights() throws SQLException {
        System.out.println("----------------");
        System.out.println("Printing all flights");
        List<Flight> flights = adminServices.readAllFlights();
        for (Flight flight : flights) {
            System.out.println("----------------");
            Route route = adminServices.readRouteById(flight.getRouteId());
            System.out.println("Origin: " + route.getOrigin() + ", Destination: " + route.getDestination());
            System.out.println(flight.toString());
        }
        printManageFlights();
    }

    /**
     * manage seats section
     */

    public void printManageSeats() throws SQLException {
        System.out.println("----------------");
        System.out.println("Manage Seats:");
        System.out.println("1) Add seats:");
        System.out.println("2) Update seats:");
        System.out.println("3) Delete seats:");
        System.out.println("4) Read seats:");
        System.out.println("5) Previous menu:");
        this.handleSeats();
    }

    public void handleSeats() throws SQLException {
        String ans = scanner.nextLine().trim();
        switch (ans) {
            case "1":
                this.addSeats();
                scanner.nextLine();
                this.printManageSeats();
                break;

            case "2":
                this.updateSeats();
                scanner.nextLine();
                this.printManageSeats();
                break;

            case "3":
                this.deleteSeats();
                scanner.nextLine();
                this.printManageSeats();
                break;

            case "4":
                readSeats();
                this.printManageSeats();
                break;
            case "5":
                this.printAdminMenu();
                break;
        }
    }

    public void addSeats() throws SQLException {
        System.out.println("----------------");
        System.out.println("Which airplane types' seat capacity would you like to add to?");
        int id = scanner.nextInt();
        System.out.println("How many seats would you like to add?");
        int seats = scanner.nextInt();
        adminServices.addSeats(id, seats);
        System.out.println("Seats added.");
    }

    public void updateSeats() throws SQLException {
        System.out.println("----------------");
        System.out.println("Which airplane types' seat capacity would you like to change?");
        int id = scanner.nextInt();
        System.out.println("How many seats should it have?");
        int seats = scanner.nextInt();
        adminServices.updateSeats(id, seats);
        System.out.println("Seats updated.");
    }

    public void deleteSeats() throws SQLException {
        System.out.println("----------------");
        System.out.println("Which airplane types' seat capacity would you like to delete from?");
        int id = scanner.nextInt();
        System.out.println("How many seats would you like to delete?");
        int seats = scanner.nextInt();
        adminServices.deleteSeats(id, seats);
        System.out.println("Seats deleted.");
    }

    public void readSeats() throws SQLException {
        System.out.println("----------------");
        System.out.println("Printing all airplane types and seat capacities");
        List<AirplaneType> types = adminServices.readAllAirplaneTypes();
        int index = 1;

        for (AirplaneType type : types) {
            System.out.println(index + ") " + type.toString());
            System.out.println("----------------");
            index++;
        }
    }


    /**
     * Booking Section
     */

    public void printManageBookings() throws SQLException {
        System.out.println("----------------");
        System.out.println("Manage Booking/Passenger:");
        System.out.println("1) Add a booking:");
        System.out.println("2) Update booking:");
        System.out.println("3) Delete a booking:");
        System.out.println("4) Read bookings:");
        System.out.println("5) Previous menu:");
        this.handleBookings();
    }

    public void handleBookings() throws SQLException {
        String ans = scanner.nextLine().trim();
        switch (ans) {
            case "1":
                this.addBooking();
                //scanner.nextLine();
                this.printManageBookings();
                break;

            case "2":
                this.updateBooking();
                scanner.nextLine();
                this.printManageBookings();
                break;

            case "3":
                this.deleteBooking();
                scanner.nextLine();
                this.printManageBookings();
                break;

            case "4":
                this.readBookings();
                this.printManageBookings();
                break;
            case "5":
                this.printAdminMenu();
                break;
        }
    }

    public void addBooking() throws SQLException {
        System.out.println("----------------");
        System.out.println("Adding a Booking: ");
        System.out.println("Enter a confirmation code (make random generator later)");
        String confirmationCode = scanner.nextLine();
        int newId = adminServices.addBooking(confirmationCode);

        System.out.println("Enter passenger first name");
        String givenName = scanner.nextLine();
        System.out.println("Enter passenger last name");
        String familyName = scanner.nextLine();

        System.out.println("Enter passenger dob, format: YYYY-MM-DD");
        Date dob = Date.valueOf(scanner.nextLine());
        System.out.println("Enter passenger gender");
        String gender = scanner.nextLine();
        System.out.println("Enter passenger address");
        String address = scanner.nextLine();

        adminServices.addPassenger(new Passenger(-1, newId, givenName, familyName, dob, gender, address));

        System.out.println("Booking and Passenger added");
    }

    public void updateBooking() throws SQLException {
        System.out.println("----------------");
        System.out.println("Updating a Booking: ");
        System.out.println("Enter id of the booking to be updated");
        int bookingId = scanner.nextInt();

        System.out.println("Enter an active status (1 or 0)");
        int active = scanner.nextInt();

        System.out.println("Enter confirmation number: ");
        scanner.nextLine();
        String confirmation = scanner.nextLine().trim();
        scanner.nextLine();

        adminServices.updateBooking(new Booking(bookingId, active, confirmation));

        System.out.println("Enter the id of the passenger to be updated");
        int passengerId = scanner.nextInt();

        System.out.println("Enter passenger first name");
        String givenName = scanner.nextLine();
        System.out.println("Enter passenger last name");
        String familyName = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Enter passenger dob, format: YYYY-MM-DD");
        Date dob = Date.valueOf(scanner.nextLine());
        System.out.println("Enter passenger gender");
        String gender = scanner.nextLine();
        System.out.println("Enter passenger address");
        String address = scanner.nextLine();

        adminServices.updatePassenger(new Passenger(passengerId, bookingId, givenName, familyName, dob, gender, address));

        System.out.println("Booking and Passenger updated");
    }

    public void deleteBooking() throws SQLException {
        System.out.println("----------------");
        System.out.println("Enter the id of the booking to be deleted");
        int bookingId = scanner.nextInt();
        adminServices.deleteBooking(adminServices.readBookingById(bookingId));

        //adminServices.deletePassenger(adminServices.readPassengerByBookingId(bookingId));
        System.out.println("Booking and Passenger deleted");
    }

    public void readBookings() throws SQLException {
        System.out.println("----------------");
        System.out.println("Printing out all bookings");
        List<Booking> bookings = adminServices.readAllBookings();
        int index = 1;
        for (Booking booking : bookings) {
            System.out.println(index + ") " + booking.toString());
            System.out.println("----------------");
            index++;
        }

        System.out.println("Printing all Passengers: ");

        List<Passenger> passengers = adminServices.readAllPassengers();
        index = 1;
        for (Passenger passenger : passengers) {
            System.out.println(index + ") " + passenger.toString());
            System.out.println("----------------");
            index++;
        }
    }


    /**
     * Manage Traveler section
     */

    public void printManageTravelers() throws SQLException {
        System.out.println("----------------");
        System.out.println("Manage Travelers:");
        System.out.println("1) Add a Traveler:");
        System.out.println("2) Update Traveler:");
        System.out.println("3) Delete a Traveler:");
        System.out.println("4) Read Traveler:");
        System.out.println("5) Previous menu:");
        this.handleTravelers();
    }

    public void handleTravelers() throws SQLException {
        String ans = scanner.nextLine().trim();
        switch (ans) {
            case "1":
                this.addTraveler();
                //scanner.nextLine();
                this.printManageTravelers();
                break;

            case "2":
                this.updateTraveler();
                this.printManageTravelers();
                break;

            case "3":
                this.deleteTraveler();
                this.printManageTravelers();
                break;

            case "4":
                this.readTraveler();
                this.printManageTravelers();
                break;
            case "5":
                this.printAdminMenu();
                break;
        }
    }

    public void addTraveler() throws SQLException {
        System.out.println("----------------");
        System.out.println("Adding a Traveler: ");

        System.out.println("Enter first name");
        String givenName = scanner.nextLine().trim();
        System.out.println("Enter last name");
        String familyName = scanner.nextLine().trim();

        System.out.println("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Enter email");
        String email = scanner.nextLine().trim();
        System.out.println("Enter password");
        String password = scanner.nextLine().trim();
        System.out.println("Enter phone number");
        String phone = scanner.nextLine().trim();

        adminServices.addUser(new User(-1, 3, givenName, familyName, username, email, password, phone));

        System.out.println("New traveler added");
    }

    public void updateTraveler() throws SQLException {
        System.out.println("----------------");
        System.out.println("Updating a Booking: ");
        System.out.println("Enter id of the Traveler ot be updated");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter first name");
        String givenName = scanner.nextLine().trim();
        System.out.println("Enter last name");
        String familyName = scanner.nextLine().trim();

        System.out.println("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Enter email");
        String email = scanner.nextLine().trim();
        System.out.println("Enter password");
        String password = scanner.nextLine().trim();
        System.out.println("Enter phone number");
        String phone = scanner.nextLine().trim();

        adminServices.updateUser(new User(userId, 3, givenName, familyName, username, email, password, phone));

        System.out.println("Traveler updated");
    }

    public void deleteTraveler() throws SQLException {
        System.out.println("----------------");
        System.out.println("Enter the id of the Traveler to be deleted");
        int userId = scanner.nextInt();
        scanner.nextLine();
        adminServices.deleteUser(userId);
        System.out.println("Traveler deleted");
    }

    public void readTraveler() throws SQLException {
        System.out.println("----------------");
        System.out.println("Printing out all Travelers");
        List<User> users = adminServices.readAllTravelers();
        int index = 1;
        for (User user : users) {
            System.out.println(index + ") " + user.toString());
            System.out.println("----------------");
            index++;
        }
    }

    /**
     * Manage Employee Section
     */

    public void printManageEmployee() throws SQLException {
        System.out.println("----------------");
        System.out.println("Manage Employee:");
        System.out.println("1) Add an Employee:");
        System.out.println("2) Update Employee:");
        System.out.println("3) Delete an Employee:");
        System.out.println("4) Read Employee:");
        System.out.println("5) Previous menu:");
        this.handleEmployee();
    }

    public void handleEmployee() throws SQLException {
        String ans = scanner.nextLine().trim();
        switch (ans) {
            case "1":
                this.addEmployee();
                this.printManageEmployee();
                break;

            case "2":
                this.updateEmployee();
                this.printManageEmployee();
                break;

            case "3":
                this.deleteEmployee();
                this.printManageEmployee();
                break;

            case "4":
                this.readEmployee();
                this.printManageEmployee();
                break;
            case "5":
                this.printAdminMenu();
                break;
        }
    }

    public void addEmployee() throws SQLException {
        System.out.println("----------------");
        System.out.println("Adding an Employee: ");

        System.out.println("Enter first name");
        String givenName = scanner.nextLine().trim();
        System.out.println("Enter last name");
        String familyName = scanner.nextLine().trim();

        System.out.println("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Enter email");
        String email = scanner.nextLine().trim();
        System.out.println("Enter password");
        String password = scanner.nextLine().trim();
        System.out.println("Enter phone number");
        String phone = scanner.nextLine().trim();

        adminServices.addUser(new User(-1, 2, givenName, familyName, username, email, password, phone));

        System.out.println("New Employee added");
    }

    public void updateEmployee() throws SQLException {
        System.out.println("----------------");
        System.out.println("Updating an Employee: ");
        System.out.println("Enter id of the Employee to be updated");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter first name");
        String givenName = scanner.nextLine().trim();
        System.out.println("Enter last name");
        String familyName = scanner.nextLine().trim();

        System.out.println("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Enter email");
        String email = scanner.nextLine().trim();
        System.out.println("Enter password");
        String password = scanner.nextLine().trim();
        System.out.println("Enter phone number");
        String phone = scanner.nextLine().trim();

        adminServices.updateUser(new User(userId, 2, givenName, familyName, username, email, password, phone));

        System.out.println("Employee updated");
    }

    public void deleteEmployee() throws SQLException {
        System.out.println("----------------");
        System.out.println("Enter the id of the Employee to be deleted");
        int userId = scanner.nextInt();
        scanner.nextLine();
        adminServices.deleteUser(userId);
        System.out.println("Employee deleted");
    }

    public void readEmployee() throws SQLException {
        System.out.println("----------------");
        System.out.println("Printing out all Employees");
        List<User> users = adminServices.readAllEmployee();
        int index = 1;
        for (User user : users) {
            System.out.println(index + ") " + user.toString());
            System.out.println("----------------");
            index++;
        }
    }

    /**
     * Airport management section
     */

    public void printManageAirports() throws SQLException {
        System.out.println("----------------");
        System.out.println("Manage Airports:");
        System.out.println("1) Add an Airport:");
        System.out.println("2) Update Airport:");
        System.out.println("3) Delete an Airport:");
        System.out.println("4) Read Airports:");
        System.out.println("5) Previous menu:");
        this.handleAirports();
    }

    public void handleAirports() throws SQLException {
        String ans = scanner.nextLine().trim();
        switch (ans) {
            case "1":
                this.addAirport();
                this.printManageAirports();
                break;

            case "2":
                this.updateAirport();
                this.printManageAirports();
                break;

            case "3":
                this.deleteAirport();
                this.printManageAirports();
                break;

            case "4":
                this.readAirport();
                this.printManageAirports();
                break;
            case "5":
                this.printAdminMenu();
                break;
        }
    }

    public void addAirport() throws SQLException {
        System.out.println("----------------");
        System.out.println("Adding an Airport: ");

        System.out.println("Enter iata_id");
        String id = scanner.nextLine().trim();
        System.out.println("Enter city location");
        String city = scanner.nextLine().trim();

        adminServices.addAirport(new Airport(id, city, null));

        System.out.println("New Airport added");
    }

    public void updateAirport() throws SQLException {
        System.out.println("----------------");
        System.out.println("Updating a Booking: ");
        System.out.println("Updating an Airport: ");

        System.out.println("Enter iata_id");
        String id = scanner.nextLine().trim();
        System.out.println("Enter city location");
        String city = scanner.nextLine().trim();

        adminServices.updateAirport(new Airport(id, city, null));

        System.out.println("Airport updated");
    }

    public void deleteAirport() throws SQLException {
        System.out.println("----------------");
        System.out.println("Enter the id of the Airport to be deleted");
        String id = scanner.nextLine();
        adminServices.deleteAirport(id);
        System.out.println("Airport deleted");
    }

    public void readAirport() throws SQLException {
        System.out.println("----------------");
        System.out.println("Printing out all Airports");
        List<Airport> airports = adminServices.readAllAirport();
        int index = 1;
        for (Airport airport : airports) {
            System.out.println(index + ") " + airport.toString());
            System.out.println("----------------");
            index++;
        }
    }

    /**
     * Traveler section
     */

    public void checkMembership() throws SQLException {
        System.out.println("----------------");
        System.out.println("Please enter your membership account name");
        String username = scanner.nextLine().trim();
        if (travelerServices.checkMembership(username))
            printTravelerMenu();
        else {
            System.out.println("Not a member");
            printMainMenu();
        }
    }

    public void printTravelerMenu() {
        System.out.println("----------------");
        System.out.println("Welcome Traveler! What would you like to do?");
        System.out.println("1) Book a ticket");
        System.out.println("2) Cancel an Upcoming Trip");
        System.out.println("3) Quit to Previous");
        handleTravelerMenu();
    }

    public void handleTravelerMenu() {
        String ans = scanner.nextLine().trim();
        switch (ans) {
            case "1":
                bookTicket();
                break;

            case "2":
                cancelTrip();
                break;

            case "3":
                printMainMenu();
                break;
        }

    }

    public void bookTicket() {
        System.out.println("Pick a departure airport");

    }

    public void cancelTrip() {

    }


    /**
     * Admin section
     */
    public void printAdminMenu() throws SQLException {
        System.out.println("----------------");
        System.out.println("Welcome Administrator! What would you like to do?");
        System.out.println("1) Manage flights");
        System.out.println("2) Manage seats");
        System.out.println("3) Manage tickets and passengers");
        System.out.println("4) Manage airports");
        System.out.println("5) Manage travelers");
        System.out.println("6) Manage employees");
        System.out.println("7) Previous menu");
        handleAdminMenu();
    }

    public void handleAdminMenu() throws SQLException {
        String ans = scanner.nextLine().trim();
        switch (ans) {
            case "1":
                printManageFlights();
                break;

            case "2":
                printManageSeats();
                break;

            case "3":
                printManageBookings();
                break;

            case "4":
                printManageAirports();
                break;

            case "5":
                printManageTravelers();
                break;

            case "6":
                printManageEmployee();
                break;

            case "7":
                printMainMenu();
                break;
        }
    }

    /**
     * main menu section
     */
    public void printMainMenu() {
        System.out.println("----------------");
        System.out.println("Welcome to the Utopia Airlines Management System. Which category of a user are you?");
        System.out.println("1) Employee/Agent");
        System.out.println("2) Administrator");
        System.out.println("3) Traveler");
        try {
            handleMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleMainMenu() throws SQLException {
        String ans = scanner.nextLine().trim();
        switch (ans) {
            case "1":
                System.out.println("Not implemented yet");
                printMainMenu();
                break;

            case "2":
                printAdminMenu();
                break;

            case "3":
                checkMembership();
                break;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MainMenu m1 = new MainMenu();
        m1.printMainMenu();
    }
}
