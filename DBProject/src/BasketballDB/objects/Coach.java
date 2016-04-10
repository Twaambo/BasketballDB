package BasketballDB.objects;

import java.lang.Integer;

/**
 * Hold data on a single coach over their career
 * @author Noah Shiotani
 */
public class Coach {
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
}
