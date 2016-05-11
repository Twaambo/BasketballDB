package db;

import tables.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Kenny on 5/9/2016.
 */
public class DBModel extends Observable {

    private ArrayList<String> tables;
    private ArrayList<Criteria> searchCriterias;
    private ArrayList<QueryResult> queryResults;
    private H2DB database;

    public DBModel() {
        this.tables = new ArrayList<>();
        this.searchCriterias = new ArrayList<>();
        this.queryResults = new ArrayList<>();
        this.database = new H2DB();
        this.database.init();
    }

    public void tearDown() {
        this.database.closeConnection();
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
                    queryResults = CoachSeasonTable.selectCoachSeasons(database.getConnection());
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
                    queryResults = TeamSeasonTable.selectTeamSeason(database.getConnection());
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

    public void updateTables() {
        tables = new ArrayList<>();
        ResultSet result = database.showTables();
        try {
            while(result.next()){
                tables.add(result.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.setChanged();
        this.notifyObservers(new ObserverNotification(ObserverNotification.Type.TABLE_UPDATE, tables));
    }

    public void dropTable(String table) {
        boolean success = database.dropTable(table);
        this.setChanged();
        this.notifyObservers(new ObserverNotification((success) ?
                ObserverNotification.Type.DROP_TABLE_SUCCESS : ObserverNotification.Type.DROP_TABLE_FAILURE , table));
        updateTables();
    }

    public void initTable(String table) {
        boolean success = database.initTable(table);
        this.setChanged();
        this.notifyObservers(new ObserverNotification((success) ?
                ObserverNotification.Type.INIT_TABLE_SUCCESS : ObserverNotification.Type.INIT_TABLE_FAILURE , table));
        updateTables();
    }
}
