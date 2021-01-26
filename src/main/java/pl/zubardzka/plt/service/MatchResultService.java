package pl.zubardzka.plt.service;

import java.util.List;

import pl.zubardzka.plt.domain.Forum;
import pl.zubardzka.plt.domain.MatchResult;

public interface MatchResultService {

	List<MatchResult> getAll(final Forum forum);
}
