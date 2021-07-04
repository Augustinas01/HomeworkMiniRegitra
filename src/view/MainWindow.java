package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private MainMenuPanel main;
    private RegisterPanel register;
    private SearchPanel search;
    private LoginPanel login;
    private VehiclesPanel myVehicles;
    ActionListener vehicleTypeRadio, vehicleMakerListListener, profileSelectorListener, buttonsListener;


    public interface MainWindowListener {
        void buttonsListener(ActionEvent e);
        void vehicleTypeRadio(ActionEvent e);
        void vehicleMakerList(ActionEvent e);
        void profileSelectorRadio(ActionEvent e);
    }


    public MainWindow(String title){
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800,600);
        this.getContentPane().setBackground(Color.decode("#f4f4f4"));
    }

    //region Setters

    public void setButtonsListener(ActionListener buttonsListener) {
        this.buttonsListener = buttonsListener;
    }
    public void setVehicleTypeRadioListener(ActionListener vehicleTypeRadio) {
        this.vehicleTypeRadio = vehicleTypeRadio;
    }
    public void setVehicleMakerListListener(ActionListener vehicleMakerListListener) {
        this.vehicleMakerListListener = vehicleMakerListListener;
    }
    public void setProfileSelectorListener(ActionListener profileSelectorListener) {
        this.profileSelectorListener = profileSelectorListener;
    }

    //endregion

    //region Getters

    public JComboBox<String> getVehicleMakersJCB(){
        return this.register.getVehicleMakersList();
    }
    public JComboBox<String> getVehicleModelsJCB(){
        return this.register.getVehicleModelsList();
    }
    public JTextField getRegistrationYearJTF(){
        return this.register.getRegistrationYear();
    }
    public JTextField getRegistrationMonthJTF(){
        return this.register.getRegistrationMonth();
    }
    public JTextField getRegistrationDayJTF(){
        return this.register.getRegistrationDay();
    }
    public JTextField getVehicleHorsePowerJTF(){
        return this.register.getVehicleHPTextField();
    }
    public JTextField getVehiclePriceJTF(){
        return this.register.getVehiclePriceTF();
    }
    public JTextField getVehicleSeatCountJTF(){
        return this.register.getVehicleSeatCountTF();
    }
    public JTextField getNumberPlateJTF(){
        return this.register.getVehicleNumberPlateTF();
    }
    public LoginPanel getLoginJP() {
        return this.login;
    }
    public JPanel getLoginTextFieldsJP(){
        return this.login.getLoginJP();
    }
    public JPanel getLoginPersonLoginJP(){
        return this.login.getPersonLoginJP();
    }
    public JPanel getLoginCompanyLoginJP(){
        return this.login.getCompanyLoginJP();
    }
    public JTextField getLoginFirstNameJTF(){
        return this.login.getFirstName();
    }
    public JTextField getLoginLastNameJTF(){
        return this.login.getLastName();
    }
    public JTextField getCompanyIdJTF(){
        return this.login.getCompanyID();
    }


    //endregion

    public void init(){
        this.main = new MainMenuPanel(this.buttonsListener);
        this.main.setVisible(false);

        this.search = new SearchPanel(this.buttonsListener);
        this.search.setVisible(false);

        this.register = new RegisterPanel(this.buttonsListener,
                                          this.vehicleTypeRadio,
                                          this.vehicleMakerListListener);
        this.register.setVisible(false);

        this.myVehicles = new VehiclesPanel(this.buttonsListener);
        this.myVehicles.setVisible(false);

        this.login = new LoginPanel(this.buttonsListener,
                                    this.profileSelectorListener);
        this.login.setVisible(false);
        this.setVisible(true);

    }

    public void showLoginPanel(){
        if (this.register.isVisible()) {
            this.register.setVisible(false);
            this.remove(this.register);
        }
        if (this.search.isVisible()) {
            this.search.setVisible(false);
            this.remove(this.search);
        }
        if(this.main.isVisible()) {
            this.main.setVisible(false);
            this.remove(this.main);
        }
        this.add(BorderLayout.CENTER, login);
        this.login.setVisible(true);

    }

    public void showMainPanel() {
        if (this.register.isVisible()) {
            this.register.setVisible(false);
            this.remove(this.register);
        }
        if (this.search.isVisible()) {
            this.search.setVisible(false);
            this.remove(this.search);
        }
        if(this.login.isVisible()) {
            this.login.setVisible(false);
            this.remove(this.login);
        }
        this.add(BorderLayout.CENTER, main);
        this.main.setVisible(true);
    }

    public void showRegisterPanel(){
        if(this.main.isVisible()){
            this.main.setVisible(false);
            this.remove(this.main);
        }
        if(this.search.isVisible()){
            this.search.setVisible(false);
            this.remove(this.search);
        }
        this.add(BorderLayout.CENTER,this.register);
        this.register.setVisible(true);
    }

    public void showSearchPanel(){
        if(this.main.isVisible()){
            this.main.setVisible(false);
            this.remove(this.main);
        }
        if(this.register.isVisible()){
            this.register.setVisible(false);
            this.remove(this.register);
        }
        this.add(BorderLayout.CENTER,this.search);
        this.search.setVisible(true);

    }

    public void showMyVheiclesPanel(){
        if(this.main.isVisible()){
            this.main.setVisible(false);
            this.remove(this.main);
        }
        if(this.register.isVisible()){
            this.register.setVisible(false);
            this.remove(this.register);
        }
        if(this.search.isVisible()){
            this.search.setVisible(false);
            this.remove(this.search);
        }
        this.add(BorderLayout.CENTER,this.myVehicles);
        this.myVehicles.setVisible(true);

    }
}
