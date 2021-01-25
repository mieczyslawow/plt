package pl.zubardzka.plt.service.impl;

import org.springframework.stereotype.Service;
import pl.zubardzka.plt.domain.Match;
import pl.zubardzka.plt.repository.MatchRepository;
import pl.zubardzka.plt.service.MatchService;

@Service
public class MatchServiceImpl implements MatchService {

	private final MatchRepository matchRepository;

	public MatchServiceImpl(final MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	@Override
	public Match save(final Match m) {
		return matchRepository.save(m);
	}
}
