package objects;

import db.QueryResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by The Data Principlists.
 */
public class PlayerSeason implements QueryResult {
	public static final ArrayList<String> Parameters = new ArrayList() {{
		add("seasonYear");
		add("playerID");
		add("teamID");
		add("league");
		add("PPG");
		add("RPG");
		add("APG");
	}};

	public static final ObservableList<String> ColHeaders =
			FXCollections.observableArrayList(
					"SEASON_YEAR",
					"PLAYER_ID",
					"TEAM_ID",
					"LEAGUE",
					"PPG",
					"RPG",
					"APG"
			);
	
	private String seasonYear;
	private String playerID;
	private String teamID;
	private String league;
	private Float PPG;
	private Float RPG;
	private Float APG;

	
	public PlayerSeason(String seasonYear, String playerID, String teamID,
			String league, Float PPG, Float RPG, Float APG) {
		super();
		this.seasonYear = seasonYear;
		this.playerID = playerID;
		this.teamID = teamID;
		this.league= league;
		this.PPG = PPG;
		this.RPG = RPG;
		this.APG = APG;
	}

	public PlayerSeason(String[] data) {
		this.seasonYear = data[0];
		this.playerID = data[1];
		this.teamID = data[2];
		this.league = (data[3]);
		this.PPG = Float.parseFloat(data[4]);
		this.RPG = Float.parseFloat(data[5]);
		this.APG = Float.parseFloat(data[6]);
	}

	public String getSeasonYear() {
		return seasonYear;
	}

	public void setSeasonYear(String seasonYear) {
		this.seasonYear = seasonYear;
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public Float getRPG() {
		return RPG;
	}

	public void setRPG(Float RPG) {
		this.RPG = RPG;
	}

	public Float getAPG() {
		return APG;
	}

	public void setAPG(Float APG) {
		this.APG = APG;
	}

	public Float getPPG() {
		return PPG;
	}

	public void setPPG(Float pPG) {
		PPG = pPG;
	}

	@Override
	public ArrayList<String> getParameters() {
		return Parameters;
	}
}
