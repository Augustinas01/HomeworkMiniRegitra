package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

//    private JPanel main,search,register;
    private MainPanel main;
    private RegisterPanel register;
    private SearchPanel search;
    ActionListener registerButtonListener, searchButtonListener, goBackButtonListener;


    public interface MainWindowListener {
        void registerButton();
        void searchButton();
        void goBackButton();
    }


    public MainWindow(String title){
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800,600);
        this.getContentPane().setBackground(Color.decode("#f4f4f4"));


    }

    //region Setters

    public void setRegisterButtonListener(ActionListener registerButtonListener) {
        this.registerButtonListener = registerButtonListener;
    }

    public void setSearchButtonListener(ActionListener searchButtonListener) {
        this.searchButtonListener = searchButtonListener;
    }

    public void setGoBackButtonListener(ActionListener goBackButtonListener) {
        this.goBackButtonListener = goBackButtonListener;
    }
    //endregion

    //region Getters


    //endregion

    public void init(String[] makersList){
        this.main = new MainPanel(this.registerButtonListener,this.searchButtonListener);
        this.search = new SearchPanel(this.goBackButtonListener,this.registerButtonListener);
        this.register = new RegisterPanel(this.goBackButtonListener,this.searchButtonListener, makersList);
        this.setVisible(true);

    }



    public void showMainPanel() {
        if (this.register.isVisible()) {
            this.register.setVisible(false);
            this.remove(this.register);
        }
        if (this.search.isVisible()) {
            this.search.setVisible(false);
            this.remove(this.search);
        }
        this.add(BorderLayout.CENTER, main);
        this.main.setVisible(true);
    }

    public void showRegisterPanel(){
        if(this.main.isVisible()){
            this.main.setVisible(false);
            this.remove(this.main);
        }
        if(this.search.isVisible()){
            this.search.setVisible(false);
            this.remove(this.search);
        }
        this.add(BorderLayout.CENTER,this.register);
        this.register.setVisible(true);
    }

    public void showSearchPanel(){
        if(this.main.isVisible()){
            this.main.setVisible(false);
            this.remove(this.main);
        }
        if(this.register.isVisible()){
            this.register.setVisible(false);
            this.remove(this.register);
        }
        this.add(BorderLayout.CENTER,this.search);
        this.search.setVisible(true);

    }




}
