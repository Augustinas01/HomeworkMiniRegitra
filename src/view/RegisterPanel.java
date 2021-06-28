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
        JButton goBack = new JButton("Go Back");

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
        //Content
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
        //endregion
        //region Make Combobox
        JComboBox vehicleMakersList = new JComboBox(this.makersList);
        //endregion



        //Content settings


        //Adding Content
        //region Type selector
        body.add(vehicleType); // radio buttons title
        body.add(radioCar);
        body.add(radioMotorcycle);
        body.add(radioTruck);
        body.add(radioSuperCar);
        //endregion
        body.add(vehicleMaker);
        body.add(vehicleMakersList);



        return body;
    }


    //endregion



}
