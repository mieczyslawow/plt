package pl.zubardzka.plt.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import pl.zubardzka.plt.domain.Post;
import pl.zubardzka.plt.repository.PostRepository;
import pl.zubardzka.plt.service.PostService;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;

	public PostServiceImpl(final PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public Post save(final Post post) {
		return postRepository.save(post);
	}
}
