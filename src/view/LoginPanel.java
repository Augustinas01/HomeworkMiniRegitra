package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    JTextField firstName,lastName,companyID;
    JPanel body, personLogin,companyLogin, loginJP;



    //region Getters

    public JTextField getFirstName() {
        return firstName;
    }

    public JTextField getLastName() {
        return lastName;
    }

    public JTextField getCompanyID() {
        return companyID;
    }

    public JPanel getPersonLoginJP() {
        return personLogin;
    }

    public JPanel getCompanyLoginJP() {
        return companyLogin;
    }

    public JPanel getLoginJP() {
        return loginJP;
    }
    //endregion


    public LoginPanel(ActionListener loginButtonListener, ActionListener signUpButtonListener,ActionListener profileSelectorListener){
        this.setLayout(new BorderLayout());

        this.personLogin = personLogin();
        this.companyLogin = companyLogin();
        this.loginJP = loginJP();
        this.add(BorderLayout.NORTH,new JLabel("Mini Regitra",SwingConstants.CENTER));
        this.add(BorderLayout.CENTER,body(loginButtonListener,signUpButtonListener,profileSelectorListener));
    }

    //region Body
    private JPanel body(ActionListener loginButtonListener, ActionListener signUpButtonListener,ActionListener profileSelectorListener){
        this.body = new JPanel();
        this.body.setLayout(new FlowLayout(FlowLayout.CENTER,1000,5));

        //Content


        JPanel loginSignUpButtons = new JPanel();
        JButton login = new JButton("Login");
        JButton signUp = new JButton("Sign up");
        loginSignUpButtons.add(login);
        loginSignUpButtons.add(signUp);


        JLabel loginAs = new JLabel("Profile type:");
        JPanel profileSelector = new JPanel();
        JRadioButton asPerson = new JRadioButton("Person");
        JRadioButton asCompany = new JRadioButton("Company");
        asPerson.setSelected(true);

        ButtonGroup bg = new ButtonGroup();
        bg.add(asPerson);
        bg.add(asCompany);

        profileSelector.add(asPerson);
        profileSelector.add(asCompany);

        //Content Settings

        login.addActionListener(loginButtonListener);
        signUp.addActionListener(signUpButtonListener);
        asPerson.addActionListener(profileSelectorListener);
        asCompany.addActionListener(profileSelectorListener);

        //Addding Content
        this.body.add(this.loginJP);
        this.body.add(loginAs);
        this.body.add(profileSelector);
        this.body.add(loginSignUpButtons);


        return this.body;
    }
    //endregion

    private JPanel loginJP(){
        this.loginJP = new JPanel();
        this.loginJP.add(this.personLogin);

        return this.loginJP;
    }

    private JPanel personLogin(){
        this.personLogin = new JPanel(new FlowLayout(FlowLayout.CENTER,1000,5));
        this.personLogin.setPreferredSize(new Dimension(200,100));
        JLabel firstNameLabel = new JLabel("First name");
        JLabel lastNameLabel = new JLabel("Last name");
        this.firstName = new JTextField(10);
        this.lastName = new JTextField(10);

        this.personLogin.add(firstNameLabel);
        this.personLogin.add(this.firstName);
        this.personLogin.add(lastNameLabel);
        this.personLogin.add(this.lastName);

        return this.personLogin;
    }

    private JPanel companyLogin(){
        this.companyLogin = new JPanel(new FlowLayout(FlowLayout.CENTER,1000,5));
        this.companyLogin.setPreferredSize(new Dimension(200,50));
        JLabel companyIDLabel = new JLabel("Company ID");
        this.companyID = new JTextField(10);

        this.companyLogin.add(companyIDLabel);
        this.companyLogin.add(this.companyID);

        return this.companyLogin;
    }


}
