package objects;

import objects.vehicles.Car;
import objects.vehicles.Motorcycle;
import objects.vehicles.Supercar;
import objects.vehicles.Truck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VehicleOwner {

    public static final String TYPE_PERSON = "Person";
    public static final String TYPE_COMPANY = "Company";
    public static final String TYPE_ADMIN = "admin";

    int id;
    ArrayList<Vehicle> vehiclesList = new ArrayList<>();
    HashMap<String, Vehicle> vehiclesMap = new HashMap<>();
    String registrationNumber,type;

    public VehicleOwner(String type){
        this.type = type;

    }

    public String[] getOwnerInfo(){
        return null;
    }

    public HashMap<String, Vehicle> getVehiclesMap() {
        return vehiclesMap;
    }
    public String getType(){
        return this.type;
    }


    public void addVehicleToMap(Vehicle vehicle){
        this.vehiclesMap.put(String.valueOf(vehicle.getId()),vehicle);
    }

    public void addVehicleToMap(int vehicleId){
        this.vehiclesMap.put(String.valueOf(vehicleId),getVehicle(String.valueOf(vehicleId)));
    }

    public void removeVehicle(String vehicleId){
        this.vehiclesMap.remove(vehicleId);
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

    public void setVehiclesMap(String[] vehicles) {
        for(String vehicle:vehicles){
            vehiclesMap.put(vehicle,getVehicle(vehicle));
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
                        case "car" -> vehicle = new Car(this, Vehicle.TYPE_CAR);
                        case "supercar" -> vehicle = new Supercar(this);
                        case "truck" -> vehicle = new Truck(this);
                        case "motorcycle" -> vehicle = new Motorcycle(this);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        vehicle.fillData(vehicleID);
        return vehicle;
    }

    public void save(){

    }


}
