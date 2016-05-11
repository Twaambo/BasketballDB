package objects;

import db.QueryResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hold data on a single player
 * @author Noah Shiotani
 */
public class Player implements QueryResult {
    public static final ArrayList<String> Parameters = new ArrayList() {{
        add("player_id");
        add("firstName");
        add("lastName");
        add("dob");
        add("position");
    }};

    public static final ObservableList<String> ColHeaders =
            FXCollections.observableArrayList(
                    "player_id",
                    "firstName",
                    "lastName",
                    "dob",
                    "position"
            );

    //Format: first 5 letters of last name, first 2 letters of first name, then two digits
    //e.g. Rich Adelman would be ADELMRI01
    //Digits are presumably there in case people have the same name
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

    @Override
    public ArrayList<String> getParameters() {
        return Parameters;
    }
}
