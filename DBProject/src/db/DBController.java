package db;

import java.util.ArrayList;

/**
 * Created by Kenny on 5/4/2016.
 */
public class DBController {

    private DBModel model;

    public DBController(DBModel model) {
        this.model = model;
    }

    public void addCriteria(String criteria, String value) {
        model.addCriteria(criteria, value);
    }

    public void clearCriterias() {
        model.clearCriterias();
    }

    public void selectPlayers(String table) {
        model.selectPlayers(table);
    }

    public void clearQueryResults() {
        model.clearResults();
    }

    public void dropTable(String table) {
        model.dropTable(table);
    }

    public void updateTables() {
        model.updateTables();
    }

    public void initTable(String table) {
        model.initTable(table);
    }
}
