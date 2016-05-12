package objects;

import db.QueryResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Hold data on a single coach over their career
 * @author Noah Shiotani
 * Created by The Data Principlists.
 */
public class Coach implements QueryResult{
    public static final ArrayList<String> Parameters = new ArrayList() {{
        add("coach_id");
        add("firstName");
        add("lastName");
        add("careerWins");
        add("careerLosses");
    }};

    public static final ObservableList<String> ColHeaders =
            FXCollections.observableArrayList(
                    "COACH_ID",
                    "FIRST_NAME",
                    "LAST_NAME",
                    "CAREER_WINS",
                    "CAREER_LOSSES"
            );

    //Format: first 5 letters of last name, first 2 letters of first name, then two digits
    //e.g. Rich Adelman would be ADELMRI01
    //Digits are presumably there in case people have the same name
    private String coach_id;

    private String firstName;
    private String lastName;
    private int careerWins;
    private int careerLosses;

    public Coach(String coach_id, String firstName, String lastName, int careerWins, int careerLosses) {
        this.coach_id = coach_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.careerWins = careerWins;
        this.careerLosses = careerLosses;
    }

    public Coach(String[] data){
        this.coach_id = data[0];
        this.firstName = data[1];
        this.lastName = data[2];
        this.careerWins = Integer.parseInt(data[3]);
        this.careerLosses = Integer.parseInt(data[4]);
    }

    public String getCoach_id() {
        return coach_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCareerWins() {
        return careerWins;
    }

    public int getCareerLosses() {
        return careerLosses;
    }

    @Override
    public ArrayList<String> getParameters() {
        return Parameters;
    }
}
