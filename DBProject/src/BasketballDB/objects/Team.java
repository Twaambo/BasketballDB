package BasketballDB.objects;

/**
 * Created by Noah Shiotani on 4/2/2016.
 */
public class Team {
    private int team_id;
    private String name;
    private String year;
    private String conference;

    public Team (int team_id, String name, String year) {
        this.team_id = team_id;
        this.name = name;
        this.year = year;
        this.conference = conference;
    }

    public String getYear() {
        return year;
    }

    public int getTeam_id() {
        return team_id;
    }

    public String getName() {
        return name;
    }

    public String getConference() {
        return conference;
    }
}
