package view;

import data.constants.Buttons;
import objects.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.LinkedHashMap;

public class VehicleInfoPanel extends JPanel {

    private JLabel registrationYearTF, registrationMonthTF, registrationDayTF, vehicleHPTF,vehiclePriceTF,vehicleSeatCountTF,vehicleNumberPlateTF, vehicleTaxLabel;

    public VehicleInfoPanel(LinkedHashMap<String,Object> vehicleInfo, ActionListener buttonListener){
        this.setLayout(new BorderLayout());

        this.add(BorderLayout.CENTER,body(vehicleInfo));
        this.add(BorderLayout.SOUTH,footer(buttonListener));

    }

    //region Body
    private JPanel body(LinkedHashMap<String,Object> vehicleInfo){
        JPanel body = new JPanel();
        body.setLayout(new GridLayout(0,2));

        //region Content
        //region Labels
        JLabel vehicleRegistrationDate = new JLabel("Registration date");
        JLabel vehicleHP = new JLabel("Horse power");
        JLabel vehiclePrice = new JLabel("Price");
        JLabel vehicleSeatCount = new JLabel("Seat count");
        JLabel vehicleNumberPlate = new JLabel("Number plate");
        JLabel vehicleTax = new JLabel("Tax");
        //endregion

        //region Vehicle registration year text fields
        JPanel vehicleRegistrationFields = new JPanel();
        this.registrationYearTF = new JLabel();
        this.registrationMonthTF = new JLabel();
        this.registrationDayTF = new JLabel();
        vehicleRegistrationFields.add(registrationYearTF);
        vehicleRegistrationFields.add(registrationMonthTF);
        vehicleRegistrationFields.add(registrationDayTF);
        //endregion
        this.vehicleHPTF = new JLabel();
        this.vehiclePriceTF = new JLabel();
        this.vehicleSeatCountTF = new JLabel();
        this.vehicleNumberPlateTF = new JLabel();
        this.vehicleTaxLabel = new JLabel();
        //endregion

        //Content settings

        setInfo(vehicleInfo);

        //region Adding Content

        body.add(vehicleRegistrationDate);
        body.add(vehicleRegistrationFields);

        body.add(vehicleHP);
        body.add(vehicleHPTF);

        body.add(vehiclePrice);
        body.add(vehiclePriceTF);

        body.add(vehicleSeatCount);
        body.add(vehicleSeatCountTF);

        body.add(vehicleNumberPlate);
        body.add(vehicleNumberPlateTF);

        body.add(vehicleTax);
        body.add(vehicleTaxLabel);
        //endregion

        return body;
    }
    //endregion

    //region Footer
    private JPanel footer(ActionListener buttonListener){
        JPanel footer = new JPanel();

        //Content
//        JButton changeButton = new JButton("Change");
//        changeButton.setActionCommand(Buttons.CHANGE);
//        changeButton.addActionListener(buttonListener);

        JButton cancelButton = new JButton(Buttons.OK);
        cancelButton.addActionListener(buttonListener);

        //Adding Content
//        footer.add(changeButton);
        footer.add(cancelButton);

        return footer;
    }

    //endregion

    private void setInfo(LinkedHashMap<String,Object> vehicleInfo){
        LocalDate vehicleRegistrationDate =(LocalDate) vehicleInfo.get(Vehicle.REGISTRATION_DATE);
        this.registrationYearTF.setText(String.valueOf(vehicleRegistrationDate.getYear()));
        this.registrationMonthTF.setText(String.valueOf(vehicleRegistrationDate.getMonthValue()));
        this.registrationDayTF.setText(String.valueOf(vehicleRegistrationDate.getDayOfMonth()));

        this.vehicleHPTF.setText(vehicleInfo.get(Vehicle.HORSE_POWER).toString());
        this.vehiclePriceTF.setText(vehicleInfo.get(Vehicle.PRICE).toString());
        this.vehicleSeatCountTF.setText(vehicleInfo.get(Vehicle.SEATS).toString());
        this.vehicleNumberPlateTF.setText(vehicleInfo.get(Vehicle.NUMBER_PLATE).toString());
        this.vehicleTaxLabel.setText(vehicleInfo.get(Vehicle.TAX_RATE).toString());
    }


}
