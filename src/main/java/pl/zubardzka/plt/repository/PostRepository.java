package pl.zubardzka.plt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zubardzka.plt.domain.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

}
