package view;

import data.constants.Buttons;
import data.constants.SearchOptions;
import data.constants.Titles;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchPanel extends JPanel {

    JPanel searchResultBody;
    JComboBox<String> databaseSelection;

    JTextField numPlate,brand,owner;

    public SearchPanel(ActionListener buttonListener, ActionListener databaseSelectionListener){
        this.setLayout(new BorderLayout());

        this.add(BorderLayout.NORTH,header(buttonListener));
        this.add(BorderLayout.CENTER,body(buttonListener, databaseSelectionListener));

    }

    public JPanel getSearchResultBody() {
        return searchResultBody;
    }
    public JComboBox<String> getDatabaseSelection() {
        return databaseSelection;
    }
    public JTextField getNumPlate() {
        return numPlate;
    }
    public JTextField getBrand() {
        return brand;
    }
    public JTextField getOwner() {
        return owner;
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
    private JPanel body(ActionListener buttonListener, ActionListener databaseSelectionListener){

        JPanel body = new JPanel(new BorderLayout());
        body.add(BorderLayout.NORTH,searchSection(buttonListener, databaseSelectionListener ));

        searchResultBody = new JPanel(new BorderLayout());

        body.add(BorderLayout.CENTER,searchResultBody);

        return body;
    }
    //endregion

    private JPanel searchSection(ActionListener listener, ActionListener databaseSelectionListener){
        JPanel searchSection = new JPanel(new FlowLayout(FlowLayout.CENTER,50,10));
        searchSection.setBackground(Color.lightGray);
        GridLayout optionsLayout = new GridLayout(2,4);
        optionsLayout.setHgap(20);
        JPanel searchOptions = new JPanel(optionsLayout);
        searchOptions.setOpaque(false);
        numPlate = new JTextField(SearchOptions.BY_NUMBER_PLATE, 10);
        numPlate.setBorder(new LineBorder(Color.GRAY));
        brand = new JTextField(SearchOptions.BY_BRAND, 10);
        brand.setBorder(new LineBorder(Color.GRAY));
        owner = new JTextField(SearchOptions.BY_OWNER, 10);
        owner.setBorder(new LineBorder(Color.GRAY));
        databaseSelection = new JComboBox<>();
        databaseSelection.setModel(new DefaultComboBoxModel<>(SearchOptions.DATABASE_SELECTION));
        databaseSelection.addActionListener(databaseSelectionListener);

        JLabel numPlateLabel = new JLabel(SearchOptions.BY_NUMBER_PLATE);
        numPlateLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel brandLabel = new JLabel(SearchOptions.BY_BRAND);
        brandLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel ownerLabel = new JLabel(SearchOptions.BY_OWNER);
        ownerLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel dbLabel = new JLabel("Database");
        dbLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton searchVehicle = new JButton("Search");
        searchVehicle.setActionCommand(Buttons.SEARCH_VEHICLE);
        searchVehicle.addActionListener(listener);

        searchOptions.add(numPlateLabel);
        searchOptions.add(ownerLabel);
        searchOptions.add(brandLabel);
        searchOptions.add(dbLabel);
        searchOptions.add(numPlate);
        searchOptions.add(owner);
        searchOptions.add(brand);
        searchOptions.add(databaseSelection);

        searchSection.add(searchOptions);
        searchSection.add(searchVehicle);

        return searchSection;
    }



}
