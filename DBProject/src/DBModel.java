import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Kenny on 5/9/2016.
 */
public class DBModel extends Observable {

    private ArrayList<Criteria> searchCriterias;

    public DBModel() {
        this.searchCriterias = new ArrayList<>();
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
}
