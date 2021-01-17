package pl.zubardzka.plt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zubardzka.plt.domain.Forum;

@Repository
public interface ForumRepository extends CrudRepository<Forum, Integer> {

	@Query("select case when count(f)> 0 then true else false end from Forum f where f.link = :link AND f.name = :name")
	boolean existsCarExactCustomQuery(@Param("link") String link, @Param("name") String name);

	@Query("select count(f) from Forum f")
	int countAllQuery();
}
