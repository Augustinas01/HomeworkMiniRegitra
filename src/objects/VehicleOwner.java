package objects;

import objects.vehicles.Car;
import objects.vehicles.Motorcycle;
import objects.vehicles.Supercar;
import objects.vehicles.Truck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class VehicleOwner {

    public static final String TYPE_PERSON = "Person";
    public static final String TYPE_COMPANY = "Company";
    public static final String TYPE_ADMIN = "admin";
    public static final String ID = "owner id";
    public static final String TYPE = "owner type";
    public static final String FIRST_NAME = "first name";
    public static final String LAST_NAME = "last name";
    public static final String COMPANY_TITLE = "company title";
    public static final String COMPANY_ID = "company id";
    public static final String COMPANY_TAX_DEDUCTION = "tax deduction";
    public static final String OWNED_VEHICLES_IDS = "owned ids";

    int id;
    ArrayList<Vehicle> vehiclesList = new ArrayList<>();
    HashMap<Integer, Vehicle> vehiclesMap = new HashMap<>();
    Set<Integer> ownedVehicles;
    String registrationNumber,type;

    public VehicleOwner(String type){
        this.type = type;
        this.id = Integer.MIN_VALUE;

    }

    //region Getters
    public String[] getOwnerInfo(){
        return null;
    }
    public HashMap<Integer, Vehicle> getVehiclesMap() {
        return vehiclesMap;
    }
    public String getType(){
        return this.type;
    }
    public int getId() {
        return id;
    }
    public String getCompanyTitle() {return null;}
    public String getCompanyId() {
        return null;
    }
    public BigDecimal getCompanyTaxDeduction(){
        return null;
    }
    public Set<Integer> getOwnedIds(){
        return vehiclesMap.keySet();
    }
    //endregion

    //region Setters


    public void setInfo(HashMap<String,String> info){

    }
    public void setOwnedVehiclesIds(String ids){
        System.out.println("Setting Ids in VehicleOwner");
    }


    //endregion

    public void addVehicleToMap(Vehicle vehicle){
        this.vehiclesMap.put(vehicle.getId(),vehicle);
    }

    public void addVehicleToMap(int vehicleId){
        this.vehiclesMap.put(vehicleId,getVehicle(String.valueOf(vehicleId)));
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
            vehiclesMap.put(getVehicle(vehicle).getId(),getVehicle(vehicle));
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
