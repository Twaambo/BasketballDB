package BasketballDB.objects;

/**
 * Hold data on a single player
 * @author Noah Shiotani
 */
public class Player {
    private int player_id;
    private String fName;
    private String lName;
    private String dob;
    private String postion; //Maybe make an enum for positions or something?

    public Player(int player_id, String fName, String lName, String dob, String postion) {
        this.player_id = player_id;
        this.fName = fName;
        this.lName = lName;
        this.dob = dob;
        this.postion = postion;
    }

    public Player(String[] data){
        this.player_id = Integer.parseInt(data[0]);
        this.fName = data[1];
        this.lName = data[2];
        this.dob = data[3];
        this.postion = data[4];
    }

    public int getPlayer_id() {
        return player_id;
    }

    public String getPostion() {
        return postion;
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
