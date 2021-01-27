package pl.zubardzka.plt.domain;

import javax.persistence.*;

@Entity
@Table(name = "matchday_score")
public class MatchdayScore {

	boolean extraBet;
	Integer extraBetScore;
	Integer matchesScore;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "player_id", referencedColumnName = "id")
	private Player player;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "forum_id", referencedColumnName = "id")
	private Forum forum;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(final Player player) {
		this.player = player;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(final Forum forum) {
		this.forum = forum;
	}

	public boolean isExtraBet() {
		return extraBet;
	}

	public void setExtraBet(final boolean extraBet) {
		this.extraBet = extraBet;
	}

	public Integer getExtraBetScore() {
		return extraBetScore;
	}

	public void setExtraBetScore(final Integer extraBetScore) {
		this.extraBetScore = extraBetScore;
	}

	public Integer getMatchesScore() {
		return matchesScore;
	}

	public void setMatchesScore(final Integer matchesScore) {
		this.matchesScore = matchesScore;
	}
}
