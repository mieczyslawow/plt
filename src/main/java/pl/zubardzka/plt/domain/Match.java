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
}
