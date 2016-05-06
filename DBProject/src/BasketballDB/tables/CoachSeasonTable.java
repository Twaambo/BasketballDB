package BasketballDB.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BasketballDB.objects.Coach;
import BasketballDB.objects.CoachSeason;

public class CoachSeasonTable {


    /**
     * Reads a csv file for coach season data nad adds them to the coach table
     *
     * the table must already exist
     *
     *
     * @param conn
     * @param fileName
     * @throws SQLException
     */
    public static void populateCoachSeasonTableFromCSV(Connection conn, String fileName) throws SQLException{

        //ArrayList to store the coach seasons that are added to the table later
        ArrayList<CoachSeason> coachSeasons = new ArrayList<CoachSeason>();

        //Read in the data
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                if(line.isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if(data[0].equals("year")) {
                    continue;
                }
                coachSeasons.add(new CoachSeason(data));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create an sql query to insert all the coachSeasons into the table at once
        String sql = createCoachSeasonInsertSQL(coachSeasons);

        //Create and execute the sql statement
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    public static void createCoachSeasonTable(Connection conn){
        try{
            String query = "CREATE TABLE IF NOT EXISTS coach_season("
                    + "SEASON_YEAR VARCHAR(255) PRIMARY KEY,"
                    + "COACH_ID VARCHAR(255),"
                    + "TEAM_ID VARCHAR(255),"
                    + "WINS INT,"
                    + "LOSSES INT"
                    + ");" ;
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
	
    public static String createCoachSeasonInsertSQL(ArrayList<CoachSeason> coachSeasons){
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO coach_season (SEASON_YEAR, COACH_ID, TEAM_ID, WINS, LOSSES) VALUES");

        for(int i = 0; i < coachSeasons.size(); i++){
            CoachSeason cs = coachSeasons.get(i);
            sb.append(String.format("(%s,\'%s\',\'%s\',\'%d\',\'%d\')",
                    cs.getSeasonYear(), cs.getCoachID(), cs.getTeamID(), cs.getWin(), cs.getLoss()));

            if(i != coachSeasons.size() - 1){
                sb.append(",");
            }
            else{
                sb.append(";");
            }
        }

        return sb.toString();
    }
	
}
