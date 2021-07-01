package objects;

import objects.owners.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class Vehicle {
    int id,horsePower,seats;
    String brand,model,numberPlate;
    LocalDate firstRegistrationDate;
    Double price,taxRate;
    VehicleOwner owner;

    public Vehicle(String vehicleID){
        fillData(vehicleID);
    }

    public void calculateTax(){

    }

    private void fillData(String vehicleID){
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/registeredVehicles/" + vehicleID))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] data = line.split(",");
                switch (data[0]){
                    case "id" -> this.id = Integer.parseInt(data[1]);
                    case "horsepower" -> this.horsePower = Integer.parseInt(data[1]);
                    case "seats" -> this.seats = Integer.parseInt(data[1]);
                    case "brand" -> this.brand = data[1];
                    case "model" -> this.model = data[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
