package pl.zubardzka.plt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import pl.zubardzka.plt.domain.Forum;
import pl.zubardzka.plt.repository.ForumRepository;
import pl.zubardzka.plt.service.ForumService;

@Service
public class ForumServiceImpl implements ForumService {

	private final ForumRepository forumRepository;

	public ForumServiceImpl(final ForumRepository forumRepository) {
		this.forumRepository = forumRepository;
	}

	@Override
	public Forum save(final Forum forum) {
		return forumRepository.save(forum);
	}

	@Override
	public List<Forum> getAll() {
		List<Forum> result = new ArrayList<>();
		forumRepository.findAll().forEach(result::add);
		return result;
	}

	@Override
	public int getCount() {
		return forumRepository.countAllQuery();
	}

	@Override
	public boolean existInDB(final Forum forum) {
		return forumRepository.existsForumExactCustomQuery(forum.getLink(), forum.getName());
	}

	@Override
	public Forum getById(final Integer id) {
		return forumRepository.getById(id);
	}
}
