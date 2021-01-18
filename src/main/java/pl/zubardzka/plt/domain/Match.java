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
	@JoinColumn(name = "home_team", referencedColumnName = "id")
	private Team awayTeam;

	private int homeScore;

	private int awayScore;

	private boolean hit;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

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

	public boolean isHit() {
		return hit;
	}

	public void setHit(final boolean hit) {
		this.hit = hit;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(final Post post) {
		this.post = post;
	}
}
