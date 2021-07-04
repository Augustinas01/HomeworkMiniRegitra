package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {





    public MainMenuPanel(ActionListener mainMenuListener){
        this.setLayout(new FlowLayout(FlowLayout.CENTER,1000,25));
        this.setOpaque(false);

        //Content
        JButton registerButton = new JButton("Register");
        registerButton.setFocusable(false);
        registerButton.addActionListener(mainMenuListener);

        JButton myVehiclesButton = new JButton("My vehicles");
        myVehiclesButton.setFocusable(false);
        myVehiclesButton.addActionListener(mainMenuListener);

        JButton searchButton = new JButton("Search");
        searchButton.setFocusable(false);
        searchButton.addActionListener(mainMenuListener);

        JButton logOutButton = new JButton("Log out");
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(mainMenuListener);

        //Adding content
        this.add(registerButton);
        this.add(myVehiclesButton);
        this.add(searchButton);
        this.add(logOutButton);
    }


}
