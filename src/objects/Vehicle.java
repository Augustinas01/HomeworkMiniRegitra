package objects;

import java.time.LocalDate;

public class Vehicle {
    int id,horsePower,seats;
    String brand,model,numberPlate;
    LocalDate firstRegistrationDate;
    Double price,taxRate;
    VehicleOwner owner;

    public Vehicle(){

    }

    public void calculateTax(){

    }

}
