package pl.zubardzka.plt.domain;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

	@Id
	private Integer id;

	private String name;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
