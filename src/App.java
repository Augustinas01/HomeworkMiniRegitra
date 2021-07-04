import helpers.Brains;
import view.MainWindow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        MainWindow view = new MainWindow("MiniRegitra");
        Brains controller = new Brains(view);
    }

}
