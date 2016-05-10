package db;

/**
 * Created by Kenny on 5/9/2016.
 */
public class ObserverNotification {
    public enum Type {
        SEARCH_CRITERIA,
        QUERY_RESULT_CLEAR, QUERY_RESULT
    }

    public Type type;
    public Object obj;

    public ObserverNotification(Type type, Object obj) {
        this.type = type;
        this.obj = obj;
    }
}
