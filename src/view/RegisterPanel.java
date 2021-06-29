package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {

    private final String[] makersList;



    public RegisterPanel(ActionListener goBackButtonListener, ActionListener searchButtonListener, String[] makersList){
        this.setLayout(new BorderLayout());
        this.makersList = makersList;

        this.add(BorderLayout.NORTH,header(goBackButtonListener,searchButtonListener));
        this.add(BorderLayout.CENTER,body());
    }

    //region Setters




    //endregion

    //region Header
    private JPanel header(ActionListener goBackButtonListener, ActionListener searchButtonListener){
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.CENTER,150,20));

        //Content
        JLabel title = new JLabel("Vehicle registration");
        JButton search = new JButton("Search");
        JButton goBack = new JButton("Main menu");

        //Content Settings
        goBack.setHorizontalAlignment(SwingConstants.LEFT);
        search.setHorizontalAlignment(SwingConstants.RIGHT);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        goBack.addActionListener(goBackButtonListener);
        search.addActionListener(searchButtonListener);

        //Addding Content
        header.add(goBack);
        header.add(title);
        header.add(search);

        return header;
    }
    //endregion

    //region Body
    private JPanel body(){
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
        JRadioButton radioSuperCar = new JRadioButton("SuperCar");
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioCar);
        bg.add(radioMotorcycle);
        bg.add(radioTruck);
        bg.add(radioSuperCar);

        //Adding buttons to panel
        JPanel radioButtons = new JPanel();
        radioButtons.add(radioCar);
        radioButtons.add(radioMotorcycle);
        radioButtons.add(radioTruck);
        radioButtons.add(radioSuperCar);
        //endregion
        //region Combobox
        JComboBox vehicleMakersList = new JComboBox(this.makersList);
        JComboBox vehicleModelsList = new JComboBox(this.makersList);
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
        JTextField vehicleNumberPlateTF = new JTextField("Number Plate");
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



}
