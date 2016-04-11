package BasketballDB.objects;

public class CoachSeason {
	
	private String seasonYear;
	private String coachID;
	private String teamID;
	private Integer win;
	private Integer loss;
	
	public CoachSeason(String seasonYear, String coachID, String teamID,
			Integer win, Integer loss) {
		super();
		this.seasonYear = seasonYear;
		this.coachID = coachID;
		this.teamID = teamID;
		this.win = win;
		this.loss = loss;
	}
	
	public CoachSeason(String[] data) {
		this.seasonYear = data[0];
		this.coachID = data[1];
		this.teamID = data[2];
		this.win = Integer.parseInt(data[3]);
		this.loss = Integer.parseInt(data[4]);
	}

	public String getSeasonYear() {
		return seasonYear;
	}

	public void setSeasonYear(String seasonYear) {
		this.seasonYear = seasonYear;
	}

	public String getCoachID() {
		return coachID;
	}

	public void setCoachID(String coachID) {
		this.coachID = coachID;
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

}