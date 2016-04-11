package BasketballDB.objects;

public class PlayerSeason {
	
	private String seasonYear;
	private String playerID;
	private String teamID;
	private Boolean MVP;
	private Float PPG;
	private Integer playerNumber;
	
	
	public PlayerSeason(String seasonYear, String playerID, String teamID,
			Boolean MVP, Float PPG, Integer playerNumber) {
		super();
		this.seasonYear = seasonYear;
		this.playerID = playerID;
		this.teamID = teamID;
		this.MVP = MVP;
		this.PPG = PPG;
		this.playerNumber = playerNumber;
	}

	public PlayerSeason(String[] data) {
		this.seasonYear = data[0];
		this.playerID = data[1];
		this.teamID = data[2];
		this.MVP = Boolean.parseBoolean(data[3]);
		this.PPG = Float.parseFloat(data[4]);
		this.playerNumber = Integer.parseInt(data[5]);
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

	public Boolean getMVP() {
		return MVP;
	}

	public void setMVP(Boolean mVP) {
		MVP = mVP;
	}

	public Float getPPG() {
		return PPG;
	}

	public void setPPG(Float pPG) {
		PPG = pPG;
	}

	public Integer getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(Integer playerNumber) {
		this.playerNumber = playerNumber;
	}

}
