package pl.zubardzka.plt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import pl.zubardzka.plt.domain.Forum;
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

	@Override
	public List<Match> getAll(final Forum forum) {
		return matchRepository.getAllByForum(forum);
	}

	@Override
	public Match getById(final Integer id) {
		return matchRepository.getById(id);
	}
}
