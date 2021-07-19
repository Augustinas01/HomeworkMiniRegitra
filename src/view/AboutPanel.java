package view;

import data.constants.Buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AboutPanel extends JPanel {

    public AboutPanel(ActionListener buttonListener){
        this.setLayout(new BorderLayout());

        this.add(BorderLayout.CENTER,body());
        this.add(BorderLayout.SOUTH,footer(buttonListener));
    }

    //region Body
    private JPanel body(){

        JPanel body = new JPanel(new BorderLayout());

        JLabel aboutInfo = new JLabel();
        aboutInfo.setHorizontalAlignment(JLabel.CENTER);
        aboutInfo.setText("Program created by Augustinas for CodeAcademy Homework, use it at your own risk ;D");

        body.add(BorderLayout.CENTER,aboutInfo);

        return body;
    }
    //endregion

    //region Footer
    private JPanel footer(ActionListener buttonListener){
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER,150,20));

        //Content
        JLabel title = new JLabel("About");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JButton goBack = new JButton("Main menu");
        goBack.setHorizontalAlignment(SwingConstants.LEFT);
        goBack.setActionCommand(Buttons.MAIN_MENU);
        goBack.addActionListener(buttonListener);

        //Adding Content
        footer.add(goBack);

        return footer;
    }
    //endregion




}
