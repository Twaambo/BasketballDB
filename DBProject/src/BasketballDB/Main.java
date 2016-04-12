package BasketballDB;

import BasketballDB.objects.Presenter;
import BasketballDB.views.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Initialize Database
        H2Main hMain = new H2Main();
        hMain.init();

        Scene mainScene = SceneManager.createMainScene();


        // Entry Scene
        primaryStage.setScene(mainScene);

        // Window Properties
        primaryStage.setTitle("Basketball Database");
        primaryStage.setHeight(600);
        primaryStage.setWidth(500);
        primaryStage.setResizable(false);

        primaryStage.show();
//        Presenter presenter = new Presenter();
//        presenter.showMainView(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
