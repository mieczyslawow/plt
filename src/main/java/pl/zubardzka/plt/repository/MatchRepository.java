package pl.zubardzka.plt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zubardzka.plt.domain.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Integer> {

}
