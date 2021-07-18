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
//        fillData(firstName);
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
