package helpers;

import view.MainPanel;
import view.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Brains implements MainWindow.MainWindowListener {

    MainWindow view;


    public Brains(MainWindow view){
        this.view = view;

        //Setting listeners
        view.setRegisterButtonListener(e -> registerButton());
        view.setSearchButtonListener(e -> searchButton());
        view.setGoBackButtonListener(e -> goBackButton());
        view.setVehicleTypeRadioListener(e -> vehicleTypeRadio(e));

        view.init();
        view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList("Car")));
        view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("car", "BMW")));

        view.showMainPanel();
    }

    //region Listeners
    @Override
    public void registerButton() {
        System.out.println("Register button pressed");
        view.showRegisterPanel();

    }

    @Override
    public void searchButton() {
        System.out.println("Search button pressed");
        view.showSearchPanel();

    }

    @Override
    public void goBackButton() {
        System.out.println("Go back button pressed");
        view.showMainPanel();
    }

    @Override
    public void vehicleTypeRadio(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()){
            case "Car" ->  {
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList("car")));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("car","BMW")));
            }
            case "Motorcycle" -> {
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList("motorcycle")));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("motorcycle","BMW")));
            }
            case "Truck" -> {
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList("truck")));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("truck","MAN")));
            }
            case "Supercar" -> {
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList("supercar")));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("supercar","Ferrari")));
            }
        }

    }
    //endregion


    private String[] getManufactorsList(String type){
        File file = new File("src/data/" + type);
        return file.list();
    }
//    private String[] getModelsList(String type){
//        File file = new File("src/data/" + type);
//        return file.list();
//    }


    private static String[] getModelsList(String type, String manufactor) {
        String[] list = null;


        try (BufferedReader br = new BufferedReader(new FileReader("src/data/" + type + "/" + manufactor))) {
            String line;
            while ((line = br.readLine()) != null) {
                list = line.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }


}
