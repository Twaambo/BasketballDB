package tables;

import db.QueryResult;
import objects.CoachSeason;
import objects.TeamSeason;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeamSeasonTable {

    /**
     * Reads a csv file for team data and adds them to the team season table
     * <p>
     * the team table must already exist
     *
     * @param conn     the database connection
     * @param fileName the name of the csv file to read from
     * @throws SQLException
     */
    public static void populateTeamSeasonTableFromCSV(Connection conn, String fileName) throws SQLException {

        //ArrayList to store the teams that are later added to the table
        ArrayList<TeamSeason> teamSeasons = new ArrayList<TeamSeason>();

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
                teamSeasons.add(new TeamSeason(data));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create an sql query to insert all the teams into the table at once
        String sql = createTeamSeasonInsertSQL(teamSeasons);

        //Create and execute the sql statement
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }
	
    public static void createTeamSeasonTable(Connection conn){
        try{
            String query = "CREATE TABLE IF NOT EXISTS team_season("
                    + "TEAM_ID VARCHAR(255),"
                    + "SEASON_YEAR VARCHAR(255),"
                    + "LEAGUE VARCHAR(255),"
                    + "WINS INT,"
                    + "LOSSES INT,"
                    + "FINISH INT,"
                    + "CONSTRAINT pk_TeamSeasonID PRIMARY KEY (TEAM_ID, SEASON_YEAR, LEAGUE)"
                    + ");" ;
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
	
    public static String createTeamSeasonInsertSQL(ArrayList<TeamSeason> teamSeasons){
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO team_season (SEASON_YEAR, TEAM_ID, LEAGUE, WINS, LOSSES, FINISH) VALUES");

        for(int i = 0; i < teamSeasons.size(); i++){
            TeamSeason ts = teamSeasons.get(i);
            sb.append(String.format("(%s,\'%s\',\'%s\',\'%d\',\'%d\',\'%d\')",
                    ts.getSeasonYear(), ts.getTeamID(), ts.getLeague(), ts.getWin(), ts.getLoss(), ts.getFinish()));

            if(i != teamSeasons.size() - 1){
                sb.append(",");
            }
            else{
                sb.append(";");
            }
        }

        return sb.toString();
    }

    public static ArrayList<QueryResult> selectTeamSeason(Connection conn) {
        String query = "SELECT * FROM team_season;";
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
}
