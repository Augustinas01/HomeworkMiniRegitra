package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {

    public JComboBox<String> vehicleMakersList,vehicleModelsList;



    public RegisterPanel(ActionListener goBackButtonListener,
                         ActionListener goSearchButtonListener,
                         ActionListener vehicleTypeRadio,
                         ActionListener vehicleMakersListListener){
        this.setLayout(new BorderLayout());

        this.add(BorderLayout.NORTH,header(goBackButtonListener,goSearchButtonListener));
        this.add(BorderLayout.CENTER,body(vehicleTypeRadio,vehicleMakersListListener));
        this.add(BorderLayout.SOUTH,footer());
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
    //endregion

    //region Header
    private JPanel header(ActionListener goBackButtonListener, ActionListener searchButtonListener){
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.CENTER,150,20));

        //Content
        JLabel title = new JLabel("Vehicle registration");
        JButton goSearch = new JButton("Search");
        JButton goBack = new JButton("Main menu");

        //Content Settings
        goBack.setHorizontalAlignment(SwingConstants.LEFT);
        goSearch.setHorizontalAlignment(SwingConstants.RIGHT);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        goBack.addActionListener(goBackButtonListener);
        goSearch.addActionListener(searchButtonListener);

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
        JRadioButton radioMotorcycle = new JRadioButton("Motorcycle");
        JRadioButton radioTruck = new JRadioButton("Truck");
        JRadioButton radioSuperCar = new JRadioButton("Supercar");


        radioCar.addActionListener(vehicleTypeRadioListener);
        radioMotorcycle.addActionListener(vehicleTypeRadioListener);
        radioTruck.addActionListener(vehicleTypeRadioListener);
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
        JTextField registrationYear = new JTextField("Year");
        JTextField registrationMonth = new JTextField("Month");
        JTextField registrationDay = new JTextField("Day");
        vehicleRegistrationFields.add(registrationYear);
        vehicleRegistrationFields.add(registrationMonth);
        vehicleRegistrationFields.add(registrationDay);
        //endregion
        JTextField vehicleHPTextField = new JTextField("Horse Power");
        JTextField vehiclePriceTF = new JTextField("Price");
        JTextField vehicleSeatCountTF = new JTextField("Price");
        JTextField vehicleNumberPlateTF = new JTextField("Number plate");
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

    //region footer

    private JPanel footer(){
        JPanel footer = new JPanel();

        //Content
        JButton registerButton = new JButton("Register");

        //Adding Content
        footer.add(registerButton);

        return footer;
    }



    //endregion



}
