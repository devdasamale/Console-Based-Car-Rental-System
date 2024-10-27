import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car{
    private String carId;
    private String brand;
    private String model;
    private Double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, Double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    public String getCarId(){
        return carId;
    }

    public String getBrand() {
        return brand;
    }


    public String getModel() {
        return model;
    }


    public Double getBasePricePerDay() {
        return basePricePerDay;
    }



    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public  double calculatePrice(int rentalDays){
        return basePricePerDay * rentalDays;
    }

    public void rent(){
        isAvailable = false;
    }

    public void returnCar(){
        isAvailable = true;
    }


}

class Customer{
    private String customerName;
    private String customerId;
    private String customerPhone;
    private String customerAddress;
    private String panId;

    public Customer(String customerName, String customerId, String customerPhone, String customerAddress, String panId) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.panId = panId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPanId() {
        return panId;
    }

    public void setPanId(String panId) {
        this.panId = panId;
    }
}


class Rental{
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

}

class CarRentalSystem{
    private List<Car> cars = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private  List<Rental> rentals = new ArrayList<>();

    public void addCar(Car car){
        cars.add(car);
    }

    public void displayAvailableCars(){
        System.out.println("---------------------------------------------------------");
        System.out.println("Car Id \t Car Brand \t Car Model \t Car Price Per Day");

        for(Car car : cars){
            if (car.isAvailable()){
                System.out.println("---------------------------------------------------------");
                System.out.println(car.getCarId() + "\t" + car.getBrand() + "\t\t" + car.getModel() + "\t\t\t" +  car.getBasePricePerDay());
            }
        }
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else{
            System.out.println("Car is not available for rent !...");
        }
    }


    public void returnCar(Car car){
        car.returnCar();
        Rental rentalToRemove = null;
        for(Rental rental : rentals){
            if(rental.getCar() == car){
                rentalToRemove = rental;
                break;
            }
        }

        if(rentalToRemove != null){
            rentals.remove(rentalToRemove);
        } else{
            System.out.println("Car was not rented !...");
        }
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println();
            System.out.println("*** Welcome to Car Rental System ***");
            System.out.println("1. Available Cars");
            System.out.println("2. Rent a Car");
            System.out.println("3. Return a Car");
            System.out.println("4. Exit/Logout");

            String carId;

            int choice = sc.nextInt();

            switch (choice){
                // Case 1
                case 1:
                    displayAvailableCars();
                    break;
                // Case 2
                case 2:
                    System.out.println();
                    System.out.println("----- Rent a car -----");

                    System.out.println("---------------------------------------------------------");
                    System.out.println("Car Id \t Car Brand \t Car Model \t Car Price Per Day");

                    for(Car car : cars){
                        if (car.isAvailable()){
//                            System.out.println("---------------------------------------------------------");
                            System.out.println(car.getCarId() + "\t" + car.getBrand() + "\t\t" + car.getModel() + "\t\t\t" +  car.getBasePricePerDay());
                        }
                    }
                    System.out.println("---------------------------------------------------------");

                    System.out.println("\nEnter Car Id you want to rent: ");
                    carId = sc.next();

                    System.out.println("Enter Number of days for rent");
                    int rentalDays = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter your name: ");
                    String customerName = sc.nextLine();

                    System.out.println("Enter your Contact Number: ");
                    String customerPhone = sc.next();
                    sc.nextLine();

                    System.out.println("Enter Address: ");
                    String customerAddress = sc.nextLine();

                    System.out.println("Enter PAN Id: ");
                    String panId = sc.next();

                    Customer newCustomer = new Customer(customerName, "CUS" + (customers.size() + 1), customerPhone, customerAddress, panId );
                    addCustomer(newCustomer);

                    Car selectedCar = null;
                    for(Car car : cars){
                        if(car.getCarId().equalsIgnoreCase(carId) && car.isAvailable()){
                            selectedCar = car;
                            break;
                        }
                    }

                    if(selectedCar != null){
                        double totalPrice = selectedCar.calculatePrice(rentalDays);

                        System.out.println("\n ~~~~~~~~ Rental Information ~~~~~~~~");

                        System.out.println("Customer Id : " + newCustomer.getCustomerId());
                        System.out.println("Customer Name : " + newCustomer.getCustomerName());
                        System.out.println("Customer Address : " + newCustomer.getCustomerAddress());
                        System.out.println("Customer Contact No. : " + newCustomer.getCustomerPhone());
                        System.out.println("Car Id: " + selectedCar.getCarId());
                        System.out.println("Car : " + selectedCar.getBrand() + " " + selectedCar.getModel());
                        System.out.println("Rental Days : " + rentalDays);
                        System.out.println("Total Price : ₹ " + totalPrice);

                        System.out.println("\nConfirm rental (Y/N): ");
                        String confirm = sc.next();

                        if(confirm.equalsIgnoreCase("Y")){
                            rentCar(selectedCar, newCustomer, rentalDays);
                            System.out.println("\nCar Rented Successfully...");
                        } else{
                            System.out.println("\n Rental Canceled");
                        }
                    }else {
                        System.out.println("\n Invalid car selection or not available for rent !..");
                    }
                    break;

                // Case 3
                case 3:
                    System.out.println("----- Return a car -----");
                    System.out.println("Enter Car Id to return");
                    carId = sc.next();

                    Car carToReturn = null;
                    for(Car car : cars){
                        if(car.getCarId().equalsIgnoreCase(carId) && !car.isAvailable()){
                            carToReturn = car;
                            break;
                        }
                    }

                    if(carToReturn != null){
                        Customer customer = null;
                        for (Rental rental: rentals){
                            if(rental.getCar() == carToReturn){
                                customer = rental.getCustomer();
                                break;
                            }
                        }

                        if(customer != null){
                            returnCar(carToReturn);
                            System.out.println("Car returned successfully by " + customer.getCustomerName());
                        } else{
                            System.out.println("Car was not rented or rental information is missing...");
                        }
                    }else{
                        System.err.println("Invalid Car id or car is not rented !...");
                    }
                    break;
                case 4:
                    System.out.println("\nThank you for using the Car Rental System !...");
                    break;
                default:
                    System.err.println("Invalid Choice !");
                    break;
            }
            System.out.println();
        }
    }

}


public class Main {
    static CarRentalSystem rentalSystem = new CarRentalSystem();

    static {
        Car car1 = new Car("C001", "Toyota", "Innova", 3000.0);
        Car car2 = new Car("C002", "Toy0ta", "Crysta", 3000.0);
        Car car3 = new Car("C003", "Tata", "Safari", 2700.0);
        Car car4 = new Car("C004", "Hyundai", "Creta", 2800.0);
        Car car5 = new Car("C005", "Tata", "Harrier", 2700.0);
        Car car6 = new Car("C006", "Honda", "City", 3000.0);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addCar(car5);
        rentalSystem.addCar(car6);

    }

    public static void main(String[] args) {
        rentalSystem.menu();
    }
}