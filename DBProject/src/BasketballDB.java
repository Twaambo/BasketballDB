import db.DBController;
import db.DBModel;
import javafx.application.Application;
import javafx.stage.Stage;
import views.DBView;

public class BasketballDB extends Application {

    private DBModel model;
    private DBController controller;
    private DBView view;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Initialize Database
//        db.H2DB hMain = new db.H2DB();
//        hMain.init();

        model = new DBModel();
        controller = new DBController(model);
        view = new DBView(controller, primaryStage);
        model.addObserver(view);
        controller.updateTables();

        // Window Properties
        primaryStage.setTitle("Basketball Database");
        primaryStage.setHeight(550);
        primaryStage.setWidth(800);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        model.tearDown();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
