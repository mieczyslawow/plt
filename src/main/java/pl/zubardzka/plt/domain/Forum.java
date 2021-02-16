package pl.zubardzka.plt.domain;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "forum")
public class Forum {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private String link;

	private boolean read;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Match> matches;

	public Forum() {
	}

	public Forum(final String name, final String link) {
		this();
		this.name = name;
		this.link = link;
	}

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

	public String getLink() {
		return link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(final boolean read) {
		this.read = read;
	}

	public void setMatches(final List<Match> matches) {
		this.matches = matches;
	}

	@Override
	public String toString() {
		return "Forum{" + "id=" + id + ", name='" + name + '\'' + ", link='" + link + '\'' + '}';
	}
}
