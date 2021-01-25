package pl.zubardzka.plt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zubardzka.plt.domain.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {

	Team getByName(String name);
}
