package view;

import data.constants.Buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {

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



}
