package db;

import tables.CoachTable;
import tables.PlayerTable;
import tables.TeamTable;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Kenny on 5/9/2016.
 */
public class DBModel extends Observable {

    private ArrayList<Criteria> searchCriterias;
    private ArrayList<QueryResult> queryResults;
    private H2DB database;

    public DBModel() {
        this.searchCriterias = new ArrayList<>();
        this.queryResults = new ArrayList<>();
        this.database = new H2DB();
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

    public void selectPlayers(String table) {
        // TODO: HERE
        switch (table) {
            case "PLAYERS": {
                if(searchCriterias.isEmpty()) {
                    queryResults = PlayerTable.selectPlayers(database.getConnection());
                } else {

                }
                break;
            }
            //case "PLAYER_SEASON":
            case "COACHES": {
                if(searchCriterias.isEmpty()) {
                    queryResults = CoachTable.selectCoaches(database.getConnection());
                } else {

                }
                break;
            }
            case "COACH_SEASON": {
                if (searchCriterias.isEmpty()) {
//                    queryResults = CoachSeasonTable(database.getConnection());
                } else {

                }
                break;
            }
            case "TEAMS": {
                if(searchCriterias.isEmpty()) {
                    queryResults = TeamTable.selectTeams(database.getConnection());
                } else {

                }
                break;
            }
            case "TEAM_SEASON": {
                if (searchCriterias.isEmpty()) {
//                    queryResults = CoachSeasonTable(database.getConnection());
                } else {

                }
                break;
            }
        }
        this.setChanged();
        this.notifyObservers(new ObserverNotification(ObserverNotification.Type.QUERY_RESULT, queryResults));
    }

    public void clearResults() {
        queryResults = new ArrayList<>();
        this.setChanged();
        this.notifyObservers(new ObserverNotification(ObserverNotification.Type.QUERY_RESULT, queryResults));
    }
}
