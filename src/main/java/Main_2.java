import controllers.Controller_2;
import views.SimpleView;

public abstract class Main_2 {
    public static void main(String[] args) {
        new Controller_2(
                System.in,
                new SimpleView("Type english phrase and press Enter...")
        ).run();
    }
}
