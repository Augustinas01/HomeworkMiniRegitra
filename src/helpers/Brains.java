package helpers;


import objects.Vehicle;
import objects.VehicleOwner;
import objects.owners.Company;
import objects.owners.Person;
import view.MainWindow;
import view.VehicleEditPanel;
import view.VehiclesPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Brains implements MainWindow.MainWindowListener {

    private final MainWindow view;

    private String vehicleType,userType;

    private VehicleOwner loggedUser;

    private JPanel vehiclesGrid;

    private VehicleEditPanel vehicleEditPanel;

    private JDialog editDialog;



    public Brains(MainWindow view){
        this.view = view;

        //Set listeners
        view.setButtonsListener(e -> buttonsListener(e));
        view.setVehicleTypeRadioListener(e -> vehicleTypeRadio(e));
        view.setVehicleMakerListListener(e -> vehicleMakerList(e));
        view.setProfileSelectorListener(e -> profileSelectorRadio(e));

        view.init();
        this.vehicleType = "car";
        this.userType = "person";
        view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
        view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("BMW")));


        view.showLoginPanel();




    }



    //region Listeners
    @Override
    public void buttonsListener(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Main menu" -> view.showMainPanel();
            case "Register" -> view.showRegisterPanel();
            case "Register vehicle" -> registerVehicle();
            case "My vehicles" -> showMyVehiclesPanel();
            case "Search" -> view.showSearchPanel();
            case "Log out" -> view.showLoginPanel();
            case "Login" -> login();
            case "Sign up" -> signUp();
            default -> System.out.println(e.getActionCommand());

        }

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
    private void delButtonListener(ActionEvent e) {
        Vehicle selectedVehicle = loggedUser.getVehiclesMap().get(e.getActionCommand());

        int confirmResult = JOptionPane.showConfirmDialog(view,
                String.format("Do you really want to remove this vehicle?\n\n%s: %s\nNumber plate: %s",
                        selectedVehicle.getBrand(), selectedVehicle.getModel(), selectedVehicle.getNumberPlate()),
                "Removal confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirmResult == JOptionPane.YES_OPTION) {
            loggedUser.removeVehicle(e.getActionCommand());
            loggedUser.save();
            showMyVehiclesPanel();

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

    //region Registration
    private void registerVehicle(){
        System.out.println("Type: " + this.vehicleType);
        String vehicleMake = Objects.requireNonNull(view.getVehicleMakersJCB().getSelectedItem()).toString();
        String vehicleModel = Objects.requireNonNull(view.getVehicleModelsJCB().getSelectedItem()).toString();
        String vehicleFirstRegistrationYear = String.format(
                "%s-%s-%s",
                view.getRegistrationYearJTF().getText(),
                view.getRegistrationMonthJTF().getText(),
                view.getRegistrationDayJTF().getText());
        String vehicleHP = view.getVehicleHorsePowerJTF().getText();
        String vehiclePrice = view.getVehiclePriceJTF().getText();
        String vehicleSeatCount = view.getVehicleSeatCountJTF().getText();
        String vehicleNumberPlate = view.getNumberPlateJTF().getText();

        String vehicleInfo = String.format(
                "owner,%s;%s%n" +
                "type,%s%n" +
                "horsepower,%s%n" +
                "seats,%s%n" +
                "brand,%s%n" +
                "model,%s%n" +
                "numberplate,%s%n" +
                "firstregistrationdate,%s%n" +
                "price,%s%n" +
                "taxrate,%s%n" ,
                loggedUser.getOwnerInfo()[0],
                loggedUser.getOwnerInfo()[1],
                this.vehicleType,
                vehicleHP,
                vehicleSeatCount,
                vehicleMake,
                vehicleModel,
                vehicleNumberPlate,
                vehicleFirstRegistrationYear,
                vehiclePrice,
                null);


        File vehiclesDB = new File("src/data/registeredVehicles");
        int vehicleID = Objects.requireNonNull(vehiclesDB.listFiles()).length-1;

        if (!vehiclesDB.exists())
        {
            try
            {
                vehiclesDB.mkdirs();
                vehiclesDB.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            BufferedWriter buf = new BufferedWriter(new FileWriter(vehiclesDB + "/" + vehicleID, true));

            buf.append("id,").append(String.valueOf(vehicleID));
            buf.newLine();
            buf.append(vehicleInfo);

            buf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        loggedUser.addVehicleToMap(vehicleID);
        loggedUser.save();


    }

//    private boolean vehicleRegistrationValidation(){
//        String vehicleMake = Objects.requireNonNull(view.getVehicleMakersJCB().getSelectedItem()).toString();
//        String vehicleModel = Objects.requireNonNull(view.getVehicleModelsJCB().getSelectedItem()).toString();
//        String vehicleFirstRegistrationYear = String.format(
//                "%s-%s-%s",
//                view.getRegistrationYearJTF().getText(),
//                view.getRegistrationMonthJTF().getText(),
//                view.getRegistrationDayJTF().getText());
//        String vehicleHP = view.getVehicleHorsePowerJTF().getText();
//        String vehiclePrice = view.getVehiclePriceJTF().getText();
//        String vehicleSeatCount = view.getVehicleSeatCountJTF().getText();
//        String vehicleNumberPlate = view.getNumberPlateJTF().getText();
//
//
//    }

    //TODO optimization, user writing can be one method
    private void registerUser(){
        String age = JOptionPane.showInputDialog(view,"What is your age?","Registration",JOptionPane.PLAIN_MESSAGE);

        if (!(age == null)) {
            String confirmationText = String.format("First name: %s %n" +
                    "Last name: %s %n" +
                    "Age: %s%n",view.getLoginFirstNameJTF().getText(),view.getLoginLastNameJTF().getText(),age);
            int confirmResult = JOptionPane.showConfirmDialog(view,
                    "Is this correct?\n" + confirmationText,
                    "Registration confirmation",
                    JOptionPane.YES_NO_OPTION);
            if(confirmResult == JOptionPane.NO_OPTION){
                return;
            }
        } else {
            return;
        }

        String userInfo = String.format("firstname,%s%n" +
                "lastname,%s%n" +
                "age,%s%n" +
                "type,%s%n" +
                "owns,%s%n",view.getLoginFirstNameJTF().getText(),view.getLoginLastNameJTF().getText(),age,this.userType,null);

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

            buf.append(userInfo);

            buf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(view,"User created succesfully!");

    }

    private void registerCompany(){
        String title = JOptionPane.showInputDialog(view,"What is company title?","Registration",JOptionPane.PLAIN_MESSAGE);

        if (!(title == null)) {
            String confirmationText = String.format("Company title: %s %n" +
                    "Company no: %s %n",title,view.getCompanyIdJTF().getText());
            int confirmResult = JOptionPane.showConfirmDialog(view,
                    "Is this correct?\n" + confirmationText,
                    "Registration confirmation",
                    JOptionPane.YES_NO_OPTION);
            if(confirmResult == JOptionPane.NO_OPTION){
                return;
            }
        } else {
            return;
        }
        String companyInfo = String.format("title,%s%n" +
                "no,%s%n" +
                "owns,%s%n",title,view.getCompanyIdJTF().getText(),null);

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
            BufferedWriter buf = new BufferedWriter(new FileWriter(usersDB + "/" + view.getCompanyIdJTF().getText(), true));

            buf.append(companyInfo);

            buf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(view,"User created succesfully!");

    }
    //endregion


    private boolean authorize(String username){
        File file = new File("src/data/users");
        for(File user: Objects.requireNonNull(file.listFiles())){
            if(user.getName().equals(username)){
                return true;
            }
        }
        return false;
    }




    private void failedLogin(){
        if(this.userType.equals("person")){
            view.getLoginFirstNameJTF().setBorder(new LineBorder(Color.red));
        }
        if(this.userType.equals("company")){
            view.getCompanyIdJTF().setBorder(new LineBorder(Color.red));
        }

    }

    private void showMyVehiclesPanel(){
        if(view.getMyVehicles().isVisible()){
            view.remove(view.getMyVehicles());
        }
        view.setMyVehicles(new VehiclesPanel(view.getButtonsListener()));
        JPanel vehiclesGridBody = new JPanel(new FlowLayout());
        if(vehiclesGrid != null){
                view.getMyVehiclesBody().remove(vehiclesGridBody);
                vehiclesGridBody.remove(vehiclesGrid);
        }

        vehiclesGrid = new JPanel(new GridLayout(0,7));
//        vehiclesGrid.setPreferredSize(new Dimension(view.getWidth(), view.getHeight()/2));

//        for(Vehicle vehicle:loggedUser.getVehiclesList()){

        loggedUser.getVehiclesMap().forEach((vehicleId,vehicle) ->{



            LinkedHashMap<String,Object> vehicleInfo = vehicle.getInfo();

            vehicleInfo.forEach((info,stat) -> {
                switch (info){
                    case "brand", "model", "horsePower", "seats", "numberPlate", "price" -> {
                        JLabel cell = new JLabel(stat.toString());
                        cell.setHorizontalAlignment(SwingConstants.CENTER);
                        cell.setBorder(new LineBorder(Color.BLACK));
                        vehiclesGrid.add(cell);

                    }
                    case "id" -> vehiclesGrid.add(vehicleGridEdit(stat.toString()));

                }
            });
        });
//        }
        vehiclesGridBody.add(vehiclesGrid);



        view.getMyVehiclesBody().add(vehiclesGridBody);
        view.showMyVehiclesPanel();

    }

    public void login() {
        System.out.println("Login button pressed");
        switch (this.userType){
            case "person" -> {
                if(authorize(view.getLoginFirstNameJTF().getText())){
                    view.showMainPanel();

                    loggedUser = new Person(view.getLoginFirstNameJTF().getText());

                    view.getLoginFirstNameJTF().setText("");
                    view.getLoginLastNameJTF().setText("");
                    view.getLoginFirstNameJTF().setBorder(new LineBorder(Color.black));
                }else{
                    failedLogin();
                }
            }
            case "company" -> {
                if(authorize(view.getCompanyIdJTF().getText())){
                    view.showMainPanel();

                    loggedUser = new Company(view.getCompanyIdJTF().getText());

                    view.getLoginFirstNameJTF().setText("");
                    view.getLoginLastNameJTF().setText("");
                    view.getCompanyIdJTF().setBorder(new LineBorder(Color.black));
                }else{
                    failedLogin();
                }
            }
        }

    }

    public void signUp() {
        System.out.println("Sign up button pressed");
        if (this.userType.equals("person")){
            registerUser();
        }else{
            registerCompany();
        }
    }

    private JPanel vehicleGridEdit(String id){
        JPanel editCell = new JPanel(new GridLayout(1,2));
        JButton delButton = new JButton("DEL");
        JButton editButton = new JButton("EDIT");

        delButton.setActionCommand(id);
        delButton.addActionListener(e -> delButtonListener(e));

        editButton.setActionCommand(id);
        editButton.addActionListener(e -> editButtonListener(e));

        editCell.add(delButton);
        editCell.add(editButton);
        return editCell;
    }

    private void editButtonListener(ActionEvent e){
        editDialog = new JDialog();
        vehicleEditPanel = new VehicleEditPanel(loggedUser.getVehiclesMap().get(e.getActionCommand()).getInfo(),e1 -> editDialogListener(e1,e));
        editDialog.setTitle("Editing - " +loggedUser.getVehiclesMap().get(e.getActionCommand()).getNumberPlate());
        editDialog.setLayout(new FlowLayout());
        editDialog.add(vehicleEditPanel);
        editDialog.pack();
        editDialog.setLocationRelativeTo(view);
        editDialog.setVisible(true);
    }

    private void editDialogListener(ActionEvent e1,ActionEvent e){
        switch (e1.getActionCommand()){
            case "Cancel" -> editDialog.dispose();
            case "Change" -> {
                loggedUser.getVehiclesMap().get(e.getActionCommand()).setInfo(vehicleEditPanel.getDialogTFsStrings());
                System.out.println(loggedUser.getVehiclesMap().get(e.getActionCommand()).getBrand());
                System.out.println(loggedUser.getVehiclesMap().get(e.getActionCommand()).getNumberPlate());
                loggedUser.save();
                showMyVehiclesPanel();
                editDialog.dispose();
            }
        }

    }

//    private void changeVehicleInfo(String vehicleId){
//        System.out.println("Info changed");
//        HashMap<String,String> editedInfo = vehicleEditPanel.getDialogTFsStrings();
//        editedInfo.forEach((vehicleInfo,textFieldString) -> {
//            loggedUser.getVehiclesMap().get(vehicleId).
//        });
//    }




}
