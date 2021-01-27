package pl.zubardzka.plt.domain;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "nick_name")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Match> matches;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(final List<Match> matches) {
		this.matches = matches;
	}
}
