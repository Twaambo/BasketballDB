package BasketballDB.views;

import BasketballDB.H2Main;
import BasketballDB.Main;
import BasketballDB.objects.Coach;
import BasketballDB.objects.Player;
import BasketballDB.objects.Team;
import BasketballDB.tables.CoachTable;
import BasketballDB.tables.PlayerTable;
import BasketballDB.tables.TeamTable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Kenny on 4/11/2016.
 */
public class SceneManager {

    public static Connection connection;

    // TODO: This should be majorly refactored during R2
    public static Scene createMainScene() {
        BorderPane bp = new BorderPane();

        // Put it all together
        bp.setCenter(createTabPane());
        bp.setTop(createTextField());

        return new Scene(bp);
    }

    private static TextField createTextField() {
        // Create a TextField
        TextField ta = new TextField();
        ta.setText("Querying - To be implemented");
        ta.setEditable(false);

        return ta;
    }

    private static TabPane createTabPane() {
        TabPane tabPane = new TabPane();

        // Place Holder Tab
        Tab placeholder = new Tab("Results");
        placeholder.setClosable(false);
        BorderPane placeHolderPane = new BorderPane();
        placeHolderPane.setCenter(new Label("TO BE IMPLEMENTED"));
        placeholder.setContent(placeHolderPane);

        // Create objects.Player Tab
        Tab playerTab = new Tab("objects.Player");
        playerTab.setClosable(false);
        TableView<Player> playerTable = createPlayerTable();

        BorderPane playerPane = new BorderPane();
        Button playerButton = new Button("Fetch Players");
        playerButton.setOnAction((event) -> {
            ArrayList<Player> result = PlayerTable.selectPlayers(H2Main.test);
            playerTable.getItems().addAll(result);
            playerButton.setDisable(true);
        });
        playerButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Put together the playerPane
        playerPane.setCenter(playerTable);
        playerPane.setBottom(playerButton);

        // Set the content of the tab to our newly constructed view
        playerTab.setContent(playerPane);

        // Create Coach Tab
        Tab coachTab = new Tab("Coach");
        coachTab.setClosable(false);
        TableView<Coach> coachTable = createCoachTable();

        BorderPane coachPane = new BorderPane();
        Button coachButton = new Button("Fetch Coaches");
        coachButton.setOnAction((event) -> {
            ArrayList<Coach> result = CoachTable.selectCoaches(H2Main.test);
            coachTable.getItems().addAll(result);
            coachButton.setDisable(true);
        });
        coachButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Put together the coachPane
        coachPane.setCenter(coachTable);
        coachPane.setBottom(coachButton);

        // Set the content of the tab to our newly constructed view
        coachTab.setContent(coachPane);

        // Create Team Tab
        Tab teamTab = new Tab("Team");
        teamTab.setClosable(false);
        TableView<Team> teamTable = createTeamTable();

        BorderPane teamPane = new BorderPane();
        Button teamButton = new Button("Fetch Teams");
        teamButton.setOnAction((event) -> {
            ArrayList<Team> result = TeamTable.selectTeams(H2Main.test);
            teamTable.getItems().addAll(result);
            teamButton.setDisable(true);
        });
        teamButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Put together the teamPane
        teamPane.setCenter(teamTable);
        teamPane.setBottom(teamButton);

        // Set the content of the tab to our newly constructed view
        teamTab.setContent(teamPane);

        // Add the tabs to the actual pane
        tabPane.getTabs().add(playerTab);
        tabPane.getTabs().add(coachTab);
        tabPane.getTabs().add(teamTab);
        tabPane.getTabs().add(placeholder);

        return tabPane;
    }

    // TODO: Make this into a generic function, pass into array w/ args
    private static TableView<Coach> createCoachTable() {
        TableView<Coach> table = new TableView();

        TableColumn playerIDCol = new TableColumn("Coach ID");
        playerIDCol.setCellValueFactory(new PropertyValueFactory<Player, String>("coach_id"));

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));

        TableColumn dob = new TableColumn("Wins");
        dob.setCellValueFactory(new PropertyValueFactory<Player, String>("careerWins"));

        TableColumn pos = new TableColumn("Losses");
        pos.setCellValueFactory(new PropertyValueFactory<Player, String>("careerLosses"));

        table.getColumns().addAll(playerIDCol, firstNameCol, lastNameCol, dob, pos);

        return table;
    }

    private static TableView<Team> createTeamTable() {
        TableView<Team> table = new TableView();

        TableColumn playerIDCol = new TableColumn("Team ID");
        playerIDCol.setCellValueFactory(new PropertyValueFactory<Player, String>("team_id"));

        TableColumn firstNameCol = new TableColumn("Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));

        TableColumn lastNameCol = new TableColumn("Location");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("location"));

        TableColumn dob = new TableColumn("League");
        dob.setCellValueFactory(new PropertyValueFactory<Player, String>("league"));


        table.getColumns().addAll(playerIDCol, firstNameCol, lastNameCol, dob);

        return table;
    }

    private static TableView<Player> createPlayerTable() {
        TableView<Player> table = new TableView();

        TableColumn playerIDCol = new TableColumn("objects.Player ID");
        playerIDCol.setCellValueFactory(new PropertyValueFactory<Player, String>("player_id"));
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
        TableColumn dob = new TableColumn("DOB");
        dob.setCellValueFactory(new PropertyValueFactory<Player, String>("dob"));
        TableColumn pos = new TableColumn("Position");
        pos.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));

        table.getColumns().addAll(playerIDCol, firstNameCol, lastNameCol, dob, pos);

        return table;
    }


}
