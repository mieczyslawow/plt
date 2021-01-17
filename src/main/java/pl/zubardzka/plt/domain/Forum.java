package pl.zubardzka.plt.domain;

import javax.persistence.*;

@Entity
@Table(name = "forum")
public class Forum {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private String link;

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

	@Override
	public String toString() {
		return "Forum{" + "id=" + id + ", name='" + name + '\'' + ", link='" + link + '\'' + '}';
	}
}
