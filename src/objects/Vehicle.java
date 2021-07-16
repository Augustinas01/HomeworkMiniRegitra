package objects;

import objects.owners.Person;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Vehicle {

    public static final String ID = "id";
    public static final String OWNER = "owner";
    public static final String HORSE_POWER = "horsePower";
    public static final String SEATS = "seats";
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String NUMBER_PLATE = "numberPlate";
    public static final String TYPE = "type";
    public static final String[] ALL_TYPES = {"car","motorcycle","truck","supercar"};
    public static final String TYPE_CAR = "car";
    public static final String TYPE_MOTORCYCLE = "motorcycle";
    public static final String TYPE_TRUCK = "truck";
    public static final String TYPE_SUPERCAR = "supercar";
    public static final String REGISTRATION_DATE = "firstregistrationdate";
    public static final String PRICE = "price";


    int id,horsePower,seats;
    String brand,model,numberPlate,type;
    LocalDate firstRegistrationDate;
    BigDecimal price,taxRate;
    VehicleOwner owner;

    public Vehicle(VehicleOwner vehicleOwner, String type){
        this.owner = vehicleOwner;
        this.type = type;
        this.id = Integer.MIN_VALUE;
    }

    //region Getters


    public int getHorsePower() {
        return horsePower;
    }

    public int getSeats() {
        return seats;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public LocalDate getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public VehicleOwner getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public LinkedHashMap<String,Object> getInfo(){
        LinkedHashMap<String, Object> info = new LinkedHashMap<>();
        info.put(BRAND, brand);
        info.put(MODEL, model);
        info.put(HORSE_POWER, horsePower);
        info.put(SEATS, seats);
        info.put(NUMBER_PLATE, numberPlate);
        info.put(PRICE, price);
        info.put(TYPE,type);
        info.put(OWNER,String.format("%s %s",owner.getOwnerInfo()[0],owner.getOwnerInfo()[1]));
        info.put(ID, id);
        info.put(REGISTRATION_DATE, firstRegistrationDate);
        return info;
    }
    //endregion


    public void setOwner(VehicleOwner owner) {
        this.owner = owner;
    }

    public void setInfo(HashMap<String,String> infoMap){
        infoMap.forEach((key,value) ->{
            switch (key){
                case BRAND -> this.brand = value;
                case MODEL -> this.model = value;
                case HORSE_POWER -> this.horsePower = Integer.parseInt(value);
                case SEATS -> this.seats = Integer.parseInt(value);
                case NUMBER_PLATE -> this.numberPlate = value;
                case PRICE -> this.price = new BigDecimal(value);
                case REGISTRATION_DATE -> this.firstRegistrationDate = LocalDate.parse(value);
                case ID -> this.id = Integer.parseInt(value);
            }
        });

    }

    public void calculateTax(){

    }



    public void fillData(String vehicleID){
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/registeredVehicles/" + vehicleID))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] data = line.split(",");
                switch (data[0]){
                    case ID -> this.id = Integer.parseInt(data[1]);
                    case HORSE_POWER -> this.horsePower = Integer.parseInt(data[1]);
                    case SEATS -> this.seats = Integer.parseInt(data[1]);
                    case BRAND -> this.brand = data[1];
                    case MODEL -> this.model = data[1];
                    case NUMBER_PLATE -> this.numberPlate = data[1];
                    case PRICE -> this.price = new BigDecimal(data[1]);
                    case REGISTRATION_DATE -> this.firstRegistrationDate = LocalDate.parse(data[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(){
        File vehiclesDB = new File("src/data/registeredVehicles");
        if(this.id== Integer.MIN_VALUE){
            this.id = Objects.requireNonNull(vehiclesDB.listFiles()).length - 1;
        }
        String vehicleInfo = String.format(
                        "id,%s%n" +
                        "owner,%s;%s%n" +
                        "type,%s%n" +
                        "horsePower,%s%n" +
                        "seats,%s%n" +
                        "brand,%s%n" +
                        "model,%s%n" +
                        "numberPlate,%s%n" +
                        "firstregistrationdate,%s%n" +
                        "price,%s%n" +
                        "taxrate,%s%n" ,
                this.id, owner.getOwnerInfo()[0], owner.getOwnerInfo()[1], this.type,
                this.horsePower, this.seats, this.brand, this.model, this.numberPlate, this.firstRegistrationDate, this.price, this.taxRate);

        if (!vehiclesDB.exists()){
            vehiclesDB.mkdirs();
        }
        try{
            BufferedWriter buf = new BufferedWriter(new FileWriter(vehiclesDB + "/" + this.id, false));
            buf.append(vehicleInfo);
            buf.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        owner.addVehicleToMap(this);
        owner.save();
        saveToCSV();
    }

    private void saveToCSV(){

        File vehiclesDB = new File("src/data/registeredVehicles/csv");

        String vehicleInfo = String.format("%s,%s,%s,%s %s,%s," +
                                            "%s,%s,%s,%s,%s,%s,%s%n",
                this.id,this.brand,this.model,owner.getOwnerInfo()[0],owner.getOwnerInfo()[1],this.type,
                this.numberPlate,this.firstRegistrationDate.toString(),this.horsePower,this.seats,this.price,this.taxRate,owner.getType());

        if (!vehiclesDB.exists()){
            try{
                vehiclesDB.mkdirs();
                for (String type:ALL_TYPES) {
                    BufferedWriter buf = new BufferedWriter(new FileWriter(vehiclesDB + "/" + type + "DB.csv", true));
                    buf.append("id,brand,model,owner,type,numberplate,firstregistrationdate,horsepower,seats,price,taxrate\n");
                    buf.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try{
            BufferedWriter buf = new BufferedWriter(new FileWriter(vehiclesDB + "/" + this.type + "DB.csv", true));
            buf.append(vehicleInfo);
            buf.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
