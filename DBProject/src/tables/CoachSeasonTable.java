package tables;


import db.Criteria;
import db.QueryResult;
import objects.Coach;
import objects.CoachSeason;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                    + "COACH_ID VARCHAR(255),"
                    + "SEASON_YEAR VARCHAR(255),"
                    + "TEAM_ID VARCHAR(255),"
                    + "WINS INT,"
                    + "LOSSES INT,"
                    + "CONSTRAINT pk_CoachSeasonID PRIMARY KEY (COACH_ID, SEASON_YEAR, TEAM_ID)"
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

    public static ArrayList<QueryResult> selectCoachSeasons(Connection conn) {
        String query = "SELECT * FROM coach_season;";
        ArrayList<QueryResult> results = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                results.add(new CoachSeason(result.getString(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static ArrayList<QueryResult> selectCoachSeasons(Connection conn, ArrayList<Criteria> criterias) {
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT * FROM coach_season WHERE ");

        String border = "";
        for(Criteria crit : criterias) {
            sb.append(border);
            sb.append(crit.getParameter() + "='" + crit.getValue() + "'");
            border = ",";
        }
        sb.append(";");

        ArrayList<QueryResult> results = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sb.toString());

            while(result.next()){
                results.add(new CoachSeason(result.getString(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
	
}
