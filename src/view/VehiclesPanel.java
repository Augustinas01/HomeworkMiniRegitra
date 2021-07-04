package view;

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
        JButton goSearch = new JButton("Register");
        JButton goBack = new JButton("Main menu");

        //Content Settings
        goBack.setHorizontalAlignment(SwingConstants.LEFT);
        goSearch.setHorizontalAlignment(SwingConstants.RIGHT);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        goBack.addActionListener(buttonListener);
        goSearch.addActionListener(buttonListener);

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
        JPanel header = new JPanel(new GridLayout(0,4));


        String[] gridHeader = {"Brand","Model","HP","Year"};
        for(String cell:gridHeader){
            header.add(new JLabel(cell));
        }



        bodyPanel.add(BorderLayout.NORTH,header);

        return  bodyPanel;
    }

    //endregion

}
