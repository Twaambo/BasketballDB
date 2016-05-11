package views;

import db.Criteria;
import db.DBController;
import db.ObserverNotification;
import db.QueryResult;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

/**
 * Created by Kenny on 5/4/2016.
 */
public class DBView implements Observer {
    private DBController controller;
    private Stage stage;

    private QueryView qtv;
    private AdminView av;

    public DBView(DBController controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
        qtv = new QueryView(controller);
        av = new AdminView(controller);
        init();
    }

    private void init() {
        BorderPane bp = new BorderPane();
        TabPane tabPane = new TabPane();

        tabPane.getTabs().addAll(qtv.init(), av.init());
        // Put it all together
        bp.setTop(tabPane);

        this.stage.setScene(new Scene(bp));
    }

    private void populateQueryResultTable(ArrayList<QueryResult> results) {
        TableView resultTable = qtv.getResultTable();
        resultTable.getColumns().remove(0, resultTable.getColumns().size());
        resultTable.getColumns().clear();
        if(!results.isEmpty()) {
            QueryResult result = results.get(0);
            for(String param : result.getParameters()) {
                TableColumn newCol = new TableColumn(param);
                newCol.setCellValueFactory(new PropertyValueFactory<QueryResult, String>(param));
                resultTable.getColumns().add(newCol);
            }
        }
        resultTable.getItems().addAll(results);
    }

    @Override
    public void update(Observable o, Object arg) {
        TableView criteriaTable = qtv.getCriteriaTable();
        TableView resultTable = qtv.getResultTable();
        Button clearResultsButton = qtv.getClearResultsButton();
        Button clearCriteriaButton = qtv.getClearCriteriaButton();

        ObserverNotification notification = (ObserverNotification) arg;
        if(notification.type == ObserverNotification.Type.SEARCH_CRITERIA) {
            ArrayList<Criteria> criteriaList = (ArrayList<Criteria>) notification.obj;
            criteriaTable.getItems().clear();
            criteriaTable.getItems().addAll(criteriaList);
            if(criteriaList.isEmpty()) {
                clearCriteriaButton.setDisable(true);
            } else {
                clearCriteriaButton.setDisable(false);
            }
        } else if (notification.type == ObserverNotification.Type.QUERY_RESULT) {
            ArrayList<QueryResult> queryResults = (ArrayList<QueryResult>) notification.obj;
            if(queryResults.isEmpty()) {
                clearResultsButton.setDisable(true);
                resultTable.getItems().clear();

            } else {
                clearResultsButton.setDisable(false);
                populateQueryResultTable(queryResults);
            }
        } else if (notification.type == ObserverNotification.Type.TABLE_UPDATE) {
            ArrayList<String> tables = (ArrayList<String>) notification.obj;
            qtv.getTableDropdown().getItems().clear();
            qtv.getTableDropdown().getItems().addAll(tables);
            av.getTableDropdown().getItems().clear();
            av.getTableDropdown().getItems().addAll(tables);
        } else if (notification.type == ObserverNotification.Type.DROP_TABLE_SUCCESS) {
            Label status = av.getStatus();
            String table = (String) notification.obj;
            status.setText("SUCCESS - DROP TABLE: " + table);
            status.setTextFill(Color.GREEN);

        } else if (notification.type == ObserverNotification.Type.DROP_TABLE_FAILURE) {
            Label status = av.getStatus();
            String table = (String) notification.obj;
            status.setText("FAILURE - DROP TABLE: " + table);
            status.setTextFill(Color.RED);
        } else if (notification.type == ObserverNotification.Type.INIT_TABLE_SUCCESS) {
            Label status = av.getStatus();
            String table = (String) notification.obj;
            status.setText("SUCCESS - INIT TABLE: " + table);
            status.setTextFill(Color.GREEN);
        } else if (notification.type == ObserverNotification.Type.INIT_TABLE_FAILURE) {
            Label status = av.getStatus();
            String table = (String) notification.obj;
            status.setText("FAILURE - INIT TABLE: " + table);
            status.setTextFill(Color.RED);
        }
    }
}
