package pl.zubardzka.plt.service;

import java.util.List;

import pl.zubardzka.plt.domain.Forum;
import pl.zubardzka.plt.domain.Match;

public interface MatchService {

	Match save(final Match m);

	List<Match> getAll(final Forum forum);
}
