package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {





    public MainMenuPanel(ActionListener registerButtonListener, ActionListener searchButtonListener, ActionListener logOutButtonListener){
        this.setLayout(new FlowLayout(FlowLayout.CENTER,1000,25));
        this.setOpaque(false);

        //Content
        JButton registerButton = new JButton("Register");
        registerButton.setFocusable(false);
        registerButton.addActionListener(registerButtonListener);

        JButton searchButton = new JButton("Search");
        searchButton.setFocusable(false);
        searchButton.addActionListener(searchButtonListener);

        JButton logOutButton = new JButton("Log out");
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(logOutButtonListener);

        //Adding content
        this.add(registerButton);
        this.add(searchButton);
        this.add(logOutButton);
    }


}
