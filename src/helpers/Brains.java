package helpers;


import data.constants.Buttons;
import data.constants.Manufactor;
import data.constants.SearchOptions;
import data.constants.Titles;
import objects.Vehicle;
import objects.VehicleOwner;
import objects.vehicles.Car;
import objects.vehicles.Motorcycle;
import objects.vehicles.Supercar;
import objects.vehicles.Truck;
import view.MainWindow;
import view.SearchPanel;
import view.VehicleEditPanel;
import view.VehiclesPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

import java.util.*;

public class Brains implements MainWindow.MainWindowListener {




    private final MainWindow view;

    private String vehicleType,userType,databaseSelection;

    private VehicleOwner loggedUser;

    private JPanel vehiclesGrid;

    private VehicleEditPanel vehicleEditPanel;

    private JDialog editDialog;

    private DataManager dataManager;



    public Brains(MainWindow view){
        this.view = view;
        this.dataManager = new DataManager();
        this.loggedUser = null;

        //Set listeners
        view.setButtonsListener(e -> buttonsListener(e));
        view.setVehicleTypeRadioListener(e -> vehicleTypeRadio(e));
        view.setVehicleMakerListListener(e -> vehicleMakerList(e));
        view.setProfileSelectorListener(e -> profileSelectorRadio(e));

        view.init();
        this.vehicleType = Vehicle.TYPE_CAR;
        this.userType = VehicleOwner.TYPE_PERSON;
        databaseSelection = SearchOptions.DB_ALL_VEHICLES;
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
            case Buttons.SEARCH            -> showSearchPanel(false);
            case Buttons.LOG_OUT           -> logOut();
            case Buttons.LOGIN             -> login();
            case Buttons.SIGN_UP           -> signUp();
            case Buttons.SEARCH_VEHICLE    -> showSearchPanel(true);
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

    @Override
    public void searchDBListener(ActionEvent e) {
        switch (Objects.requireNonNull(view.getSearchDatabaseJCB().getSelectedItem()).toString()){
            case SearchOptions.DB_ALL_VEHICLES -> databaseSelection = SearchOptions.DB_ALL_VEHICLES;
            case SearchOptions.DB_CARS -> databaseSelection = SearchOptions.DB_CARS;
            case SearchOptions.DB_MOTORCYCLE -> databaseSelection = SearchOptions.DB_MOTORCYCLE;
            case SearchOptions.DB_TRUCK -> databaseSelection = SearchOptions.DB_TRUCK;
            case SearchOptions.DB_SUPERCAR -> databaseSelection = SearchOptions.DB_SUPERCAR;
        }
    }

    private void delButtonListener(ActionEvent e) {
        LinkedHashMap<String, Object> vehicleInfo = dataManager.getAllVehiclesDB().get(Integer.parseInt(e.getActionCommand())).getInfo();

        int confirmResult = JOptionPane.showConfirmDialog(view,
                String.format("Do you really want to remove this vehicle?\n\n%s: %s\nNumber plate: %s",
                        vehicleInfo.get(Vehicle.BRAND), vehicleInfo.get(Vehicle.MODEL), vehicleInfo.get(Vehicle.NUMBER_PLATE)),
                "Removal confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirmResult == JOptionPane.YES_OPTION) {
            dataManager.getAllVehiclesDB().get(Integer.parseInt(e.getActionCommand())).setOwner(dataManager.getAllUsersDB().get(0));
            showMyVehiclesPanel();

        }
    }
    private void editButtonListener(ActionEvent e){
        editDialog = new JDialog();
        LinkedHashMap<String, Object> vehicleInfo = dataManager.getAllVehiclesDB().get(Integer.parseInt(e.getActionCommand())).getInfo();
        vehicleEditPanel = new VehicleEditPanel(vehicleInfo,e1 -> editDialogListener(e1,e));
        editDialog.setTitle("Editing - " + vehicleInfo.get(Vehicle.NUMBER_PLATE));
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
                dataManager.getAllVehiclesDB().get(Integer.parseInt(e.getActionCommand())).setInfo(vehicleEditPanel.getDialogTFsStrings());
                showMyVehiclesPanel();
                editDialog.dispose();
            }
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
            dataManager.save(vehicle);
            loggedUser.addVehicleToSet(vehicle.getId());
            dataManager.save(loggedUser);
        }
    }


    //TODO JTF Validation
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

        //Registration confirmation
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

        dataManager.register(view.getLoginFirstNameJTF().getText(),view.getLoginLastNameJTF().getText(),age);

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
        dataManager.register(title,view.getCompanyIdJTF().getText());

//        String companyInfo = String.format("title,%s%n" +
//                "no,%s%n" +
//                "owns,%s%n",title,view.getCompanyIdJTF().getText(),null);

//        File usersDB = new File("src/data/users");
//        if (!usersDB.exists()) {
//            try{
//                usersDB.mkdirs();
//                usersDB.createNewFile();
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try{
//            BufferedWriter buf = new BufferedWriter(new FileWriter(usersDB + "/" + view.getCompanyIdJTF().getText(), true));
//
//            buf.append(companyInfo);
//
//            buf.close();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
        JOptionPane.showMessageDialog(view,"User created succesfully!");
    }
    //endregion

    //region Logging-in
    public void login() {
        System.out.println("Login button pressed");
        switch (this.userType){
            case VehicleOwner.TYPE_PERSON -> {
                loggedUser = authorize(view.getLoginFirstNameJTF().getText());
                if(loggedUser != null){
                    view.showMainPanel();

                    view.getLoginFirstNameJTF().setText("");
                    view.getLoginLastNameJTF().setText("");
                    view.getLoginFirstNameJTF().setBorder(new LineBorder(Color.black));
                }else{
                    failedLogin();
                }
            }
            case VehicleOwner.TYPE_COMPANY -> {
                loggedUser = authorize(view.getCompanyIdJTF().getText());
                if(loggedUser != null){
                    view.showMainPanel();


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



    private VehicleOwner authorize(String username){

        Collection<VehicleOwner> users =dataManager.getAllUsersDB().values();

        for(VehicleOwner user:users){
            switch (this.userType){
                case VehicleOwner.TYPE_PERSON -> {
                    if (user.getFirstName() != null && user.getFirstName().equals(username)) {
                        return user;
                    }
                }
                case VehicleOwner.TYPE_COMPANY -> {
                    if(user.getCompanyID() != null && user.getCompanyID().equals(username)){
                        return user;
                    }
                }
            }
        }

        return null;
    }


    private void failedLogin(){
        if(this.userType.equals(VehicleOwner.TYPE_PERSON)){
            view.getLoginFirstNameJTF().setBorder(new LineBorder(Color.red));
        }
        if(this.userType.equals(VehicleOwner.TYPE_COMPANY)){
            view.getCompanyIdJTF().setBorder(new LineBorder(Color.red));
        }

    }
    //endregion

    private void showSearchPanel(boolean search){

        HashMap<Integer,Vehicle> vehicles = null;

        if(search){
            if(!view.getSearchNumberPlateJTF().getText().equals(SearchOptions.BY_NUMBER_PLATE)){
                vehicles = dataManager.getSearchResults(view.getSearchNumberPlateJTF().getText(), databaseSelection,Vehicle.NUMBER_PLATE);
            }
            if (!view.getSearchBrandJTF().getText().equals(SearchOptions.BY_BRAND)){
                vehicles = dataManager.getSearchResults(view.getSearchBrandJTF().getText(),databaseSelection,Vehicle.BRAND);
            }
            if(!view.getSearchOwnerJTF().getText().equals(SearchOptions.BY_OWNER)){
                vehicles = dataManager.getSearchResults(view.getSearchOwnerJTF().getText(),databaseSelection,Vehicle.OWNER);
            }
        }else{
            vehicles = dataManager.getAllVehiclesDB();
        }

        //region Visibility check
        if(view.getSearchPanel().isVisible()){
            view.remove(view.getSearchPanel());
        }
        view.setSearchPanel(new SearchPanel(view.getButtonsListener(),this::searchDBListener));
        JPanel vehiclesGridBody = new JPanel(new FlowLayout());
        if(vehiclesGrid != null){
            view.getSearchBody().remove(vehiclesGridBody);
            vehiclesGridBody.remove(vehiclesGrid);
        }
        //endregion



        GridLayout resultsLayout = new GridLayout(0,Titles.ALL_VEHICLES_GRID_TITLE.length);
        vehiclesGrid = new JPanel(resultsLayout);


        for(String title:Titles.ALL_VEHICLES_GRID_TITLE){
            JLabel cell = new JLabel(title);
            cell.setHorizontalAlignment(SwingConstants.CENTER);
            cell.setBorder(new LineBorder(Color.BLACK));
            vehiclesGrid.add(cell);
        }


        if(vehicles != null) {

            vehicles.forEach((vehicleId, vehicle) -> {

                LinkedHashMap<String, Object> vehicleInfo = vehicle.getInfo();

                vehicleInfo.forEach((info, stat) -> {
                    switch (info) {
                        case Vehicle.BRAND, Vehicle.MODEL, Vehicle.NUMBER_PLATE, Vehicle.PRICE, Vehicle.TYPE, Vehicle.OWNER -> {
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
        }else{
            vehiclesGridBody.add(new JLabel("There's no vehicles :("));
        }




        view.getSearchBody().add(vehiclesGridBody);
        view.showSearchPanel();
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

        vehiclesGrid = new JPanel(new GridLayout(0, Titles.MY_VEHICLES_GRID_TITLE.length));

        for(String title:Titles.MY_VEHICLES_GRID_TITLE){
            JLabel cell = new JLabel(title);
            cell.setHorizontalAlignment(SwingConstants.CENTER);
            cell.setBackground(Color.lightGray);
            vehiclesGrid.add(cell);
        }

        HashMap<Integer,Vehicle> vehicles = dataManager.getSearchResults(String.valueOf(loggedUser.getId()),
                SearchOptions.DB_ALL_VEHICLES,
                SearchOptions.BY_OWNER_ID);



        if(vehicles != null) {
            vehicles.forEach((vehicleId, vehicle) -> {

                LinkedHashMap<String, Object> vehicleInfo = vehicle.getInfo();

                vehicleInfo.forEach((info, stat) -> {
                    switch (info) {
                        case Vehicle.BRAND, Vehicle.MODEL, Vehicle.HORSE_POWER, Vehicle.SEATS, Vehicle.NUMBER_PLATE, Vehicle.PRICE -> {
                            JLabel cell = new JLabel(stat.toString());
                            cell.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.setBorder(new LineBorder(Color.BLACK));
                            vehiclesGrid.add(cell);

                        }
                        case Vehicle.ID -> vehiclesGrid.add(vehicleGridEdit(String.valueOf(stat)));

                    }
                });
            });
            vehiclesGridBody.add(vehiclesGrid);
        }else {
            vehiclesGridBody.add(new JLabel("You have no vehicles ;("));
        }


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

    private void logOut(){
        if(dataManager.saveDB()){
            dataManager = new DataManager();
            view.showLoginPanel();
        }

    }


}
