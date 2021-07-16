package view;

import data.constants.Buttons;
import data.constants.Titles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VehiclesPanel extends JPanel {


    JPanel bodyPanel;

    public VehiclesPanel(ActionListener buttonListener){
        this.setLayout(new BorderLayout());

        this.add(BorderLayout.NORTH,header(buttonListener));
        this.add(BorderLayout.CENTER,body());

    }

    public JPanel getBodyPanel() {
        return bodyPanel;
    }

    //region Header
    private JPanel header(ActionListener buttonListener){
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER,150,20));

        //Content
        JLabel title = new JLabel("Vehicle search");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JButton goSearch = new JButton("Register");
        goSearch.setHorizontalAlignment(SwingConstants.RIGHT);
        goSearch.setActionCommand(Buttons.REGISTER);
        goSearch.addActionListener(buttonListener);

        JButton goBack = new JButton("Main menu");
        goBack.setHorizontalAlignment(SwingConstants.LEFT);
        goBack.setActionCommand(Buttons.MAIN_MENU);
        goBack.addActionListener(buttonListener);

        //Addding Content
        header.add(goBack);
        header.add(title);
        header.add(goSearch);

        return header;
    }
    //endregion

    //region Body

    private JPanel body(){
        bodyPanel = new JPanel(new BorderLayout());

//        JPanel header = new JPanel(new GridLayout(0, Titles.MY_VEHICLES_GRID_TITLE.length));
//
//        for(String title:Titles.MY_VEHICLES_GRID_TITLE){
//            JLabel cell = new JLabel(title);
//            cell.setHorizontalAlignment(SwingConstants.CENTER);
//            header.add(cell);
//        }
//
//        bodyPanel.add(BorderLayout.NORTH,header);

        return  bodyPanel;
    }

    //endregion

}
