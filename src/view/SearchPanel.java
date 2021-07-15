package view;

import data.constants.Buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {

    JPanel bodyPanel;

    public SearchPanel(ActionListener buttonListener){
        this.setLayout(new BorderLayout());

        this.add(BorderLayout.NORTH,header(buttonListener));

    }

    //region Header
    private JPanel header(ActionListener buttonListener){
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER,150,20));

        //Content
        JLabel title = new JLabel("Vehicle search");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JButton goRegister = new JButton("Register");
        goRegister.setHorizontalAlignment(SwingConstants.RIGHT);
        goRegister.setActionCommand(Buttons.REGISTER);
        goRegister.addActionListener(buttonListener);

        JButton goBack = new JButton("Main menu");
        goBack.setHorizontalAlignment(SwingConstants.LEFT);
        goBack.setActionCommand(Buttons.MAIN_MENU);
        goBack.addActionListener(buttonListener);


        //Addding Content
        header.add(goBack);
        header.add(title);
        header.add(goRegister);

        return header;
    }
    //endregion

    //region Body

    private JPanel body(){
        bodyPanel = new JPanel(new BorderLayout());
        String[] gridHeader = {"Brand","Model","HP","Seats","Number plate","Price","Actions"};

        JPanel header = new JPanel(new GridLayout(0, gridHeader.length));

        for(String title:gridHeader){
            JLabel cell = new JLabel(title);
            cell.setHorizontalAlignment(SwingConstants.CENTER);
            header.add(cell);
        }

        bodyPanel.add(BorderLayout.NORTH,header);

        return  bodyPanel;
    }


    //endregion



}
