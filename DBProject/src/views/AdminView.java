package views;

import db.DBController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * Created by The Data Principlists 5/11/2016.
 */
public class AdminView {
    private DBController controller;
    private ComboBox tableDropdown;
    private ComboBox tableInits;
    private Label status;

    private static final ArrayList<String> Tables = new ArrayList() {{
        add("PLAYERS");
        add("COACHES");
        add("PLAYER_SEASONS");
        add("COACH_SEASONS");
        add("TEAMS");
        add("TEAM_SEASONS");
    }};

    public AdminView(DBController controller) {
        this.controller = controller;
    }

    public Tab init() {
        Tab tab = new Tab("Admin");

        FlowPane fpInit = new FlowPane();
        fpInit.setAlignment(Pos.CENTER);

        Button initTableButton = new Button("Init Table");
        initTableButton.setDisable(true);
        initTableButton.setOnAction((event) -> {
            controller.initTable(tableInits.getValue().toString());
        });

        tableInits = new ComboBox(FXCollections.observableArrayList(Tables));
        tableInits.setMinWidth(100);
        tableInits.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                initTableButton.setDisable(false);
            } else {
                initTableButton.setDisable(true);
            }
        });

        fpInit.setMargin(tableInits, new Insets(10, 10, 10, 10));
        fpInit.setMargin(initTableButton, new Insets(10, 10, 10, 10));
        fpInit.getChildren().addAll(tableInits, initTableButton);

        FlowPane fpDrop = new FlowPane();
        fpDrop.setAlignment(Pos.CENTER);

        Button dropTableButton = new Button("Drop Table");
        dropTableButton.setDisable(true);
        dropTableButton.setOnAction((event) -> {
           controller.dropTable(tableDropdown.getValue().toString());
        });

        tableDropdown = new ComboBox();
        tableDropdown.setMinWidth(100);
        tableDropdown.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                dropTableButton.setDisable(false);
            } else {
                dropTableButton.setDisable(true);
            }
        });

        fpDrop.setMargin(tableDropdown, new Insets(10, 10, 10, 10));
        fpDrop.setMargin(dropTableButton, new Insets(10, 10, 10, 10));
        fpDrop.getChildren().addAll(tableDropdown, dropTableButton);

        status = new Label("STATUS");
        status.setFont(new Font("Helvetica", 25));
        FlowPane fpStatus = new FlowPane(status);
        fpStatus.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(fpInit, fpDrop);
        vbox.setAlignment(Pos.CENTER);

        HBox hbox = new HBox(vbox, fpStatus);
        hbox.setAlignment(Pos.CENTER);

        tab.setContent(hbox);
        return tab;
    }

    public ComboBox getTableDropdown() {
        return tableDropdown;
    }

    public Label getStatus() {
        return status;
    }
}
