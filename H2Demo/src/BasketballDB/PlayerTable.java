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
     * @param conn
     * @param fileName
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
        String sql = createPersonInsertSQL(players);

        //Create and execute the sql statement
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }
}
