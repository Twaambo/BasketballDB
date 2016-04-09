package BasketballDB.controllers;

import BasketballDB.objects.Player;
import BasketballDB.objects.Presenter;
import BasketballDB.objects.Team;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class HomeController {
    @FXML private GridPane content;
    @FXML private TableView<Player> playerTableView;
    @FXML private TextField searchBar;
    private Presenter presenter = new Presenter();

    public void addPlayer(ActionEvent event){
        ObservableList<Player> players = playerTableView.getItems();
        Player lebron = new Player (1, "LeBron", "James", "10/10/1980","Forward");
        players.add(lebron);
    }

    public void changeToPlayerView(ActionEvent event){
        Stage stage = (Stage) content.getScene().getWindow();
        //presenter.showPlayerView(primaryStage);
    }
}
