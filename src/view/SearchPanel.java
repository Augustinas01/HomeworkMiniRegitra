package view;

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



}
