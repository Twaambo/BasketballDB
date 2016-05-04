import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Kenny on 5/4/2016.
 */
public class DBView implements Observer {
    private DBController controller;
    private Stage stage;


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

    private static TabPane createTabPane() {
        TabPane tabPane = new TabPane();

        // Query Tab
        Tab queryTab = new Tab("Query");
        queryTab.setClosable(false);
        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(createCriteriaTable());
        mainPane.setCenter(createQueryResultTable());
        queryTab.setContent(mainPane);

        // Add Tabs to TabPane
        tabPane.getTabs().add(queryTab);

        return tabPane;
    }

    private static TableView<Criteria> createCriteriaTable() {
        TableView<Criteria> table = new TableView();

        TableColumn parameters = new TableColumn("Parameters");
        parameters.setCellValueFactory(new PropertyValueFactory<Criteria, String>("parameter"));
        TableColumn values = new TableColumn("Values");
        values.setCellValueFactory(new PropertyValueFactory<Criteria, String>("value"));

        table.getColumns().addAll(parameters, values);

        return table;
    }

    private static TableView<QueryResult> createQueryResultTable() {
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
