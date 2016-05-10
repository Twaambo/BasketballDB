package db;

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

    public void selectPlayers() {
        model.selectPlayers();
    }

    public void clearQueryResults() {
        model.clearResults();
    }
}
