import helpers.Brains;
import view.MainWindow;


public class App {

    public static void main(String[] args) {
        MainWindow view = new MainWindow("MiniRegitra");
        new Brains(view);
    }

}
