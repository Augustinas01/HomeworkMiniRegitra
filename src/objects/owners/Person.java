package objects.owners;

import objects.Vehicle;
import objects.VehicleOwner;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class Person extends VehicleOwner {
    String firstName,lastName, type;
    int age;


    public Person (){
        super(VehicleOwner.TYPE_PERSON);
    }

    //region Getters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    @Override
    public int getAge(){
        return this.age;
    }
    @Override
    public String[] getOwnerInfo(){
        return new String[]{firstName,lastName};
    }

    //endregion

    //region Setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setInfo(HashMap<String,String> info){
        info.forEach((key,stat) -> {
            switch (key){
                case ID -> super.setId(Integer.parseInt(stat));
                case FIRST_NAME -> this.firstName = stat;
                case LAST_NAME -> this.lastName = stat;
                case OWNED_VEHICLES_IDS -> super.setOwnedVehiclesIds(stat);
            }
        });
    }


    //endregion

    public void canOwnVehicle(){

    }

}
