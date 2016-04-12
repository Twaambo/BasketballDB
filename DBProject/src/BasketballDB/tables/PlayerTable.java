package BasketballDB.tables;

import BasketballDB.objects.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
                if(line.isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if(data[0].equals("pid")) {
                    continue;
                }
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
     * @param conn the database connection
     */
    public static void createPlayerTable(Connection conn){
        try{
            String query = "CREATE TABLE IF NOT EXISTS players("
                    + "PLAYER_ID VARCHAR(255) PRIMARY KEY,"
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
     * Creates an SQL query to add players to the player table from an array list
     * @param players list of players to add
     * @return the SQL query as a string
     */
    public static String createPlayerInsertSQL(ArrayList<Player> players){
        StringBuilder sb = new StringBuilder();

        //The start of the statement that tells it the order of the attributes
        sb.append("INSERT INTO players (PLAYER_ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, POSITION) VALUES");

        for(int i = 0; i < players.size(); i++){
            Player p = players.get(i);
            sb.append(String.format(" (\'%s\',\'%s\',\'%s\',\'%s\', \'%s\')",
                    p.getPlayer_id(), p.getFirstName(), p.getLastName(), p.getDob(), p.getPosition()));

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

    public static void printPersonTable(Connection conn){
        String query = "SELECT * FROM players;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            System.out.println("hellO");

            while(result.next()){
                System.out.printf("Person %s: %s %s %s\n",
                        result.getString(1),
                        result.getString(2),
                        result.getString(4),
                        result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //addPlayer

    //other query stuff
}
