package helpers;

import data.constants.SearchOptions;
import objects.Vehicle;
import objects.VehicleOwner;
import objects.owners.Company;
import objects.owners.Person;
import objects.vehicles.Car;
import objects.vehicles.Motorcycle;
import objects.vehicles.Supercar;
import objects.vehicles.Truck;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;


public class DataManager {

    private HashMap<Integer, Vehicle> carDB, motorcycleDB,truckDB,superCarDB;
    private HashMap<Integer, Vehicle> allVehiclesDB = new HashMap<>();
    private File vehiclesDB;

    public DataManager(){
        initVehicleDB(Vehicle.TYPE_CAR);
        initVehicleDB(Vehicle.TYPE_MOTORCYCLE);
        initVehicleDB(Vehicle.TYPE_TRUCK);
        initVehicleDB(Vehicle.TYPE_SUPERCAR);
        allVehiclesDB.putAll(carDB);
        allVehiclesDB.putAll(motorcycleDB);
        allVehiclesDB.putAll(truckDB);
        allVehiclesDB.putAll(superCarDB);

    }

    //region Getters

    public HashMap<Integer, Vehicle> getCarDB() {
        return carDB;
    }
    public HashMap<Integer, Vehicle> getMotorcycleDB() {
        return motorcycleDB;
    }
    public HashMap<Integer, Vehicle> getTruckDB() {
        return truckDB;
    }
    public HashMap<Integer, Vehicle> getSuperCarDB() {
        return superCarDB;
    }
    public HashMap<Integer, Vehicle> getAllVehiclesDB() {
        return allVehiclesDB;
    }
    //endregion


    public void addVehicle(Vehicle vehicle){
        switch (vehicle.getType()){
            case Vehicle.TYPE_CAR -> {
                carDB.put(vehicle.getId(),vehicle);
                allVehiclesDB.put(vehicle.getId(), vehicle);
            }
            case Vehicle.TYPE_MOTORCYCLE -> {
                motorcycleDB.put(vehicle.getId(),vehicle);
                allVehiclesDB.put(vehicle.getId(), vehicle);
            }
            case Vehicle.TYPE_TRUCK -> {
                truckDB.put(vehicle.getId(),vehicle);
                allVehiclesDB.put(vehicle.getId(), vehicle);
            }
            case Vehicle.TYPE_SUPERCAR -> {
                superCarDB.put(vehicle.getId(),vehicle);
                allVehiclesDB.put(vehicle.getId(), vehicle);
            }
        }
    }

    private void initVehicleDB(String vehicleType){

        HashMap<Integer,Vehicle> vehicles = new HashMap<>();
        HashMap<String,String> vehicleInfo = new HashMap<>();

        Vehicle vehicle = null;
        VehicleOwner owner = null;
        String[] keys = null;

        vehiclesDB = new File("src/data/registeredVehicles/csv" + "/" + vehicleType + "DB.csv");

        if (!vehiclesDB.exists()){
            try{
                vehiclesDB.getParentFile().mkdirs();
                vehiclesDB.createNewFile();
                BufferedWriter buf = new BufferedWriter(new FileWriter(vehiclesDB, true));
                for(String key:SearchOptions.DB_VEHICLES_KEYS){
                    if(!key.equals(Vehicle.OWNER_TYPE)){
                        buf.append(key).append(",");
                    }else{
                        buf.append(key);
                        buf.newLine();
                    }

                }
//                buf.append("id,brand,model,owner,type,numberplate,firstregistrationdate,horsepower,seats,price,taxrate,ownertype\n");
                buf.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }


        try (BufferedReader br = new BufferedReader(new FileReader(vehiclesDB))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                //Sets keys
                if(data[0].equals(Vehicle.ID)){
                    keys = data;
                    continue;
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
                String ownerType = vehicleInfo.get(Vehicle.OWNER_TYPE);
                switch (ownerType){
                    case VehicleOwner.TYPE_PERSON -> owner = new Person(vehicleInfo.get(Vehicle.OWNER).split(" ")[0]);
                    case VehicleOwner.TYPE_COMPANY -> owner = new Company(vehicleInfo.get(Vehicle.OWNER));
                    case VehicleOwner.TYPE_ADMIN -> owner = new Person(vehicleInfo.get(Vehicle.OWNER).split(" ")[0]);
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

        switch (vehicleType){
            case Vehicle.TYPE_CAR -> this.carDB =vehicles;
            case Vehicle.TYPE_MOTORCYCLE -> this.motorcycleDB = vehicles;
            case Vehicle.TYPE_TRUCK -> this.truckDB = vehicles;
            case Vehicle.TYPE_SUPERCAR -> this.superCarDB = vehicles;
            default -> System.out.println("Vehicles database initialization failed :(");
        }

    }

    private void initUsersDB(){

    }


    public HashMap<Integer,Vehicle> getSearchResults(String searchText,String vehicleType,String searchType){
        HashMap<Integer, Vehicle> searchDB = null;
        HashMap<Integer, Vehicle> results = new HashMap<>();

        //Setting in which database search gonna happen
        switch (vehicleType){
            case SearchOptions.DB_CARS -> searchDB = carDB;
            case SearchOptions.DB_MOTORCYCLE -> searchDB = motorcycleDB;
            case SearchOptions.DB_TRUCK -> searchDB = truckDB;
            case SearchOptions.DB_SUPERCAR -> searchDB = superCarDB;
            case SearchOptions.DB_ALL_VEHICLES -> searchDB = allVehiclesDB;
        }
        if(searchDB != null) {
            searchDB.forEach((vehicleID, vehicle) -> {
                if (vehicle.getInfo().get(searchType).equals(searchText)) {
                    results.put(vehicleID, vehicle);
                }
            });
        }else{
            System.out.println("There was error in vehicle type selection :(");
        }

        if(!results.isEmpty()){
            return results;
        }else{
            return null;
        }

    }

    public void save(Vehicle vehicle){

       this.vehiclesDB = new File("src/data/registeredVehicles/csv" + "/" + vehicle.getType() + "DB.csv");

        if(vehicle.getId() == Integer.MIN_VALUE){
            vehicle.setId(allVehiclesDB.size());
        }

        addVehicle(vehicle);

        String vehicleInfo = String.format("%s,%s,%s,%s %s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                vehicle.getId(),vehicle.getBrand(),vehicle.getModel(),vehicle.getOwner().getOwnerInfo()[0],vehicle.getOwner().getOwnerInfo()[1],
                vehicle.getType(), vehicle.getNumberPlate(),vehicle.getFirstRegistrationDate(),vehicle.getHorsePower(),vehicle.getSeats(),
                vehicle.getPrice(),vehicle.getTaxRate(),vehicle.getOwner().getType());
        try{
            BufferedWriter buf = new BufferedWriter(new FileWriter(vehiclesDB, true));
            buf.append(vehicleInfo);
            buf.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }


}
