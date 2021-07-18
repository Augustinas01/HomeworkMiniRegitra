package objects.owners;

import objects.VehicleOwner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Company extends VehicleOwner {
    String title, companyID;
    double taxDeduction;

    public Company(String companyID){
        super(VehicleOwner.TYPE_COMPANY);
        this.companyID = companyID;
//        fillData(companyID);
    }

//    private void fillData(String companyID){
//        try (BufferedReader br = new BufferedReader(new FileReader("src/data/users/" + companyID))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(",");
//                switch (data[0]){
//                    case "title" -> this.title = data[1];
//                    case "owns" -> super.setVehicleList(data[1].split(";"));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public String[] getOwnerInfo(){
        return new String[]{title,companyID};
    }
}
