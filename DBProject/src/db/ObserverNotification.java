package db;

/**
 * Created by The Data Principlists. on 5/9/2016.
 */
public class ObserverNotification {
    public enum Type {
        SEARCH_CRITERIA,
        QUERY_RESULT_CLEAR,
        TABLE_UPDATE,
        DROP_TABLE_SUCCESS,
        DROP_TABLE_FAILURE,
        INIT_TABLE_SUCCESS,
        INIT_TABLE_FAILURE, QUERY_RESULT
    }

    public Type type;
    public Object obj;

    public ObserverNotification(Type type, Object obj) {
        this.type = type;
        this.obj = obj;
    }
}
