package pl.zubardzka.plt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zubardzka.plt.domain.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

	Player getByName(String name);
}
