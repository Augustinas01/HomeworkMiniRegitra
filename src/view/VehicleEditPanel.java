package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class VehicleEditPanel extends JPanel {

    private JTextField registrationYearTF, registrationMonthTF, registrationDayTF, vehicleHPTF,vehiclePriceTF,vehicleSeatCountTF,vehicleNumberPlateTF;

    public VehicleEditPanel(LinkedHashMap<String,Object> vehicleInfo, ActionListener buttonListener){

        this.setLayout(new BorderLayout());

        this.add(BorderLayout.CENTER,body(vehicleInfo));
        this.add(BorderLayout.SOUTH,footer(buttonListener));

    }
    //region Getters

    public HashMap<String,String> getDialogTFsStrings(){
        HashMap<String,String> textFields = new HashMap<>();
        textFields.put("registrationYear",registrationYearTF.getText());
        textFields.put("registrationMonth",registrationMonthTF.getText());
        textFields.put("registrationDay",registrationDayTF.getText());
        textFields.put("vehicleHP",vehicleHPTF.getText());
        textFields.put("vehiclePrice",vehiclePriceTF.getText());
        textFields.put("vehicleSeats",vehicleSeatCountTF.getText());
        textFields.put("vehicleNumberPlate", vehicleNumberPlateTF.getText());
        return textFields;

    }

    public JTextField getRegistrationYearTF() {
        return registrationYearTF;
    }

    public JTextField getRegistrationMonthTF() {
        return registrationMonthTF;
    }

    public JTextField getRegistrationDayTF() {
        return registrationDayTF;
    }

    public JTextField getVehicleHPTF() {
        return vehicleHPTF;
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


    //endregion



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
        //endregion

        //region Vehicle registration year text fields
        JPanel vehicleRegistrationFields = new JPanel();
        this.registrationYearTF = new JTextField("Year",4);
        this.registrationMonthTF = new JTextField("MM",2);
        this.registrationDayTF = new JTextField("Day",2);
        vehicleRegistrationFields.add(registrationYearTF);
        vehicleRegistrationFields.add(registrationMonthTF);
        vehicleRegistrationFields.add(registrationDayTF);
        //endregion
        this.vehicleHPTF = new JTextField("Horse Power");
        this.vehiclePriceTF = new JTextField("Price");
        this.vehicleSeatCountTF = new JTextField("Seat count");
        this.vehicleNumberPlateTF = new JTextField("Number plate");
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
        //endregion

        return body;
    }
    //endregion

    //region Footer

    private JPanel footer(ActionListener buttonListener){
        JPanel footer = new JPanel();

        //Content
        JButton changeButton = new JButton("Change");
        JButton cancelButton = new JButton("Cancel");

        changeButton.addActionListener(buttonListener);
        cancelButton.addActionListener(buttonListener);

        //Adding Content
        footer.add(changeButton);
        footer.add(cancelButton);

        return footer;
    }

    //endregion

    private void setInfo(LinkedHashMap<String,Object> vehicleInfo){
        LocalDate vehicleRegistrationDate =(LocalDate) vehicleInfo.get("registrationDate");
        this.registrationYearTF.setText(String.valueOf(vehicleRegistrationDate.getYear()));
        this.registrationMonthTF.setText(String.valueOf(vehicleRegistrationDate.getMonthValue()));
        this.registrationDayTF.setText(String.valueOf(vehicleRegistrationDate.getDayOfMonth()));

        this.vehicleHPTF.setText(vehicleInfo.get("horsePower").toString());
        this.vehiclePriceTF.setText(vehicleInfo.get("price").toString());
        this.vehicleSeatCountTF.setText(vehicleInfo.get("seats").toString());
        this.vehicleNumberPlateTF.setText(vehicleInfo.get("numberPlate").toString());
    }


}
