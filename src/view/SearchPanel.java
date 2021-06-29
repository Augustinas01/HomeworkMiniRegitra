package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {

    public SearchPanel(ActionListener goBackButtonListener, ActionListener goRegisterButtonListener){
        this.setLayout(new BorderLayout());

        this.add(BorderLayout.NORTH,header(goBackButtonListener,goRegisterButtonListener));

    }

    //region Header
    private JPanel header(ActionListener goBackButtonListener, ActionListener registerButtonListener){
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.CENTER,150,20));

        //Content
        JLabel title = new JLabel("Vehicle search");
        JButton goSearch = new JButton("Register");
        JButton goBack = new JButton("Main menu");

        //Content Settings
        goBack.setHorizontalAlignment(SwingConstants.LEFT);
        goSearch.setHorizontalAlignment(SwingConstants.RIGHT);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        goBack.addActionListener(goBackButtonListener);
        goSearch.addActionListener(registerButtonListener);

        //Addding Content
        header.add(goBack);
        header.add(title);
        header.add(goSearch);

        return header;
    }
    //endregion



}
