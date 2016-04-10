package BasketballDB.objects;

/**
 * Hold data on a single player
 * @author Noah Shiotani
 */
public class Player {
    private String player_id;
    private String firstName;
    private String lastName;
    private String dob;
    private String position; //Maybe make an enum for positions or something?

    public Player(String player_id, String firstName, String lastName, String dob, String position) {
        this.player_id = player_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.position = position;
    }

    public Player(String[] data){
        this.player_id = data[0];
        this.firstName = data[1];
        this.lastName = data[2];
        this.dob = data[3];
        this.position = data[4];
    }

    public String getPlayer_id() {
        return player_id;
    }

    public String getPosition() {
        return position;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }
}
