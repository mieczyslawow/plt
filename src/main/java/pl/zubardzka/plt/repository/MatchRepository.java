package pl.zubardzka.plt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zubardzka.plt.domain.Forum;
import pl.zubardzka.plt.domain.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Integer> {

	List<Match> getAllByForum(Forum forum);

	Match getById(Integer id);
}
