package BasketballDB;

import BasketballDB.objects.Presenter;
import BasketballDB.views.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    Stage stage;
    GridPane gridPane;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Scene mainScene = SceneManager.createMainScene();





        // Entry Scene
        stage.setScene(mainScene);

        // Window Properties
        stage.setTitle("Basketball Database");
        stage.setHeight(600);
        stage.setWidth(500);
        stage.setResizable(false);

        stage.show();
//        Presenter presenter = new Presenter();
//        presenter.showMainView(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
