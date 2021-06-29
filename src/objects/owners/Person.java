package objects.owners;

import objects.VehicleOwner;

public class Person extends VehicleOwner {
    String firstName,lastName;
    int age;

    public Person (String firstName){
        this.firstName = firstName;
    }

    public void canOwnVehicle(){

    }
}
