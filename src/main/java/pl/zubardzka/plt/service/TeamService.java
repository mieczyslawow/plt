package pl.zubardzka.plt.service;

import pl.zubardzka.plt.domain.Team;

public interface TeamService {

	Team getByName(String name);
}
