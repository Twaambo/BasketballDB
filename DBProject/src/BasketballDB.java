import javafx.application.Application;
import javafx.stage.Stage;

public class BasketballDB extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Initialize Database
//        H2Main hMain = new H2Main();
//        hMain.init();

        DBController controller = new DBController();
        DBView view = new DBView(controller, primaryStage);

        // Window Properties
        primaryStage.setTitle("Basketball Database");
        primaryStage.setHeight(550);
        primaryStage.setWidth(800);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
