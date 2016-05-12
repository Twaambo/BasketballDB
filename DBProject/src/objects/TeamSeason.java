package objects;

import db.QueryResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by The Data Principlists.
 */
public class TeamSeason implements QueryResult {
	public static final ArrayList<String> Parameters = new ArrayList() {{
		add("seasonYear");
		add("teamID");
		add("league");
		add("win");
		add("loss");
		add("finish");
	}};

	public static final ObservableList<String> ColHeaders =
			FXCollections.observableArrayList(
					"SEASON_YEAR",
					"TEAM_ID",
					"LEAGUE",
					"WINS",
					"LOSSES",
					"FINISH"
			);

	private String seasonYear;
	private String teamID;
	private String league;
	private Integer win;
	private Integer loss;
	private Integer finish;
	
	public TeamSeason(String seasonYear, String teamID, String league,
			Integer win, Integer loss, Integer finish) {
		super();
		this.seasonYear = seasonYear;
		this.teamID = teamID;
		this.league = league;
		this.win = win;
		this.loss = loss;
		this.finish = finish;
	}
	
	public TeamSeason(String[] data) {
		this.seasonYear = data[0];
		this.teamID = data[1];
		this.league = data[2];
		this.win = Integer.parseInt(data[3]);
		this.loss = Integer.parseInt(data[4]);
		
		this.finish = Integer.parseInt(data[5]);
	}

	public String getSeasonYear() {
		return seasonYear;
	}

	public void setSeasonYear(String seasonYear) {
		this.seasonYear = seasonYear;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public Integer getWin() {
		return win;
	}

	public void setWin(Integer win) {
		this.win = win;
	}

	public Integer getLoss() {
		return loss;
	}

	public void setLoss(Integer loss) {
		this.loss = loss;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public Integer getFinish() {
		return finish;
	}

	public void setFinish(Integer finish) {
		this.finish = finish;
	}

	@Override
	public ArrayList<String> getParameters() {
		return Parameters;
	}
}
