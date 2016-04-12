package BasketballDB.views;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

/**
 * Created by Kenny on 4/11/2016.
 */
public class SceneManager {
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

        // Create Player Tab
        Tab playerTab = new Tab("Player");
        playerTab.setClosable(false);
        TableView playerTable = createPlayerTable();

        BorderPane playerPane = new BorderPane();
        Button playerButton = new Button("Fetch Players");
        playerButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Put together the playerPane
        playerPane.setCenter(playerTable);
        playerPane.setBottom(playerButton);

        // Set the content of the tab to our newly constructed view
        playerTab.setContent(playerPane);

        // Create Coach Tab
        Tab coachTab = new Tab("Coach");
        coachTab.setClosable(false);
        TableView coachTable = createCoachTable();

        BorderPane coachPane = new BorderPane();
        Button coachButton = new Button("Fetch Coaches");
        coachButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Put together the coachPane
        coachPane.setCenter(coachTable);
        coachPane.setBottom(coachButton);

        // Set the content of the tab to our newly constructed view
        coachTab.setContent(coachPane);

        // Create Team Tab
        Tab teamTab = new Tab("Team");
        teamTab.setClosable(false);
        TableView teamTable = createTeamTable();

        BorderPane teamPane = new BorderPane();
        Button teamButton = new Button("Fetch Teams");
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
    private static TableView createCoachTable() {
        TableView table = new TableView();

        TableColumn playerIDCol = new TableColumn("Coach ID");
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn dob = new TableColumn("Wins");
        TableColumn pos = new TableColumn("Losses");

        table.getColumns().addAll(playerIDCol, firstNameCol, lastNameCol, dob, pos);

        return table;
    }

    private static TableView createTeamTable() {
        TableView table = new TableView();

        TableColumn playerIDCol = new TableColumn("Team ID");
        TableColumn firstNameCol = new TableColumn("Name");
        TableColumn lastNameCol = new TableColumn("Location");
        TableColumn dob = new TableColumn("League");

        table.getColumns().addAll(playerIDCol, firstNameCol, lastNameCol, dob);

        return table;
    }

    private static TableView createPlayerTable() {
        TableView table = new TableView();

        TableColumn playerIDCol = new TableColumn("Player ID");
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn dob = new TableColumn("DOB");
        TableColumn pos = new TableColumn("Position");

        table.getColumns().addAll(playerIDCol, firstNameCol, lastNameCol, dob, pos);

        return table;
    }
}
