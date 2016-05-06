package BasketballDB.tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BasketballDB.objects.Coach;
import BasketballDB.objects.CoachSeason;
import BasketballDB.objects.TeamSeason;

public class TeamSeasonTable {
	
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
            sb.append(String.format("(\'%s\',\'%s\',\'%s\',\'%d\',%d,%d)",
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
