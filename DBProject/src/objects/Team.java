package objects;

import db.QueryResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Hold data on a single team
 * @author Noah Shiotani
 ** Created by The Data Principlists.
 */
public class Team implements QueryResult {
    public static final ArrayList<String> Parameters = new ArrayList() {{
        add("team_id");
        add("name");
        add("location");
        add("league");
    }};

    public static final ObservableList<String> ColHeaders =
            FXCollections.observableArrayList(
                    "TEAM_ID",
                    "NAME",
                    "LOCATION",
                    "LEAGUE"
            );

    private String team_id;
    private String name;
    private String location;
    private String league;

    public Team (String team_id, String name, String location, String league) {
        this.team_id = team_id;
        this.name = name;
        this.location = location;
        this.league = league;
    }

    public Team(String[] data){
        this.team_id = data[0];
        this.name = data[1];
        this.location = data[2];
        this.league = data[3];
    }

    public String getTeam_id() {
        return team_id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getLeague() { return league;}

    @Override
    public ArrayList<String> getParameters() {
        return Parameters;
    }
}
