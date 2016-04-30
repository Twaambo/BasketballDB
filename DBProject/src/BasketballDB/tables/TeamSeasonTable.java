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
import BasketballDB.objects.Team;
import BasketballDB.objects.TeamSeason;

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
                if(data[0].equals("tid")) {
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
                    + "SEASON_YEAR VARCHAR(255) PRIMARY KEY,"
                    + "TEAM_ID VARCHAR(255),"
                    + "LEAGUE VARCHAR(255),"
                    + "WINS INT,"
                    + "LOSSES INT,"
                    + "FINISH INT);" ;
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
	
}
