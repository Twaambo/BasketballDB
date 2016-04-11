package BasketballDB.controllers;

import java.util.ArrayList;

import BasketballDB.objects.CoachSeason;
import BasketballDB.objects.Player;
import BasketballDB.objects.PlayerSeason;
import BasketballDB.objects.Presenter;
import BasketballDB.objects.Team;
import BasketballDB.objects.TeamSeason;
import BasketballDB.tables.CoachSeasonTable;
import BasketballDB.tables.PlayerSeasonTable;
import BasketballDB.tables.TeamSeasonTable;
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
        Player lebron = new Player ("1", "LeBron", "James", "10/10/1980","Forward");
        players.add(lebron);
        
        /**
        ArrayList<CoachSeason> csList = new ArrayList<CoachSeason>();
        CoachSeason cs1 = new CoachSeason("1", "2", "3", 4, 5);
        CoachSeason cs2 = new CoachSeason("6", "7", "8", 9, 10);
        csList.add(cs1);
        csList.add(cs2);
        System.out.println(CoachSeasonTable.createCoachSeasonInsertSQL(csList));
        
        ArrayList<PlayerSeason> psList = new ArrayList<PlayerSeason>();
        PlayerSeason ps1 = new PlayerSeason("1", "2", "3", true, 4.0f, 5);
        PlayerSeason ps2 = new PlayerSeason("6", "7", "8", true, 9.0f, 10);
        psList.add(ps1);
        psList.add(ps2);
        System.out.println(PlayerSeasonTable.createPlayerSeasonInsertSQL(psList));
        
        ArrayList<TeamSeason> tsList = new ArrayList<TeamSeason>();
        TeamSeason ts1 = new TeamSeason("1", "2", "3", 4, 5, 6);
        TeamSeason ts2 = new TeamSeason("7", "8", "9", 10, 11, 12);
        tsList.add(ts1);
        tsList.add(ts2);
        System.out.println(TeamSeasonTable.createTeamSeasonInsertSQL(tsList));
        */
    }

    public void changeToPlayerView(ActionEvent event){
        Stage stage = (Stage) content.getScene().getWindow();
        //presenter.showPlayerView(primaryStage);
    }
}
