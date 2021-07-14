package objects.owners;

import objects.Vehicle;
import objects.VehicleOwner;

import java.io.*;

public class Person extends VehicleOwner {
    String firstName,lastName, type;
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
                    case "type" -> this.type = data[1];
                    case "owns" -> super.setVehiclesMap(data[1].split(";"));
                    case "id" -> super.setId(Integer.parseInt(data[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(){
        String userInfo = String.format("firstname,%s%n" +
                "lastname,%s%n" +
                "age,%s%n" +
                "type,%s%n" +
                "owns,%s%n",this.firstName,this.lastName,this.age,this.type,ownedVehiclesIds());

        File usersDB = new File("src/data/users");
        if (!usersDB.exists())
        {
            try
            {
                usersDB.mkdirs();
                usersDB.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            BufferedWriter buf = new BufferedWriter(new FileWriter(usersDB + "/" + this.firstName, false));

            buf.append(userInfo);

            buf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private String ownedVehiclesIds(){
        StringBuilder ownedids = new StringBuilder();
        super.getVehiclesMap().forEach((vehicleId,vehicle) -> {
            ownedids.append(vehicleId).append(";");
        });
        return ownedids.substring(0,ownedids.lastIndexOf(";"));
    }

}
