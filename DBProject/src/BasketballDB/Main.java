package BasketballDB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Basketball Database");
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("views/home.fxml"));

        Pane myPane = myLoader.load();

        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public void showPlayerView(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Player");
        FXMLLoader playerLoader = new FXMLLoader(getClass().getResource("views/player.fxml"));

        Pane playerPane = playerLoader.load();

        Scene playerScene = new Scene(playerPane);
        primaryStage.setScene(playerScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}