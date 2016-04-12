package BasketballDB.tables;

import BasketballDB.objects.Coach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class to make and edit the coach table
 * @author Noah Shiotani
 */
public class CoachTable {

    /**
     * Reads a csv file for coach data and adds them to the coach table
     *
     * the table must already exist
     *
     * @param conn     the database conenction
     * @param fileName name of the csv file to read from
     * @throws SQLException
     */
    public static void populateCoachTableFromCSV(Connection conn, String fileName) throws SQLException {

        //ArrayList to store the players that are later added to the table
        ArrayList<Coach> coaches = new ArrayList<Coach>();

        //Read in the data
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                if(line.isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if(data[0].equals("cid")) {
                    continue;
                }
                coaches.add(new Coach(data));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create an sql query to insert all the coaches into the table at once
        String sql = createCoachInsertSQL(coaches);

        //Create and execute the sql statement
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    /**
     * Create a coach table if it does not exist
     * @param conn the database connection
     */
    public static void createCoachTable(Connection conn){
        try{
            String query = "CREATE TABLE IF NOT EXISTS coaches("
                    + "COACH_ID VARCHAR(255) PRIMARY KEY,"
                    + "FIRST_NAME VARCHAR(255),"
                    + "LAST_NAME VARCHAR(255),"
                    + "CAREER_WINS INT,"
                    + "CAREER_LOSSES INT,"
                    + ");" ;
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Creates an SQL query to add coaches to the coach table from an array list
     * @param coaches list of coaches to add
     * @return the SQL query as a string
     */
    public static String createCoachInsertSQL(ArrayList<Coach> coaches){
        StringBuilder sb = new StringBuilder();

        //The start of the statement that tells it the order of attributes
        sb.append("INSERT INTO coaches (COACH_ID, FIRST_NAME, LAST_NAME, CAREER_WINS, CAREER_LOSSES) VALUES");

        for(int i = 0; i < coaches.size(); i++){
            Coach c = coaches.get(i);
            sb.append(String.format("(\'%s\',\'%s\',\'%s\',\'%d\', \'%d\')",
                    c.getCoach_id(), c.getFirstName(), c.getLastName(), c.getCareerWins(), c.getCareerLosses()));

            //If it's the last list, add a semi-colon to end the statement
            if(i != coaches.size() - 1){
                sb.append(",");
            }
            else{
                sb.append(";");
            }
        }

        return sb.toString();
    }

    //addCoach

    //other query stuff
}
