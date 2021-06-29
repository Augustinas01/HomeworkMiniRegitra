package helpers;

import view.MainPanel;
import view.MainWindow;

import java.util.ArrayList;

public class Brains implements MainWindow.MainWindowListener {

    MainWindow view;


    public Brains(MainWindow view, ArrayList<String> manufactorsList){
        this.view = view;

        //Setting listeners
        view.setRegisterButtonListener(e -> registerButton());
        view.setSearchButtonListener(e -> searchButton());
        view.setGoBackButtonListener(e -> goBackButton());

        view.init( manufactorsList.toArray(new String[0]));

        view.showMainPanel();
    }

    //region Listeners
    @Override
    public void registerButton() {
        System.out.println("Register button pressed");
        view.showRegisterPanel();

    }

    @Override
    public void searchButton() {
        System.out.println("Search button pressed");
        view.showSearchPanel();

    }

    @Override
    public void goBackButton() {
        System.out.println("Go back button pressed");
        view.showMainPanel();
    }
    //endregion


}
