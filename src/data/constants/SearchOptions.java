package data.constants;

import objects.Vehicle;
import objects.VehicleOwner;

public final class SearchOptions {
    public static final String BY_NUMBER_PLATE = "Number plate";
    public static final String BY_OWNER = "Owner";
    public static final String BY_BRAND = "Brand";
    public static final String[] DATABASE_SELECTION = {"All vehicles","Cars","Motorcycle","Truck","Supercar"};
    public static final String DB_ALL_VEHICLES = "All vehicles";
    public static final String DB_CARS = "Cars";
    public static final String DB_MOTORCYCLE = "Motorcycle";
    public static final String DB_TRUCK = "Truck";
    public static final String DB_SUPERCAR = "Supercar";
    public static final String[] DB_VEHICLES_KEYS = {Vehicle.ID,Vehicle.BRAND,Vehicle.MODEL,Vehicle.TYPE,Vehicle.NUMBER_PLATE,Vehicle.REGISTRATION_DATE,
                                                        Vehicle.HORSE_POWER,Vehicle.SEATS,Vehicle.PRICE,Vehicle.TAX_RATE,VehicleOwner.ID};
    public static final String[] DB_USER_KEYS = {VehicleOwner.ID,VehicleOwner.FIRST_NAME,VehicleOwner.LAST_NAME,VehicleOwner.COMPANY_TITLE,VehicleOwner.COMPANY_ID,
                                                    VehicleOwner.COMPANY_TAX_DEDUCTION, VehicleOwner.OWNED_VEHICLES_IDS,VehicleOwner.TYPE};
}
