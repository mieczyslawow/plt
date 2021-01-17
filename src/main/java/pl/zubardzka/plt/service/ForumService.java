package pl.zubardzka.plt.service;

import java.util.List;

import pl.zubardzka.plt.domain.Forum;

public interface ForumService {

	Forum save(Forum forum);

	List<Forum> getAll();

	int getCount();

	boolean existInDB(Forum forum);

}
