package BasketballDB;

import BasketballDB.objects.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class to make and edit the player table
 * @author Noah Shiotani
 */
public class PlayerTable {

    /**
     * Reads a csv file for player data and adds them to the player table
     *
     * The table must already exist
     *
     * @param conn the database conenction
     * @param fileName name of the csv file to read from
     * @throws SQLException
     */
    public static void populatePersonTableFromCSV(Connection conn,
                                                  String fileName)
                                                        throws SQLException{

        //ArrayList to store the players that are later added to the table
        ArrayList<Player> players = new ArrayList<Player>();

        //Read in the data
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                players.add(new Player(data));
            }
            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        //Create an sql query to insert all the players into the table at once
        String sql = createPlayerInsertSQL(players);

        //Create and execute the sql statement
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    /**
     * Create a player table if it does not exist
     * @param conn the database conenction
     */
    public static void createPlayerTable(Connection conn){
        try{
            String query = "CREATE TABLE IF NOT EXISTS players("
                    + "PLAYER_ID INT PRIMARY KEY,"
                    + "FIRST_NAME VARCHAR(255),"
                    + "LAST_NAME VARCHAR(255),"
                    + "DATE_OF_BIRTH TIMESTAMP,"
                    + "POSITION CHAR,"
                    + ");" ;
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * For each player in the player list, the player is added to the player table
     * @param players
     * @return
     */
    public static String createPlayerInsertSQL(ArrayList<Player> players){
        StringBuilder sb = new StringBuilder();

        //The start of the statement that tells it the order of the attributes
        sb.append("INSERT INTO players (PLAYER_ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, POSITION) VALUES");

        for(int i = 0; i < players.size(); i++){
            Player p = players.get(i);
            sb.append(String.format("(%d,\'%s\',\'%s\',\'%s\''%s')",
                    p.getPlayer_id(), p.getfName(), p.getlName(), p.getDob(), p.getPostion()));

            //If it's the last, add a semi-colon to end the statement
            if(i != players.size() - 1){
                sb.append(",");
            }
            else{
                sb.append(";");
            }
        }

        return sb.toString();
    }

    //addPlayer

    //other query stuff
}
