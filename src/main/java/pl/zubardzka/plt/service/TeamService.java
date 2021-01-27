package pl.zubardzka.plt.service;

import java.util.List;

import pl.zubardzka.plt.domain.Team;

public interface TeamService {

	Team getByName(String name);

	List<Team> getAll();
}
