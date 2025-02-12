import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Car class
class Car {
    private String make;
    private String model;
    private String licensePlate;
    private double rentalPricePerDay;
    private boolean isAvailable;

    public Car(String make, String model, String licensePlate, double rentalPricePerDay) {
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.rentalPricePerDay = rentalPricePerDay;
        this.isAvailable = true; // Default is available
    }

    // Getters and Setters
    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public String getCarInfo() {
        return make + " " + model + " (" + licensePlate + ")";
    }
}

// Customer class
class Customer {
    private String name;
    private String customerId;
    private String phoneNumber;
    private List<RentalTransaction> rentalHistory;

    public Customer(String name, String customerId, String phoneNumber) {
        this.name = name;
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.rentalHistory = new ArrayList<>();
    }

    // Rent car and add rental to history
    public void rentCar(RentalTransaction transaction) {
        rentalHistory.add(transaction);
    }

    // Rental history getter
    public List<RentalTransaction> getRentalHistory() {
        return rentalHistory;
    }

    // Display customer info
    public String getCustomerInfo() {
        return name + " (ID: " + customerId + ")";
    }
}

// RentalTransaction class
class RentalTransaction {
    private Car car;
    private Customer customer;
    private LocalDate rentalDate;
    private int rentalDuration; // In days
    private double totalCost;

    public RentalTransaction(Car car, Customer customer, LocalDate rentalDate, int rentalDuration) {
        this.car = car;
        this.customer = customer;
        this.rentalDate = rentalDate;
        this.rentalDuration = rentalDuration;
        this.totalCost = car.getRentalPricePerDay() * rentalDuration;
    }

    // Complete the transaction
    public void completeTransaction() {
        car.rent();
        customer.rentCar(this);
    }

    // End transaction
    public void endTransaction() {
        car.returnCar();
    }

    // Display transaction details
    public String getTransactionDetails() {
        return car.getCarInfo() + " rented by " + customer.getCustomerInfo() +
                " for " + rentalDuration + " days. Total cost: KSH" + totalCost;
    }

    // Get total cost
    public double getTotalCost() {
        return totalCost;
    }
}

// RentalAgency class
class RentalAgency {
    private List<Car> cars;
    private List<Customer> customers;

    public RentalAgency() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
    }

    // Add car to the rental agency
    public void addCar(Car car) {
        cars.add(car);
    }

    // Add customer to the rental agency
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Find available car
    public Car findAvailableCar() {
        for (Car car : cars) {
            if (car.isAvailable()) {
                return car;
            }
        }
        return null;
    }

    // Rent car to customer
    public RentalTransaction rentCar(Customer customer, LocalDate rentalDate, int rentalDuration) {
        Car availableCar = findAvailableCar();
        if (availableCar != null) {
            RentalTransaction transaction = new RentalTransaction(availableCar, customer, rentalDate, rentalDuration);
            transaction.completeTransaction();
            return transaction;
        } else {
            System.out.println("No available cars at the moment.");
            return null;
        }
    }

    // Return car for customer
    public void returnCar(Customer customer, RentalTransaction transaction) {
        transaction.endTransaction();
    }

    // Show rental history of a customer
    public void showRentalHistory(Customer customer) {
        System.out.println("\nRental History for " + customer.getCustomerInfo() + ":");
        for (RentalTransaction transaction : customer.getRentalHistory()) {
            System.out.println(transaction.getTransactionDetails());
        }
    }

    // Show car availability
    public void showCarAvailability() {
        System.out.println("\nCar Availability:");
        for (Car car : cars) {
            System.out.println(car.getCarInfo() + " available: " + car.isAvailable());
        }
    }
}

// Main class
public class CarRentalSystem {
    public static void main(String[] args) {
        // Create rental agency
        RentalAgency agency = new RentalAgency();

        // Create and add cars to the agency
        Car car1 = new Car("Toyota", "Camry", "KBV 132D", 57000.0);
        Car car2 = new Car("Honda", "Civic", "KYZ124W", 4000.0);
        Car car3 = new Car("Ford", "Focus", "KBT123E", 4500.0);
        agency.addCar(car1);
        agency.addCar(car2);
        agency.addCar(car3);

        // Create and add customers to the agency
        Customer customer1 = new Customer("John Mwai", "C001", "0795-456-890");
        Customer customer2 = new Customer("Jane Dongi", "C002", "0712-654-210");
        agency.addCustomer(customer1);
        agency.addCustomer(customer2);

        // Rent cars to customers
        System.out.println("Renting cars...\n");

        // Rent car to customer 1 (John Doe)
        RentalTransaction transaction1 = agency.rentCar(customer1, LocalDate.now(), 5);
        if (transaction1 != null) {
            System.out.println(transaction1.getTransactionDetails());
        }

        // Rent car to customer 2 (Jane Doe)
        RentalTransaction transaction2 = agency.rentCar(customer2, LocalDate.now(), 3);
        if (transaction2 != null) {
            System.out.println(transaction2.getTransactionDetails());
        }

        // Show rental history for both customers
        agency.showRentalHistory(customer1);
        agency.showRentalHistory(customer2);

        // Show car availability before and after returning cars
        agency.showCarAvailability();

        // Return car for Customer 1
        agency.returnCar(customer1, transaction1);

        // Show car availability after returning car
        System.out.println("\nReturning car for Customer 1...");
        agency.showCarAvailability();
    }
}

