package views;

import db.Criteria;
import db.DBController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import objects.*;

import java.util.Optional;

/**
 * Created by Kenny on 5/11/2016.
 */
public class QueryView {
    private TableView criteriaTable;
    private TableView resultTable;
    private Button clearResultsButton;
    private Button queryButton;
    private Button clearCriteriaButton;
    private Button queryCriteriaButton;
    private ComboBox tableDropdown;
    private DBController controller;

    public QueryView(DBController controller) {
        this.controller = controller;
    }

    public Tab init() {
        // Query Tab
        Tab queryTab = new Tab("Query");
        queryTab.setClosable(false);
        BorderPane mainPane = new BorderPane();
        criteriaTable = createCriteriaTable();
        mainPane.setLeft(criteriaTable);
        resultTable = new TableView();
        mainPane.setCenter(resultTable);
        queryTab.setContent(mainPane);

        // db.Criteria Buttons
        FlowPane bottomPane = new FlowPane();
        queryCriteriaButton = new Button("Add Query Criteria");
        queryCriteriaButton.setDisable(true);
        clearCriteriaButton = new Button("Clear Criteria(s)");
        clearCriteriaButton.setDisable(true);
        // Table Selector Dropdown
        tableDropdown = new ComboBox();
        tableDropdown.setMinWidth(100);
        tableDropdown.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                queryButton.setDisable(false);
                queryCriteriaButton.setDisable(false);
            } else {
                queryButton.setDisable(true);
                queryCriteriaButton.setDisable(true);
            }
            controller.clearCriterias();
        });
        // Results Buttons
        clearResultsButton = new Button("Clear Result(s)");
        clearResultsButton.setOnAction((event) -> {
            clearQueryResultsTable();
        });
        clearResultsButton.setDisable(true);
        queryButton =  new Button("Query!");
        queryButton.setOnAction((event) -> {
            this.controller.clearQueryResults();
            this.controller.selectAllFromTable(tableDropdown.getValue().toString());

        });
        queryButton.setDisable(true);

        // margins
        bottomPane.setMargin(queryCriteriaButton, new Insets(10,5,10,10));
        bottomPane.setMargin(clearCriteriaButton, new Insets(10,5,10,10));
        bottomPane.setMargin(tableDropdown, new Insets(10,5,10,60));
        bottomPane.setMargin(clearResultsButton, new Insets(10, 5, 10, 130));
        bottomPane.setMargin(queryButton, new Insets(10, 10, 10, 5));

        // Add all to the pane
        bottomPane.getChildren().addAll(queryCriteriaButton, clearCriteriaButton, tableDropdown, clearResultsButton, queryButton);
        mainPane.setBottom(bottomPane);

        // ============================ UGLY SECTION START ===============================

        // Event Listener
        queryCriteriaButton.setOnAction((event) -> {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Criteria Dialog");
            dialog.setHeaderText("Please select a search criteria and specify a value");


            // Set the button types.
            ButtonType loginButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            // Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            ComboBox choices;
            switch( tableDropdown.getValue().toString()) {
                case "PLAYERS":
                    choices = new ComboBox(Player.ColHeaders);
                    break;
                case "COACHES":
                    choices = new ComboBox(Coach.ColHeaders);
                    break;
                case "COACH_SEASON":
                    choices = new ComboBox(CoachSeason.ColHeaders);
                    break;
                case "PLAYER_SEASON":
                    choices = new ComboBox(PlayerSeason.ColHeaders);
                    break;
                case "TEAMS":
                    choices = new ComboBox(Team.ColHeaders);
                    break;
                case "TEAM_SEASON":
                    choices = new ComboBox(TeamSeason.ColHeaders);
                    break;
                default:
                    choices = new ComboBox();
            }

            TextField value = new TextField();
            value.setPromptText("Value");

            grid.add(new Label("Criteria:"), 0, 0);
            grid.add(choices, 1, 0);
            grid.add(new Label("Value:"), 0, 1);
            grid.add(value, 1, 1);

            // Enable/Disable login button depending on whether a username was entered.
            Node confirmButton = dialog.getDialogPane().lookupButton(loginButtonType);
            confirmButton.setDisable(true);

            // Do some validation (using the Java 8 lambda syntax).
            choices.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(!value.getText().trim().isEmpty()) {
                    confirmButton.setDisable(newValue == null);
                }
            });

            value.textProperty().addListener((observable, oldValue, newValue) -> {
                if(choices.getValue() != null) {
                    confirmButton.setDisable(newValue.trim().isEmpty());
                }
            });

            dialog.getDialogPane().setContent(grid);

            // Convert the result to a username-password-pair when the confirm button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(choices.getValue().toString(), value.getText());
                }
                return null;
            });

            Optional<Pair<String, String>> result = dialog.showAndWait();

            result.ifPresent(callback -> {
                this.controller.addCriteria(callback.getKey(), callback.getValue());
            });
        });

        clearCriteriaButton.setOnAction((event) -> {
            this.controller.clearCriterias();
        });

        // ============================ UGLY SECTION END ===============================

        return queryTab;
    }


    private TableView<Criteria> createCriteriaTable() {
        TableView<Criteria> table = new TableView();

        TableColumn parameters = new TableColumn("Parameters");
        parameters.setCellValueFactory(new PropertyValueFactory<Criteria, String>("parameter"));
        // TODO: IGNORING THIS!? BUG, columns resize when clicked!?
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

    private void clearQueryResultsTable() {
        controller.clearQueryResults();
    }

    public TableView getResultTable() {
        return resultTable;
    }

    public TableView getCriteriaTable() {
        return criteriaTable;
    }

    public Button getClearResultsButton() {
        return clearResultsButton;
    }

    public Button getClearCriteriaButton() {
        return clearCriteriaButton;
    }

    public ComboBox getTableDropdown() {
        return tableDropdown;
    }
}
