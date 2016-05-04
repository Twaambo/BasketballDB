import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Kenny on 5/4/2016.
 */
public class DBView implements Observer {
    private DBController controller;
    private Stage stage;

    // TODO: Where should this go?
    private final ObservableList<String> tableOptions =
            FXCollections.observableArrayList(
                    "COACHES",
                    "PLAYERS",
                    "PLAYER_SEASON",
                    "TEAMS",
                    "TEAM_SEASON"
            );

    public DBView(DBController controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
        init();
    }

    private void init() {
        BorderPane bp = new BorderPane();

        // Put it all together
        bp.setTop(createTabPane());

        this.stage.setScene(new Scene(bp));
    }

    private TabPane createTabPane() {
        TabPane tabPane = new TabPane();

        // Query Tab
        Tab queryTab = new Tab("Query");
        queryTab.setClosable(false);
        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(createCriteriaTable());
        mainPane.setCenter(createQueryResultTable());
        queryTab.setContent(mainPane);

        // Criteria Buttons
        FlowPane bottomPane = new FlowPane();
        Button queryCriteriaButton = new Button("Add Query Criteria");
        Button clearCriteriaButton = new Button("Clear Criteria(s)");
        // Table Selector Dropdown
        ComboBox tableDropdown = new ComboBox(tableOptions);
        tableDropdown.setMinWidth(100);
        // Results Buttons
        Button clearResultsButton = new Button("Clear Result(s)");
        Button queryButton =  new Button("Query!");

        // margins
        bottomPane.setMargin(queryCriteriaButton, new Insets(10,5,10,10));
        bottomPane.setMargin(clearCriteriaButton, new Insets(10,5,10,10));
        bottomPane.setMargin(tableDropdown, new Insets(10,5,10,60));
        bottomPane.setMargin(clearResultsButton, new Insets(10, 10, 10, 150));
        bottomPane.setMargin(queryButton, new Insets(10, 10, 10, 5));

        // Add all to the pane
        bottomPane.getChildren().addAll(queryCriteriaButton, clearCriteriaButton, tableDropdown, clearResultsButton, queryButton);
        mainPane.setBottom(bottomPane);

        // Add Tabs to TabPane
        tabPane.getTabs().add(queryTab);
        // TODO: Do we want to add plotting? if not get rid of tabs

        return tabPane;
    }

    private TableView<Criteria> createCriteriaTable() {
        TableView<Criteria> table = new TableView();

        TableColumn parameters = new TableColumn("Parameters");
        parameters.setCellValueFactory(new PropertyValueFactory<Criteria, String>("parameter"));
        // TODO: BUG, columns resize when clicked!?
        parameters.prefWidthProperty().bind(table.widthProperty().multiply(0.6));
        parameters.maxWidthProperty().bind(parameters.prefWidthProperty());
        parameters.setResizable(false);
        TableColumn values = new TableColumn("Values");
        values.setCellValueFactory(new PropertyValueFactory<Criteria, String>("value"));
        values.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        values.maxWidthProperty().bind(values.prefWidthProperty());
        values.setResizable(false);
        table.getColumns().addAll(parameters, values);

        return table;
    }

    private TableView<QueryResult> createQueryResultTable() {
        TableView<QueryResult> table = new TableView();


        TableColumn parameters = new TableColumn("Test1");
        parameters.setCellValueFactory(new PropertyValueFactory<QueryResult, String>("parameter"));
        TableColumn values = new TableColumn("Test2");
        values.setCellValueFactory(new PropertyValueFactory<QueryResult, String>("value"));

        table.getColumns().addAll(parameters, values);

        return table;
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO: if this is a result object populate the result table
    }
}
