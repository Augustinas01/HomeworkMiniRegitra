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
    public static final String AGE ="age";
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

    public String getFirstName(){
        return null;
    }

    public String getLastName(){
        return null;
    }

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
    public String getCompanyID() {
        return null;
    }
    public BigDecimal getCompanyTaxDeduction(){
        return null;
    }
    public Set<Integer> getOwnedIds(){
        return vehiclesMap.keySet();
    }
    public int getAge(){
        return Integer.MIN_VALUE;
    }
    //endregion

    //region Setters


    public void setInfo(HashMap<String,String> info){

    }
    public void setOwnedVehiclesIds(String ids){
        System.out.println("Setting Ids in VehicleOwner");
    }


    //endregion

    public void addVehicleToSet(int vehicleID){
        if(this.ownedVehicles == null){
            this.ownedVehicles = Set.of(vehicleID);
        }else{
            this.ownedVehicles.add(vehicleID);
        }

    }


    public void calculateVehicleTax(){

    }

    public void setId(int id) {
        this.id = id;
    }


}
