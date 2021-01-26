package pl.zubardzka.plt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import pl.zubardzka.plt.domain.Forum;
import pl.zubardzka.plt.domain.MatchResult;
import pl.zubardzka.plt.repository.MatchResultRepository;
import pl.zubardzka.plt.service.MatchResultService;

@Service
public class MatchResultServiceImpl implements MatchResultService {

	private final MatchResultRepository matchRepository;

	public MatchResultServiceImpl(final MatchResultRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	@Override
	public List<MatchResult> getAll(final Forum forum) {
		return matchRepository.getAllByForumOrderById(forum);
	}
}
