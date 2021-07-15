package helpers;


import data.constants.Buttons;
import data.constants.Manufactor;
import objects.Vehicle;
import objects.VehicleOwner;
import objects.owners.Company;
import objects.owners.Person;
import objects.vehicles.Car;
import objects.vehicles.Motorcycle;
import objects.vehicles.Supercar;
import objects.vehicles.Truck;
import view.MainWindow;
import view.VehicleEditPanel;
import view.VehiclesPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

import java.util.LinkedHashMap;
import java.util.Locale;
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
        this.vehicleType = Vehicle.TYPE_CAR;
        this.userType = VehicleOwner.TYPE_PERSON;
        view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
        view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.BMW)));

        view.showLoginPanel();

    }



    //region Listeners
    @Override
    public void buttonsListener(ActionEvent e) {
        switch (e.getActionCommand()){
            case Buttons.MAIN_MENU         -> view.showMainPanel();
            case Buttons.REGISTER          -> view.showRegisterPanel();
            case Buttons.REGISTER_VEHICLE  -> registerVehicle();
            case Buttons.MY_VEHICLES       -> showMyVehiclesPanel();
            case Buttons.SEARCH            -> view.showSearchPanel();
            case Buttons.LOG_OUT           -> view.showLoginPanel();
            case Buttons.LOGIN             -> login();
            case Buttons.SIGN_UP           -> signUp();
            default                        -> System.out.println(e.getActionCommand());

        }

    }

    @Override
    public void vehicleTypeRadio(ActionEvent e) {
        System.out.println("Selected: " + e.getActionCommand());
        switch (e.getActionCommand().toLowerCase(Locale.ROOT)){
            case Vehicle.TYPE_CAR ->  {
                this.vehicleType = Vehicle.TYPE_CAR;
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.BMW)));
            }
            case Vehicle.TYPE_MOTORCYCLE -> {
                this.vehicleType = Vehicle.TYPE_MOTORCYCLE;
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.BMW)));
            }
            case Vehicle.TYPE_TRUCK -> {
                this.vehicleType = Vehicle.TYPE_TRUCK;
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.MAN)));
            }
            case Vehicle.TYPE_SUPERCAR -> {
                this.vehicleType = Vehicle.TYPE_SUPERCAR;
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.FERRARI)));
            }
        }

    }

    @Override
    public void vehicleMakerList(ActionEvent e) {
        switch (Objects.requireNonNull(view.getVehicleMakersJCB().getSelectedItem()).toString()){
            case Manufactor.BMW -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.BMW)));
            case Manufactor.MERCEDES_BENZ -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.MERCEDES_BENZ)));
            case Manufactor.PORSCHE -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.PORSCHE)));
            case Manufactor.VOLKSWAGEN -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.VOLKSWAGEN)));
            case Manufactor.MV_AGUSTA -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.MV_AGUSTA)));
            case Manufactor.SUZUKI -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.SUZUKI)));
            case Manufactor.YAMAHA -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.YAMAHA)));
            case Manufactor.FERRARI -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.FERRARI)));
            case Manufactor.KOENIGSEGG -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.KOENIGSEGG)));
            case Manufactor.LAMBORGHINI -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.LAMBORGHINI)));
            case Manufactor.MCLAREN -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.MCLAREN)));
            case Manufactor.MAN -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList( Manufactor.MAN)));
            case Manufactor.SCANIA -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList( Manufactor.SCANIA)));
            case Manufactor.VOLVO -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList(Manufactor.VOLVO)));
        }
    }

    @Override
    public void profileSelectorRadio(ActionEvent e) {
        System.out.println("Selected: " + e.getActionCommand());
        switch (e.getActionCommand()){
            case VehicleOwner.TYPE_PERSON -> {
                this.userType = VehicleOwner.TYPE_PERSON;
                if(view.getLoginCompanyLoginJP().isVisible()){
                    view.getLoginCompanyLoginJP().setVisible(false);
                    view.getLoginTextFieldsJP().remove(view.getLoginCompanyLoginJP());
                }
                view.getLoginTextFieldsJP().add(view.getLoginPersonLoginJP());
                view.getLoginPersonLoginJP().setVisible(true);
            }
            case VehicleOwner.TYPE_COMPANY -> {
                this.userType = VehicleOwner.TYPE_COMPANY;
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
        Vehicle vehicle = null;
        switch (this.vehicleType){
            case Vehicle.TYPE_CAR        -> vehicle = new Car(loggedUser,Vehicle.TYPE_CAR);
            case Vehicle.TYPE_MOTORCYCLE -> vehicle = new Motorcycle(loggedUser);
            case Vehicle.TYPE_TRUCK      -> vehicle = new Truck(loggedUser);
            case Vehicle.TYPE_SUPERCAR   -> vehicle = new Supercar(loggedUser);
        }
        if(vehicle != null) {
            vehicle.setInfo(view.getRegInfoMap());
            vehicle.save();
        }
    }


//    private boolean vehicleRegistrationValidation(){
//        view.getRegistrationYearJTF().getText()
//        String vehicleFirstRegistrationYear = String.format(
//                "%s-%s-%s",
//                ,
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
            try{
                usersDB.mkdirs();
                usersDB.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try{
            BufferedWriter buf = new BufferedWriter(new FileWriter(usersDB + "/" + view.getLoginFirstNameJTF().getText(), true));

            buf.append(userInfo);

            buf.close();
        }
        catch (IOException e){
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
        }else{
            return;
        }
        String companyInfo = String.format("title,%s%n" +
                "no,%s%n" +
                "owns,%s%n",title,view.getCompanyIdJTF().getText(),null);

        File usersDB = new File("src/data/users");
        if (!usersDB.exists())
        {
            try{
                usersDB.mkdirs();
                usersDB.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try{
            BufferedWriter buf = new BufferedWriter(new FileWriter(usersDB + "/" + view.getCompanyIdJTF().getText(), true));

            buf.append(companyInfo);

            buf.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(view,"User created succesfully!");
    }
    //endregion


    public void login() {
        System.out.println("Login button pressed");
        switch (this.userType){
            case VehicleOwner.TYPE_PERSON -> {
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
            case VehicleOwner.TYPE_COMPANY -> {
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
        if (this.userType.equals(VehicleOwner.TYPE_PERSON)){
            registerUser();
        }else{
            registerCompany();
        }
    }



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
        if(this.userType.equals(VehicleOwner.TYPE_PERSON)){
            view.getLoginFirstNameJTF().setBorder(new LineBorder(Color.red));
        }
        if(this.userType.equals(VehicleOwner.TYPE_COMPANY)){
            view.getCompanyIdJTF().setBorder(new LineBorder(Color.red));
        }

    }

    private void showVehiclesPanel(){
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

        loggedUser.getVehiclesMap().forEach((vehicleId,vehicle) ->{



            LinkedHashMap<String,Object> vehicleInfo = vehicle.getInfo();

            vehicleInfo.forEach((info,stat) -> {
                switch (info){
                    case Vehicle.BRAND, Vehicle.MODEL, Vehicle.HORSE_POWER, Vehicle.SEATS, Vehicle.NUMBER_PLATE, Vehicle.PRICE -> {
                        JLabel cell = new JLabel(stat.toString());
                        cell.setHorizontalAlignment(SwingConstants.CENTER);
                        cell.setBorder(new LineBorder(Color.BLACK));
                        vehiclesGrid.add(cell);

                    }
                    case Vehicle.ID -> vehiclesGrid.add(vehicleGridEdit(stat.toString()));

                }
            });
        });
        vehiclesGridBody.add(vehiclesGrid);

        view.getMyVehiclesBody().add(vehiclesGridBody);
        view.showMyVehiclesPanel();
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

        loggedUser.getVehiclesMap().forEach((vehicleId,vehicle) ->{



            LinkedHashMap<String,Object> vehicleInfo = vehicle.getInfo();

            vehicleInfo.forEach((info,stat) -> {
                switch (info){
                    case Vehicle.BRAND, Vehicle.MODEL, Vehicle.HORSE_POWER, Vehicle.SEATS, Vehicle.NUMBER_PLATE, Vehicle.PRICE -> {
                        JLabel cell = new JLabel(stat.toString());
                        cell.setHorizontalAlignment(SwingConstants.CENTER);
                        cell.setBorder(new LineBorder(Color.BLACK));
                        vehiclesGrid.add(cell);

                    }
                    case Vehicle.ID -> vehiclesGrid.add(vehicleGridEdit(stat.toString()));

                }
            });
        });
        vehiclesGridBody.add(vehiclesGrid);

        view.getMyVehiclesBody().add(vehiclesGridBody);
        view.showMyVehiclesPanel();
    }

    private JPanel vehicleGridEdit(String id){
        JPanel editCell = new JPanel(new GridLayout(1,2));
        JButton delButton = new JButton(Buttons.DEL);
        JButton editButton = new JButton(Buttons.EDIT);

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
            case Buttons.CANCEL -> editDialog.dispose();
            case Buttons.CHANGE -> {
                loggedUser.getVehiclesMap().get(e.getActionCommand()).setInfo(vehicleEditPanel.getDialogTFsStrings());
                loggedUser.getVehiclesMap().get(e.getActionCommand()).save();
                System.out.println(loggedUser.getVehiclesMap().get(e.getActionCommand()).getBrand());
                System.out.println(loggedUser.getVehiclesMap().get(e.getActionCommand()).getNumberPlate());
                loggedUser.save();
                showMyVehiclesPanel();
                editDialog.dispose();
            }
        }
    }

}
