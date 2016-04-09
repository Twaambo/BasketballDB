package BasketballDB.controllers;

import BasketballDB.objects.Player;
import BasketballDB.objects.Team;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HomeController {

    @FXML private TableView<Player> playerTableView;
    @FXML private TableView<Team> teamTableView;
    @FXML private TextField searchBar;

    public void addPlayer(ActionEvent event){
        ObservableList<Player> players = playerTableView.getItems();
        Player lebron = new Player (1, "LeBron", "James", "10/10/1980","Forward");
        players.add(lebron);
    }


    public void addTeam(ActionEvent event) {
        ObservableList<Team> teams = teamTableView.getItems();
    }
}
