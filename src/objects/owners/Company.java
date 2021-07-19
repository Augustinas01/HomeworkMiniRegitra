package objects.owners;

import objects.VehicleOwner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Company extends VehicleOwner {
    String title, companyID;
    double taxDeduction;

    public Company(){
        super(VehicleOwner.TYPE_COMPANY);
    }


    @Override
    public String getCompanyTitle() {
        return title;
    }

    @Override
    public String getCompanyID() {
        return companyID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    @Override
    public String[] getOwnerInfo(){
        return new String[]{title,companyID};
    }


    @Override
    public void setInfo(HashMap<String,String> info){
        info.forEach((key,stat) -> {
            switch (key){
                case ID -> super.setId(Integer.parseInt(stat));
                case COMPANY_ID -> this.companyID = stat;
                case COMPANY_TITLE -> this.title = stat;
                case OWNED_VEHICLES_IDS -> super.setOwnedVehiclesIds(stat);
            }
        });
    }
}
