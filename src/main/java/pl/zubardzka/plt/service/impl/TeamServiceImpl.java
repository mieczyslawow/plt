package pl.zubardzka.plt.service.impl;

import org.springframework.stereotype.Service;
import pl.zubardzka.plt.domain.Team;
import pl.zubardzka.plt.repository.TeamRepository;
import pl.zubardzka.plt.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	private final TeamRepository teamRepository;

	public TeamServiceImpl(final TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Override
	public Team getByName(final String name) {
		return teamRepository.getByName(name);
	}
}
