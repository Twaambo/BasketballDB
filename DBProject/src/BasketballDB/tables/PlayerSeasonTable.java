package BasketballDB.tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BasketballDB.objects.Coach;
import BasketballDB.objects.CoachSeason;
import BasketballDB.objects.PlayerSeason;

public class PlayerSeasonTable {
	
    public static void createPlayerSeasonTable(Connection conn){
        try{
            String query = "CREATE TABLE IF NOT EXISTS player_season("
                    + "SEASON_YEAR VARCHAR(255) PRIMARY KEY,"
                    + "PLAYER_ID VARCHAR(255),"
                    + "TEAM_ID VARCHAR(255),"
                    + "MVP BOOLEAN,"
                    + "PPG FLOAT(6, 3),"
                    + "PLAYER_NUMBER INT"
                    + ");" ;
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
	
    public static String createPlayerSeasonInsertSQL(ArrayList<PlayerSeason> playerSeasons){
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO player_season (SEASON_YEAR, PLAYER_ID, TEAM_ID, MVP, PPG, PLAYER_NUMBER) VALUES");

        for(int i = 0; i < playerSeasons.size(); i++){
            PlayerSeason ps = playerSeasons.get(i);
            sb.append(String.format("(%s,\'%s\',\'%s\',\'%b\',\'%f\',\'%d\')",
                    ps.getSeasonYear(), ps.getPlayerID(), ps.getTeamID(), ps.getMVP(), ps.getPPG(), ps.getPlayerNumber()));

            if(i != playerSeasons.size() - 1){
                sb.append(",");
            }
            else{
                sb.append(";");
            }
        }

        return sb.toString();
    }
	
}
