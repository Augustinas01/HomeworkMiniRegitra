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
        Brains controller = new Brains(view, getManufactorsList());

    }

    public static ArrayList<String> getManufactorsList() {
        ArrayList<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/data/CarManufactors"))) {
            String line;

            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }



}
