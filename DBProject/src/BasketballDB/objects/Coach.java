package BasketballDB.objects;

import java.lang.Integer;

/**
 * Hold data on a single coach over their career
 * @author Noah Shiotani
 */
public class Coach {
    private int coach_id;
    private String firstName;
    private String lastName;
    private int careerWins;
    private int careerLosses;

    public Coach(int coach_id, String firstName, String lastName, int careerWins, int careerLosses) {
        this.coach_id = coach_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.careerWins = careerWins;
        this.careerLosses = careerLosses;
    }

    public Coach(String[] data){
        this.coach_id = Integer.parseInt(data[0]);
        this.firstName = data[1];
        this.lastName = data[2];
        this.careerWins = Integer.parseInt(data[3]);
        this.careerLosses = Integer.parseInt(data[4]);
    }

    public int getCoach_id() {
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
