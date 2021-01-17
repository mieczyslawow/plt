package pl.zubardzka.plt.domain;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {

	@Id
	private Integer id;

	private String name;

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
}
