package objects.owners;

import objects.VehicleOwner;

public class Company extends VehicleOwner {
    String title;
    int companyID;
    double taxDeduction;

    public Company(int companyID){
        this.companyID = companyID;
    }
}
