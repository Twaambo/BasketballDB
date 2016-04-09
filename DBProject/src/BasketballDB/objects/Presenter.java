package BasketballDB.objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by will on 4/9/16.
 */
public class Presenter {

    public void showPlayerView(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Player");
        FXMLLoader playerLoader = new FXMLLoader(getClass().getResource("../views/player.fxml"));

        Pane playerPane = playerLoader.load();

        Scene playerScene = new Scene(playerPane);
        primaryStage.setScene(playerScene);
        primaryStage.show();

    }

    public void showMainView(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Basketball Database");
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("../views/home.fxml"));

        Pane myPane = myLoader.load();

        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}
