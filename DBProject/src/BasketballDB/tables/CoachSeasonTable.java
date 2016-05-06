package BasketballDB.tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BasketballDB.objects.Coach;
import BasketballDB.objects.CoachSeason;

public class CoachSeasonTable {
	
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
            sb.append(String.format("(\'%s\',\'%s\',\'%s\',%d,%d)",
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
