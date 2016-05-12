package db;

import org.h2.jdbc.JdbcSQLException;
import tables.*;

import java.sql.*;

/**
 * This is a sample main program. 
 * You will create something similar
 * to run your database.
 * 
 * @author scj
 *
 * Created by The Data Principlists.
 */
public class H2DB {

	//The connection to the database
	private Connection conn;
	private final String PATH = System.getProperty("user.dir") + "\\src\\csv\\";

	/**
	 * Create a database connection with the given params
	 * @param location: path of where to place the database
	 * @param user: user name for the owner of the database
	 * @param password: password of the database owner
	 */
	public void createConnection(String location,
			                     String user,
			                     String password){
		try {
			
			//This needs to be on the front of your location
			String url = "jdbc:h2:" + location;
			
			//This tells it to use the h2 driver
			Class.forName("org.h2.Driver");
			
			//creates the connection
			conn = DriverManager.getConnection(url,
					                           user,
					                           password);
		} catch (SQLException | ClassNotFoundException e) {
			//You should handle this better
            System.err.println("Database already in use! -- Please close existing connections");
            System.exit(1);
        }
	}
	
	/**
	 * just returns the connection
	 * @return: returns class level connection
	 */
	public Connection getConnection(){
		return conn;
	}
	
	/**
	 * When your database program exits
	 * you should close the connection
	 */
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Starts and runs the database
	 */
	public void init() {
		// Hard drive location of the database
		String location = System.getProperty("user.dir") + "\\basketball";
		String user = "sa";
		String password = "";

		//Create the database connections, basically makes the database
		createConnection(location, user, password);
	}

    public boolean initTable(String table) {
        if (table.equals("PLAYERS")) {
            try {

                /**
                 * Creates a sample objects.Player table
                 * and populates it from a csv file
                 */
                PlayerTable.createPlayerTable(getConnection());
                PlayerTable.populatePlayerTableFromCSV(getConnection(),
                        PATH + "players.csv");
            }
            catch (JdbcSQLException jde) {
                if (jde.toString().contains("Unique index")) {
                    return false;
                } else {
                    jde.printStackTrace();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        else if (table.equals("PLAYER_SEASONS")) {
            try {

                /**
                 * Creates a player seasons table
                 * and populates it from a csv file
                 */
                PlayerSeasonTable.createPlayerSeasonTable(getConnection());
                PlayerSeasonTable.populatePlayerSeasonTableFromCSV(
                        getConnection(),
                        PATH + "playerseasons.csv");
            } catch (JdbcSQLException jde) {
                if(jde.toString().contains("Unique index")) {
                    return false;
                }
                else {
                    jde.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else if (table.equals("COACHES")) {
            try {

                /**
                 * Creates a sample coach table
                 * and populates it from a csv file
                 */
                CoachTable.createCoachTable(getConnection());
                CoachTable.populateCoachTableFromCSV(
                        getConnection(),
                        PATH + "coaches.csv");
            } catch (JdbcSQLException jde) {
                if(jde.toString().contains("Unique index")) {
                    return false;
                }
                else {
                    jde.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else if(table.equals("TEAMS")) {
            try {

                /**
                 * Creates a sample team table
                 * and populates it from a csv file
                 */
                TeamTable.createTeamTable(getConnection());
                TeamTable.populateTeamTableFromCSV(
                        getConnection(),
                        PATH + "teams.csv");
            } catch (JdbcSQLException jde) {
                if(jde.toString().contains("Unique index")) {
                    return false;
                }
                else {
                    jde.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else if (table.equals("COACH_SEASONS")) {
            try {

                /**
                 * Creates a sample coach seasons table
                 * and populates it from a csv file
                 */
                CoachSeasonTable.createCoachSeasonTable(getConnection());
                CoachSeasonTable.populateCoachSeasonTableFromCSV(
                        getConnection(),
                        PATH + "coachseasons.csv");
            } catch (JdbcSQLException jde) {
                if(jde.toString().contains("Unique index")) {
                    return false;
                }
                else {
                    jde.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else if (table.equals("TEAM_SEASONS")) {
            try {

                /**
                 * Creates a sample team seasons table
                 * and populates it from a csv file
                 */
                TeamSeasonTable.createTeamSeasonTable(getConnection());
                TeamSeasonTable.populateTeamSeasonTableFromCSV(
                        getConnection(),
                        PATH + "teamseasons.csv");
            } catch (JdbcSQLException jde) {
                if(jde.toString().contains("Unique index")) {
                    return false;
                }
                else {
                    jde.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Drops the table provided it exists
     * @param table to be dropped
     * @return true/false
     */
    public boolean dropTable(String table) {
        try {
            String query = "DROP TABLE " + table + ";";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Displays all tables available for the given DB.
     */
    public ResultSet showTables() {
        try {
            String query = "SHOW TABLES;";
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
