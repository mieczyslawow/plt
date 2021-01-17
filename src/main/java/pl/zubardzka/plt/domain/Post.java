package pl.zubardzka.plt.domain;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {

	@Id
	private Integer id;

	@OneToOne
	private Player player;

	@ManyToOne
	@JoinColumn(name = "forum_id", nullable = false)
	private Forum forum;

	@OneToMany
	@JoinColumn(name = "id")
	private List<Match> matches;

}
