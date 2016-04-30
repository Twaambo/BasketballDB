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
import BasketballDB.objects.Player;
import BasketballDB.objects.PlayerSeason;

public class PlayerSeasonTable {

    /**
     * Reads a csv file for player data and adds them to the player season table
     *
     * The table must already exist
     *
     * @param conn the database conenction
     * @param fileName name of the csv file to read from
     * @throws SQLException
     */
    public static void populatePlayerSeasonTableFromCSV(Connection conn,
                                                  String fileName)
            throws SQLException{

        //ArrayList to store the players that are later added to the table
        ArrayList<PlayerSeason> playerSeasons = new ArrayList<PlayerSeason>();

        //Read in the data
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = br.readLine()) != null){
                if(line.isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if(data[0].equals("pid")) {
                    continue;
                }
                playerSeasons.add(new PlayerSeason(data));
            }
            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        //Create an sql query to insert all the players into the table at once
        String sql = createPlayerSeasonInsertSQL(playerSeasons);

        //Create and execute the sql statement
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }
	
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
