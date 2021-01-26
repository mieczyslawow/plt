package pl.zubardzka.plt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zubardzka.plt.domain.Forum;
import pl.zubardzka.plt.domain.MatchResult;

@Repository
public interface MatchResultRepository extends CrudRepository<MatchResult, Integer> {

	List<MatchResult> getAllByForumOrderById(Forum forum);
}
