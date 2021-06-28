package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {





    public MainPanel(ActionListener registerButtonListener,ActionListener searchButtonListener){
        this.setLayout(new FlowLayout(FlowLayout.CENTER,50,250));
        this.setOpaque(false);

        //Content
        JButton registerButton = new JButton("Register");
        registerButton.setFocusable(false);
        registerButton.addActionListener(registerButtonListener);

        JButton searchButton = new JButton("Search");
        searchButton.setFocusable(false);
        searchButton.addActionListener(searchButtonListener);

        //Adding content
        this.add(registerButton);
        this.add(searchButton);
    }


}
