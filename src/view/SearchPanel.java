package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {

    public SearchPanel(ActionListener goBackButtonListener, ActionListener registerButtonListener){
        this.setLayout(new BorderLayout());

        this.add(BorderLayout.NORTH,header(goBackButtonListener,registerButtonListener));

    }

    //region Header
    private JPanel header(ActionListener goBackButtonListener, ActionListener registerButtonListener){
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.CENTER,150,20));

        //Content
        JLabel title = new JLabel("Vehicle search");
        JButton search = new JButton("Register");
        JButton goBack = new JButton("Main menu");

        //Content Settings
        goBack.setHorizontalAlignment(SwingConstants.LEFT);
        search.setHorizontalAlignment(SwingConstants.RIGHT);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        goBack.addActionListener(goBackButtonListener);
        search.addActionListener(registerButtonListener);

        //Addding Content
        header.add(goBack);
        header.add(title);
        header.add(search);

        return header;
    }
    //endregion



}
