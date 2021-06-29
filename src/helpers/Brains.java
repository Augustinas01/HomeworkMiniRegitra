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
import java.util.Objects;

public class Brains implements MainWindow.MainWindowListener {

    MainWindow view;

    private String type;


    public Brains(MainWindow view){
        this.view = view;

        //Setting listeners
        view.setRegisterButtonListener(e -> registerButton());
        view.setSearchButtonListener(e -> searchButton());
        view.setGoBackButtonListener(e -> goBackButton());
        view.setVehicleTypeRadioListener(e -> vehicleTypeRadio(e));
        view.setVehicleMakerListListener(e -> vehicleMakerList(e));

        view.init();
        this.type = "car";
        view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
        view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("BMW")));

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
                this.type = "car";
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("BMW")));
            }
            case "Motorcycle" -> {
                this.type = "motorcycle";
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("BMW")));
            }
            case "Truck" -> {
                this.type = "truck";
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("MAN")));
            }
            case "Supercar" -> {
                this.type = "supercar";
                view.getVehicleMakersJCB().setModel(new DefaultComboBoxModel<>(getManufactorsList()));
                view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Ferrari")));
            }
        }

    }

    @Override
    public void vehicleMakerList(ActionEvent e) {
        switch (Objects.requireNonNull(view.getVehicleMakersJCB().getSelectedItem()).toString()){
            case "BMW" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("BMW")));
            case "Mercedes-Benz" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Mercedes-Benz")));
            case "Porsche" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Porsche")));
            case "Volkswagen" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Volkswagen")));
            case "MV_Agusta" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("MV_Agusta")));
            case "Suzuki" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Suzuki")));
            case "Yamaha" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Yamaha")));
            case "Ferrari" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Ferrari")));
            case "Koenigsegg" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Koenigsegg")));
            case "Lamborghini" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Lamborghini")));
            case "McLaren" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("McLaren")));
            case "MAN" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList( "MAN")));
            case "Scania" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList( "Scania")));
            case "Volvo" -> view.getVehicleModelsJCB().setModel(new DefaultComboBoxModel<>(getModelsList("Volvo")));
        }
    }
    //endregion


    private String[] getManufactorsList(){
        File file = new File("src/data/" + this.type);
        return file.list();
    }
//    private String[] getModelsList(String type){
//        File file = new File("src/data/" + type);
//        return file.list();
//    }


    private  String[] getModelsList(String manufactor) {
        String[] list = null;


        try (BufferedReader br = new BufferedReader(new FileReader("src/data/" + this.type + "/" + manufactor))) {
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
