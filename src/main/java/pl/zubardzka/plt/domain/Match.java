package pl.zubardzka.plt.domain;

import javax.persistence.*;

@Entity
@Table(name = "matches")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "home_team", referencedColumnName = "id")
	private Team homeTeam;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "away_team", referencedColumnName = "id")
	private Team awayTeam;

	private int homeScore;

	private int awayScore;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "forum_id", referencedColumnName = "id")
	private Forum forum;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "player_id", referencedColumnName = "id")
	private Player player;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "matches_result_id", referencedColumnName = "id")
	private MatchResult matchResult;

	private Integer score;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(final Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(final Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(final int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(final int awayScore) {
		this.awayScore = awayScore;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(final Forum forum) {
		this.forum = forum;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(final Player player) {
		this.player = player;
	}

	public MatchResult getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(final MatchResult matchResult) {
		this.matchResult = matchResult;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(final Integer score) {
		this.score = score;
	}
}
