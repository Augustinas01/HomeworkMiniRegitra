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

    public String[] getOwnerInfo(){
        return null;
    }

    public ArrayList<Vehicle> getVehiclesList() {
        return this.vehiclesList;
    }

    public void addVehicle(int vehicleId){
        this.vehiclesList.add(getVehicle(String.valueOf(vehicleId)));
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
        if(vehicleID.equals("null")){
            return null;
        }
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

    public void save(){

    }


}
