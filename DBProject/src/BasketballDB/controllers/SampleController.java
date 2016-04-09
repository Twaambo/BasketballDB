package BasketballDB.controllers;

import BasketballDB.objects.Player;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class SampleController {

    @FXML private TableView<Player> playerTableView;

    public void addPlayer(ActionEvent event){
        ObservableList<Player> players = playerTableView.getItems();
        Player lebron = new Player (1, "LeBron", "James", "10/10/1980","Forward");
        players.add(lebron);
    }
}
