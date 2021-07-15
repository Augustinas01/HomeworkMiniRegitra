package helpers;

import objects.Vehicle;
import objects.VehicleOwner;
import objects.owners.Company;
import objects.owners.Person;
import objects.vehicles.Car;
import objects.vehicles.Motorcycle;
import objects.vehicles.Supercar;
import objects.vehicles.Truck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class DataManager {
    public static final String OWNER_TYPE = "ownertype";


    public HashMap<Integer, Vehicle> getVehicles(String vehicleType){

        HashMap<Integer,Vehicle> vehicles = new HashMap<>();
        HashMap<String,String> vehicleInfo = new HashMap<>();

        Vehicle vehicle = null;
        VehicleOwner owner = null;

        try (BufferedReader br = new BufferedReader(new FileReader("src/data/registeredVehicles/csv/" + vehicleType + "DB.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                String[] keys = null;

                //Sets keys
                if(data[0].equals(Vehicle.ID)){
                    keys = data;
                }
                int i = 0;
                //Vehicle info to HashMap
                if(keys != null) {
                    for (String key : keys) {
                        vehicleInfo.put(key, data[i]);
                        i++;
                    }
                }
                //Creates owner from vehicle
                switch (vehicleInfo.get(OWNER_TYPE)){
                    case VehicleOwner.TYPE_PERSON -> owner = new Person(vehicleInfo.get(Vehicle.OWNER.split(" ")[0]));
                    case VehicleOwner.TYPE_COMPANY -> owner = new Company(vehicleInfo.get(Vehicle.OWNER));
                    case VehicleOwner.TYPE_ADMIN -> owner = new Person(vehicleInfo.get(Vehicle.OWNER.split(" ")[0]));
                }
                //Creates vehicle
                switch (vehicleInfo.get(Vehicle.TYPE)){
                    case Vehicle.TYPE_CAR -> {
                        vehicle = new Car(owner, Vehicle.TYPE_CAR);
                        vehicle.setInfo(vehicleInfo);
                    }
                    case Vehicle.TYPE_MOTORCYCLE -> {
                        vehicle = new Motorcycle(owner);
                        vehicle.setInfo(vehicleInfo);
                    }
                    case Vehicle.TYPE_TRUCK -> {
                        vehicle = new Truck(owner);
                        vehicle.setInfo(vehicleInfo);
                    }
                    case Vehicle.TYPE_SUPERCAR -> {
                        vehicle = new Supercar(owner);
                        vehicle.setInfo(vehicleInfo);
                    }
                }
                if(vehicle != null) {
                    vehicles.put(vehicle.getId(), vehicle);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vehicles;

    }


}
