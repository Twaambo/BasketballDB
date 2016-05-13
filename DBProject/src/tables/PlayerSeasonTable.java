package tables;


import db.Criteria;
import db.QueryResult;
import objects.CoachSeason;
import objects.Player;
import objects.PlayerSeason;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                    + "PLAYER_ID VARCHAR(255),"
                    + "SEASON_YEAR VARCHAR(255),"
                    + "TEAM_ID VARCHAR(255),"
                    + "LEAGUE VARCHAR(255),"
                    + "PPG FLOAT(6),"
                    + "RPG FLOAT(6),"
                    + "APG FLOAT(6),"
                    + "CONSTRAINT pk_PlayerSeasonID PRIMARY KEY (PLAYER_ID, SEASON_YEAR, TEAM_ID)"
                    + ");" ;
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
	
    public static String createPlayerSeasonInsertSQL(ArrayList<PlayerSeason> playerSeasons){
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO player_season (PLAYER_ID, SEASON_YEAR, TEAM_ID, LEAGUE, PPG, RPG, APG) VALUES");

        for(int i = 0; i < playerSeasons.size(); i++){
            PlayerSeason ps = playerSeasons.get(i);
            sb.append(String.format("(\'%s\',\'%s\',\'%s\',\'%s\',\'%f\',\'%f\',\'%f\')",
                    ps.getSeasonYear(), ps.getPlayerID(), ps.getTeamID(), ps.getLeague(), ps.getPPG(), ps.getRPG(), ps.getAPG()));

            if(i != playerSeasons.size() - 1){
                sb.append(",");
            }
            else{
                sb.append(";");
            }
        }

        return sb.toString();
    }

    public static ArrayList<QueryResult> selectPlayerSeasons(Connection conn) {
        String query = "SELECT * FROM player_season;";
        ArrayList<QueryResult> results = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                results.add(new PlayerSeason(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getFloat(5), result.getFloat(6), result.getFloat(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static ArrayList<QueryResult> selectPlayerSeasons(Connection conn, ArrayList<Criteria> criterias) {
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT * FROM player_season WHERE ");

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
                results.add(new PlayerSeason(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getFloat(5), result.getFloat(6), result.getFloat(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

}
