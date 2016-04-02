package BasketballDB.objects;

/**
 * Hold data on a single player
 * @author Noah Shiotani
 */
public class Player {
    private int player_id;
    private int team_id;
    private String fName;
    private String lName;
    private String dob; //idk if this should be a string or not
    private String postion; //Maybe make an enum for positions or something?

    public Player(int player_id, int team_id, String fName, String lName, String dob, String postion) {
        this.player_id = player_id;
        this.team_id = team_id;
        this.fName = fName;
        this.lName = lName;
        this.dob = dob;
        this.postion = postion;
    }

    public Player(String[] data){
        this.player_id = Integer.parseInt(data[0]);
        this.team_id = Integer.parseInt(data[1]);
        this.fName = data[2];
        this.lName = data[3];
        this.dob = data[4];
        this.postion = data[5];
    }

    public int getPlayer_id() {
        return player_id;
    }

    public String getPostion() {
        return postion;
    }

    public int getTeam_id() {
        return team_id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getDob() {
        return dob;
    }
}
