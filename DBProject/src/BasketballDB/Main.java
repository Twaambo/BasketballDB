package BasketballDB;

import BasketballDB.objects.Presenter;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    Stage stage;
    GridPane gridPane;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        Presenter presenter = new Presenter();
        presenter.showMainView(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
