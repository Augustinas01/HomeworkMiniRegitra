import com.sun.tools.javac.Main;
import helpers.Brains;
import helpers.DataManager;
import view.MainWindow;


public class App {
    public static void main(String[] args) {
        MainWindow view = new MainWindow();
        Brains brains = new Brains(view);
    }
}
