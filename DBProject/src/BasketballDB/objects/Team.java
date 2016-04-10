package BasketballDB.objects;

/**
 * Hold data on a single team
 * @author Noah Shiotani
 */
public class Team {
    private int team_id;
    private String name;
    private String location;
    private String league;

    public Team (int team_id, String name, String location, String league) {
        this.team_id = team_id;
        this.name = name;
        this.location = location;
        this.league = league;
    }

    public Team(String[] data){
        this.team_id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.location = data[2];
        this.league = data[3];
    }

    public int getTeam_id() {
        return team_id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getLeague() { return league;}
}
