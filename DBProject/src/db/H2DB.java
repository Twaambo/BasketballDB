package db;

import org.h2.jdbc.JdbcSQLException;
import tables.CoachSeasonTable;
import tables.CoachTable;
import tables.PlayerTable;
import tables.TeamTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is a sample main program. 
 * You will create something similar
 * to run your database.
 * 
 * @author scj
 *
 */
public class H2DB {

	//The connection to the database
	private Connection conn;
	private final String PATH = System.getProperty("user.dir") + "\\src\\csv\\";
	public static Connection test;
	
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
			test = conn;
		} catch (SQLException | ClassNotFoundException e) {
			//You should handle this better
			e.printStackTrace();
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
				// TODO: Total hack, get rid of this
				// no-op
			} else {
				jde.printStackTrace();
			}
		}
			catch(Exception e) {
			e.printStackTrace();
		}

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
				// TODO: Total hack, get rid of this
				// no-op
			}
			else {
				jde.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

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
				// TODO: Total hack, get rid of this
				// no-op
			}
			else {
				jde.printStackTrace();
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			/**
			 * Creates a sample team table
			 * and populates it from a csv file
			 */
			CoachSeasonTable.createCoachSeasonTable(getConnection());
			CoachSeasonTable.populateCoachSeasonTableFromCSV(
					getConnection(),
					PATH + "coachseasons.csv");
		} catch (JdbcSQLException jde) {
			if(jde.toString().contains("Unique index")) {
				// TODO: Total hack, get rid of this
				// no-op
			}
			else {
				jde.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
