package objects;

import objects.owners.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Vehicle {
    int id,horsePower,seats;
    String brand,model,numberPlate;
    LocalDate firstRegistrationDate;
    BigDecimal price,taxRate;
    VehicleOwner owner;

    public Vehicle(String vehicleID){
        fillData(vehicleID);
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
    //endregion

    public LinkedHashMap<String,Object> getInfo(){
        LinkedHashMap<String, Object> info = new LinkedHashMap<>();
        info.put("brand", brand);
        info.put("model",model);
        info.put("horsePower",horsePower);
        info.put("seats",seats);
        info.put("numberPlate",numberPlate);
        info.put("price",price);
        info.put("id",id);
        return info;
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
                    case "numberplate" -> this.numberPlate = data[1];
                    case "price" -> this.price = new BigDecimal(data[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
