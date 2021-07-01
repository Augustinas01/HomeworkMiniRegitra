package helpers;


import objects.VehicleOwner;
import objects.owners.Company;
import objects.owners.Person;
import view.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

import java.util.Objects;

public class Brains implements MainWindow.MainWindowListener {

    private final MainWindow view;

    private String vehicleType,userType;

    private VehicleOwner loggedUser;



    public Brains(MainWindow view){
        this.view = view;

        //Set listeners
        view.setRegisterButtonListener(e -> goRegisterButton());
        view.setSearchButtonListener(e -> goSearchButton());
        view.setGoBackButtonListener(e -> goBackButton());
        view.setVehicleTypeRadioListener(e -> vehicleTypeRadio(e));
        view.setVehicleMakerListListener(e -> vehicleMakerList(e));
        view.setVehicleRegisterButtonListener(e -> vehicleRegisterButton());
        view.setLoginButtonListener(e -> loginButton());
        view.setSignUpButtonListener(e -> signUpButton());
        view.setProfileSelectorListener(e -> profileSelectorRadio(e));
        view.setLogOutButtonListener(e -> logOut());

        view.init();
        this.vehicleType = "car";
        this.userType = "person";
        view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
        view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("BMW")));

        view.showLoginPanel();

    }

    //region Listeners
    @Override
    public void goRegisterButton() {
        System.out.println("Register button pressed");
        view.showRegisterPanel();

    }

    @Override
    public void goSearchButton() {
        System.out.println("Search button pressed");
        view.showSearchPanel();

    }

    @Override
    public void goBackButton() {
        System.out.println("Go back button pressed");
        view.showMainPanel();
    }

    @Override
    public void logOut() {
        System.out.println("Log out button pressed");
        view.showLoginPanel();
    }

    @Override
    public void vehicleTypeRadio(ActionEvent e) {
        System.out.println("Selected: " + e.getActionCommand());
        switch (e.getActionCommand()){
            case "Car" ->  {
                this.vehicleType = "car";
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("BMW")));
            }
            case "Motorcycle" -> {
                this.vehicleType = "motorcycle";
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("BMW")));
            }
            case "Truck" -> {
                this.vehicleType = "truck";
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("MAN")));
            }
            case "Supercar" -> {
                this.vehicleType = "supercar";
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Ferrari")));
            }
        }

    }

    @Override
    public void vehicleMakerList(ActionEvent e) {
        switch (Objects.requireNonNull(view.getVehicleMakersJCB().getSelectedItem()).toString()){
            case "BMW" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("BMW")));
            case "Mercedes-Benz" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Mercedes-Benz")));
            case "Porsche" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Porsche")));
            case "Volkswagen" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Volkswagen")));
            case "MV_Agusta" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("MV_Agusta")));
            case "Suzuki" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Suzuki")));
            case "Yamaha" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Yamaha")));
            case "Ferrari" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Ferrari")));
            case "Koenigsegg" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Koenigsegg")));
            case "Lamborghini" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Lamborghini")));
            case "McLaren" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("McLaren")));
            case "MAN" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList( "MAN")));
            case "Scania" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList( "Scania")));
            case "Volvo" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Volvo")));
        }
    }

    @Override
    public void vehicleRegisterButton() {
        System.out.println("Vehicle registration button pressed");
        registerVehicle();
    }

    @Override
    public void loginButton() {
        System.out.println("Login button pressed");
        switch (this.userType){
            case "person" -> {
                if(authorize(view.getLoginFirstNameJTF().getText())){
                    view.showMainPanel();

                    loggedUser = new Person(view.getLoginFirstNameJTF().getText());

                    view.getLoginFirstNameJTF().setText("");
                    view.getLoginLastNameJTF().setText("");
                }
            }
            case "company" -> {
                if(authorize(view.getCompanyIdJTF().getText())){
                    view.showMainPanel();

                    loggedUser = new Company(Integer.parseInt(view.getCompanyIdJTF().getText()));

                    view.getLoginFirstNameJTF().setText("");
                    view.getLoginLastNameJTF().setText("");

                };
            }
        }

    }

    @Override
    public void signUpButton() {
        System.out.println("Sign up button pressed");
        registerUser();

    }

    @Override
    public void profileSelectorRadio(ActionEvent e) {
        System.out.println("Selected: " + e.getActionCommand());
        switch (e.getActionCommand()){
            case "Person" -> {
                this.userType = "person";
                if(view.getLoginCompanyLoginJP().isVisible()){
                    view.getLoginCompanyLoginJP().setVisible(false);
                    view.getLoginTextFieldsJP().remove(view.getLoginCompanyLoginJP());
                }
                view.getLoginTextFieldsJP().add(view.getLoginPersonLoginJP());
                view.getLoginPersonLoginJP().setVisible(true);
            }
            case "Company" -> {
                this.userType = "company";
                if(view.getLoginPersonLoginJP().isVisible()){
                    view.getLoginPersonLoginJP().setVisible(false);
                    view.getLoginTextFieldsJP().remove(view.getLoginPersonLoginJP());
                }
                view.getLoginTextFieldsJP().add(view.getLoginCompanyLoginJP());
                view.getLoginCompanyLoginJP().setVisible(true);
            }

        }

    }
    //endregion


    private String[] getManufactorsList(){
        File file = new File("src/data/vehicleDB/" + this.vehicleType);
        return file.list();
    }
//    private String[] getModelsList(String type){
//        File file = new File("src/data/" + type);
//        return file.list();
//    }


    private  String[] getModelsList(String manufactor) {
        String[] list = null;
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/vehicleDB/" + this.vehicleType + "/" + manufactor))) {
            String line;
            while ((line = br.readLine()) != null) {
                list = line.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    private void registerVehicle(){
        System.out.println("Type: " + this.vehicleType);
        System.out.println("Make: " + view.getVehicleMakersJCB().getSelectedItem());
        System.out.println("Model: " + view.getVehicleModelsJCB().getSelectedItem());
        System.out.println("Registration year: " +
                            view.getRegistrationYearJTF().getText() +
                            " " +
                            view.getRegistrationMonthJTF().getText() +
                            " " +
                            view.getRegistrationDayJTF().getText());
        System.out.println("Horse power: " + view.getVehicleHorsePowerJTF().getText());
        System.out.println("Price: " + view.getVehiclePriceJTF().getText());
        System.out.println("Seats: " + view.getVehicleSeatCountJTF().getText());
        System.out.println("Number plate: " + view.getNumberPlateJTF().getText());
    }


    private void registerUser(){
        System.out.println("User type: " + this.userType);
        System.out.println("First name: " + view.getLoginFirstNameJTF().getText());
        System.out.println("Last name: " + view.getLoginLastNameJTF().getText());

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
            BufferedWriter buf = new BufferedWriter(new FileWriter(usersDB + "/" + view.getLoginFirstNameJTF().getText(), true));
            buf.append(view.getLoginLastNameJTF().getText() + "," +
                    this.userType + "," +
                    null);

            buf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }


    //region Authorization

    private boolean authorize(String username){
        File file = new File("src/data/users");
        for(File user:file.listFiles()){
            if(user.getName().equals(username)){
                return true;
            }
        }
        return false;
    }
//    private boolean authorize(int companyID){
//        File file = new File("src/data/users");
//        for(File user:file.listFiles()){
//            if(user.getName().equals(String.valueOf(companyID))){
//                return true;
//            }
//        }
//        return false;
//    }
    //endregion


}
