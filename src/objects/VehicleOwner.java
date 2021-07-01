package objects;

import objects.vehicles.Car;
import objects.vehicles.Motorcycle;
import objects.vehicles.Supercar;
import objects.vehicles.Truck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VehicleOwner {
    int id;
    ArrayList<Vehicle> vehiclesList = new ArrayList<>();
    String registrationNumber;

    public VehicleOwner(){

    }

    public void calculateVehicleTax(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVehicleList(String[] vehicles) {
        for(String vehicle:vehicles){
            vehiclesList.add(getVehicle(vehicle));
        }
    }

    private Vehicle getVehicle(String vehicleID){
        Vehicle vehicle = null;
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/registeredVehicles/" + vehicleID))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] data = line.split(",");
                if ("type".equals(data[0])) {
                    switch (data[1]) {
                        case "car" -> vehicle = new Car(vehicleID);
                        case "supercar" -> vehicle = new Supercar(vehicleID);
                        case "truck" -> vehicle = new Truck(vehicleID);
                        case "motorcycle" -> vehicle = new Motorcycle(vehicleID);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vehicle;
    }

//    protected void fillData(){
//        try (BufferedReader br = new BufferedReader(new FileReader("src/data/users/" + this.firstName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(",");
//                switch (data[0]){
//                    case "firstname" -> this.firstName = data[1];
//                    case "lastname" -> this.lastName = data[1];
//                    case "age" -> this.age = Integer.parseInt(data[1]);
//                    case "type" -> this.accType = data[1];
//                    case "owns" -> fillVehicles(data[1].split(";"));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
