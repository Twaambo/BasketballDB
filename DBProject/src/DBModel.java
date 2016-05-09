import javax.management.Query;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Kenny on 5/9/2016.
 */
public class DBModel extends Observable {

    private ArrayList<Criteria> searchCriterias;
    private ArrayList<QueryResult> queryResults;
    private H2Main database;

    public DBModel() {
        this.searchCriterias = new ArrayList<>();
        this.queryResults = new ArrayList<>();
        this.database = new H2Main();
        this.database.init();
    }

    public void addCriteria(String criteria, String value) {
        this.searchCriterias.add(new Criteria(criteria, value));
        this.setChanged();
        this.notifyObservers(new ObserverNotification(ObserverNotification.Type.SEARCH_CRITERIA, searchCriterias));
    }

    public void clearCriterias() {
        this.searchCriterias = new ArrayList<>();
        this.setChanged();
        this.notifyObservers(new ObserverNotification(ObserverNotification.Type.SEARCH_CRITERIA, searchCriterias));
    }

    public void selectPlayers() {
        queryResults = PlayerTable.selectPlayers(database.getConnection());
        this.setChanged();
        this.notifyObservers(new ObserverNotification(ObserverNotification.Type.QUERY_RESULT, queryResults));
    }

    public void clearResults() {
        queryResults = new ArrayList<>();
        this.setChanged();
        this.notifyObservers(new ObserverNotification(ObserverNotification.Type.QUERY_RESULT, queryResults));
    }
}
