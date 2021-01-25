package pl.zubardzka.plt.domain;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne
	private Player player;

	@ManyToOne
	@JoinColumn(name = "forum_id", nullable = false)
	private Forum forum;

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id")
	private List<Match> matches;

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

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(final List<Match> matches) {
		this.matches = matches;
	}
}
