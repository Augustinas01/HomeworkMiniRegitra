package objects.owners;

import objects.VehicleOwner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Person extends VehicleOwner {
    String firstName,lastName;
    int age;


    public Person (String firstName){
        fillData(firstName);
    }

    //region Getters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String[] getOwnerInfo(){
        return new String[]{firstName,lastName};
    }

    //endregion

    public void canOwnVehicle(){

    }

    protected void fillData(String firstName){
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/users/" + firstName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                switch (data[0]){
                    case "firstname" -> this.firstName = data[1];
                    case "lastname" -> this.lastName = data[1];
                    case "age" -> this.age = Integer.parseInt(data[1]);
//                    case "type" -> this.accType = data[1];
                    case "owns" -> super.setVehicleList(data[1].split(";"));
                    case "id" -> super.setId(Integer.parseInt(data[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
