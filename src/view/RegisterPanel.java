package view;

import data.constants.Buttons;
import objects.Vehicle;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class RegisterPanel extends JPanel {

    public JComboBox<String> vehicleMakersList,vehicleModelsList;
    private JTextField registrationYear,registrationMonth,registrationDay,vehicleHPTextField,vehiclePriceTF,vehicleSeatCountTF,vehicleNumberPlateTF;



    public RegisterPanel(ActionListener buttonListener,
                         ActionListener vehicleTypeRadio,
                         ActionListener vehicleMakersListListener){
        this.setLayout(new BorderLayout());

        this.add(BorderLayout.NORTH,header(buttonListener));
        this.add(BorderLayout.CENTER,body(vehicleTypeRadio,vehicleMakersListListener));
        this.add(BorderLayout.SOUTH,footer(buttonListener));
    }

    //region Setters




    //endregion

    //region Getters

    public JComboBox<String> getVehicleMakersList() {
        return vehicleMakersList;
    }

    public JComboBox<String> getVehicleModelsList() {
        return vehicleModelsList;
    }

    public JTextField getRegistrationYear() {
        return registrationYear;
    }

    public JTextField getRegistrationMonth() {
        return registrationMonth;
    }

    public JTextField getRegistrationDay() {
        return registrationDay;
    }

    public JTextField getVehicleHPTextField() {
        return vehicleHPTextField;
    }

    public JTextField getVehiclePriceTF() {
        return vehiclePriceTF;
    }

    public JTextField getVehicleSeatCountTF() {
        return vehicleSeatCountTF;
    }

    public JTextField getVehicleNumberPlateTF() {
        return vehicleNumberPlateTF;
    }

    public HashMap<String,String> getRegInfoMap(){
        HashMap<String,String> info = new HashMap<>();

        if(yearIsValid() && monthIsValid() && dayIsValid()){
            info.put(Vehicle.REGISTRATION_DATE,String.format("%s-%s-%s",registrationYear.getText(),registrationMonth.getText(),registrationDay.getText()));
        }else{
            return null;
        }

        if(horsePowerIsValid()){
            info.put(Vehicle.HORSE_POWER,vehicleHPTextField.getText());
        }else{
            return null;
        }

        if(priceIsValid()){
            info.put(Vehicle.PRICE,vehiclePriceTF.getText());
        }else{
            return null;
        }

        if(seatsIsValid()){
            info.put(Vehicle.SEATS,vehicleSeatCountTF.getText());
        }else{
            return null;
        }

        if(numberPlateIsValid()){
            info.put(Vehicle.NUMBER_PLATE, vehicleNumberPlateTF.getText());
        }else{
            return null;
        }

        info.put(Vehicle.BRAND, Objects.requireNonNull(vehicleMakersList.getSelectedItem()).toString());
        info.put(Vehicle.MODEL, Objects.requireNonNull(vehicleModelsList.getSelectedItem()).toString());
        return info;
    }

    //endregion

    //region Header
    private JPanel header(ActionListener buttonListener){
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER,150,20));

        //Content
        JLabel title = new JLabel("Vehicle registration");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JButton goSearch = new JButton("Search");
        goSearch.setHorizontalAlignment(SwingConstants.RIGHT);
        goSearch.setActionCommand(Buttons.SEARCH);
        goSearch.addActionListener(buttonListener);

        JButton goBack = new JButton("Main menu");
        goBack.setHorizontalAlignment(SwingConstants.LEFT);
        goBack.setActionCommand(Buttons.MAIN_MENU);
        goBack.addActionListener(buttonListener);


        //Addding Content
        header.add(goBack);
        header.add(title);
        header.add(goSearch);

        return header;
    }
    //endregion

    //region Body
    private JPanel body(ActionListener vehicleTypeRadioListener,ActionListener vehicleMakersListListener){
        JPanel body = new JPanel();
        body.setLayout(new GridLayout(0,2));

        //region Content
        //region Labels
        JLabel vehicleType = new JLabel("Select vehicle type");
        JLabel vehicleMaker = new JLabel("Make");
        JLabel vehicleModel = new JLabel("Model");
        JLabel vehicleRegistrationDate = new JLabel("First registration date");
        JLabel vehicleHP = new JLabel("Horse power");
        JLabel vehiclePrice = new JLabel("Price");
        JLabel vehicleSeatCount = new JLabel("Seat count");
        JLabel vehicleNumberPlate = new JLabel("Number plate");
        //endregion
        //region Radio Buttons
        JRadioButton radioCar = new JRadioButton("Car");
        radioCar.setActionCommand(Vehicle.TYPE_CAR);
        radioCar.addActionListener(vehicleTypeRadioListener);

        JRadioButton radioMotorcycle = new JRadioButton("Motorcycle");
        radioMotorcycle.setActionCommand(Vehicle.TYPE_MOTORCYCLE);
        radioMotorcycle.addActionListener(vehicleTypeRadioListener);

        JRadioButton radioTruck = new JRadioButton("Truck");
        radioTruck.setActionCommand(Vehicle.TYPE_TRUCK);
        radioTruck.addActionListener(vehicleTypeRadioListener);

        JRadioButton radioSuperCar = new JRadioButton("Supercar");
        radioSuperCar.setActionCommand(Vehicle.TYPE_SUPERCAR);
        radioSuperCar.addActionListener(vehicleTypeRadioListener);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioCar);
        bg.add(radioMotorcycle);
        bg.add(radioTruck);
        bg.add(radioSuperCar);

        radioCar.setSelected(true);


        //Adding buttons to panel
        JPanel radioButtons = new JPanel();
        radioButtons.add(radioCar);
        radioButtons.add(radioMotorcycle);
        radioButtons.add(radioTruck);
        radioButtons.add(radioSuperCar);
        //endregion
        //region Combobox
        vehicleMakersList = new JComboBox<>();
        vehicleModelsList = new JComboBox<>();

        vehicleMakersList.addActionListener(vehicleMakersListListener);

        //endregion
        //region Vehicle registration year text fields
        JPanel vehicleRegistrationFields = new JPanel();
        this.registrationYear = new JTextField(Vehicle.REGISTRATION_YEAR);
        this.registrationMonth = new JTextField(Vehicle.REGISTRATION_MONTH);
        this.registrationDay = new JTextField(Vehicle.REGISTRATION_DAY);
        vehicleRegistrationFields.add(registrationYear);
        vehicleRegistrationFields.add(registrationMonth);
        vehicleRegistrationFields.add(registrationDay);
        //endregion
        this.vehicleHPTextField = new JTextField("Horse Power");
        this.vehiclePriceTF = new JTextField("Price");
        this.vehicleSeatCountTF = new JTextField("Seat count");
        this.vehicleNumberPlateTF = new JTextField("Number plate");
        //endregion

        //Content settings

        //region Adding Content
        body.add(vehicleType); // radio buttons title
        body.add(radioButtons);

        body.add(vehicleMaker);
        body.add(vehicleMakersList);

        body.add(vehicleModel);
        body.add(vehicleModelsList);

        body.add(vehicleRegistrationDate);
        body.add(vehicleRegistrationFields);

        body.add(vehicleHP);
        body.add(vehicleHPTextField);

        body.add(vehiclePrice);
        body.add(vehiclePriceTF);

        body.add(vehicleSeatCount);
        body.add(vehicleSeatCountTF);

        body.add(vehicleNumberPlate);
        body.add(vehicleNumberPlateTF);
        //endregion

        return body;
    }
    //endregion

    //region Footer

    private JPanel footer(ActionListener buttonListener){
        JPanel footer = new JPanel();

        //Content
        JButton registerButton = new JButton("Register vehicle");
        registerButton.setActionCommand(Buttons.REGISTER_VEHICLE);
        registerButton.addActionListener(buttonListener);

        //Adding Content
        footer.add(registerButton);

        return footer;
    }



    //endregion


    private boolean yearIsValid(){
        String regYear = registrationYear.getText();

        if(!regYear.equals(Vehicle.REGISTRATION_YEAR)){
            try{
                if(Integer.parseInt(regYear) <= LocalDate.now().getYear()){
                    registrationYear.setBorder(new LineBorder(Color.green));
                    return true;
                }
            }catch (NumberFormatException e){
                System.out.println("Oops!");
            }
        }

        registrationYear.setBorder(new LineBorder(Color.red));
        return false;
    }
    private boolean monthIsValid(){
        String regMonth = registrationMonth.getText();

        if(!regMonth.equals(Vehicle.REGISTRATION_MONTH)){
            try{
                if(Integer.parseInt(regMonth) <= LocalDate.MAX.getMonthValue()){
                    registrationMonth.setBorder(new LineBorder(Color.green));
                    return true;
                }
            }catch (NumberFormatException e){
                System.out.println("Oops!");
            }
        }

        registrationMonth.setBorder(new LineBorder(Color.red));
        return false;
    }
    private boolean dayIsValid(){
        String regDay = registrationDay.getText();

        if(!regDay.equals(Vehicle.REGISTRATION_DAY)){
            try{
                if(Integer.parseInt(regDay) <= LocalDate.MAX.getDayOfMonth()){
                    registrationDay.setBorder(new LineBorder(Color.green));
                    return true;
                }
            }catch (NumberFormatException e){
                System.out.println("Oops!");
            }
        }

        registrationDay.setBorder(new LineBorder(Color.red));
        return false;
    }

    private boolean horsePowerIsValid(){
        String horsePower = vehicleHPTextField.getText();

        if(!horsePower.equals(Vehicle.HORSE_POWER)){
            try {
                if(Integer.parseInt(horsePower) <= 5000){
                    vehicleHPTextField.setBorder(new LineBorder(Color.green));
                    return true;
                }
            }catch (NumberFormatException e){
                System.out.println("Oops!");
            }
        }
        vehicleHPTextField.setBorder(new LineBorder(Color.red));
        return false;
    }

    private boolean priceIsValid(){
        if(!vehiclePriceTF.getText().equals(Vehicle.PRICE)) {
            try {
                new BigDecimal(vehiclePriceTF.getText());
                vehiclePriceTF.setBorder(new LineBorder(Color.green));
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Oops!");
            }
        }
        vehiclePriceTF.setBorder(new LineBorder(Color.red));
        return false;
    }

    private boolean seatsIsValid(){
        if(!vehicleSeatCountTF.getText().equals(Vehicle.SEATS)){
            try {
                if(Integer.parseInt(vehicleSeatCountTF.getText()) <= 300){
                    vehicleSeatCountTF.setBorder(new LineBorder(Color.green));
                    return true;
                }
            }catch (NumberFormatException e){
                System.out.println("Oops!");
            }
        }
        vehicleSeatCountTF.setBorder(new LineBorder(Color.red));
        return false;
    }

    private boolean numberPlateIsValid(){
        String[] numberPlate = vehicleNumberPlateTF.getText().split(":");

        if(numberPlate.length != 2){
            vehicleNumberPlateTF.setBorder(new LineBorder(Color.red));
            return false;
        }

        if(!vehicleNumberPlateTF.getText().equals(Vehicle.NUMBER_PLATE) && numberPlate[0].length() == 3){

            for (char c : numberPlate[0].toCharArray()) {
                if (!Character.isLetter(c)) {
                    vehicleNumberPlateTF.setBorder(new LineBorder(Color.red));
                    return false;
                }
            }

            try {
                if(Integer.parseInt(numberPlate[1]) <= 999){
                    vehicleNumberPlateTF.setBorder(new LineBorder(Color.green));
                    return true;
                }
            }catch (NumberFormatException e){
                System.out.println("Oops!");
            }
        }
        vehicleNumberPlateTF.setBorder(new LineBorder(Color.red));
        return false;
    }



}
