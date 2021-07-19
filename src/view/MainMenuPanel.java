package view;

import data.constants.Buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {





    public MainMenuPanel(ActionListener mainMenuListener){
        this.setLayout(new FlowLayout(FlowLayout.CENTER,1000,25));
        this.setOpaque(false);

        //Content
        JButton registerButton = new JButton("Register");
        registerButton.setActionCommand(Buttons.REGISTER);
        registerButton.setFocusable(false);
        registerButton.addActionListener(mainMenuListener);

        JButton myVehiclesButton = new JButton("My vehicles");
        myVehiclesButton.setActionCommand(Buttons.MY_VEHICLES);
        myVehiclesButton.setFocusable(false);
        myVehiclesButton.addActionListener(mainMenuListener);

        JButton searchButton = new JButton("Search");
        searchButton.setActionCommand(Buttons.SEARCH);
        searchButton.setFocusable(false);
        searchButton.addActionListener(mainMenuListener);

        JButton aboutButton = new JButton("About");
        aboutButton.setActionCommand(Buttons.ABOUT);
        aboutButton.setFocusable(false);
        aboutButton.addActionListener(mainMenuListener);

        JButton logOutButton = new JButton("Log out");
        logOutButton.setActionCommand(Buttons.LOG_OUT);
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(mainMenuListener);

        //Adding content
        this.add(registerButton);
        this.add(myVehiclesButton);
        this.add(searchButton);
        this.add(aboutButton);
        this.add(logOutButton);
    }


}
