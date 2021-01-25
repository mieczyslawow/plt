package pl.zubardzka.plt.service.impl;

import org.springframework.stereotype.Service;
import pl.zubardzka.plt.domain.Player;
import pl.zubardzka.plt.repository.PlayerRepository;
import pl.zubardzka.plt.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	private final PlayerRepository playerRepository;

	public PlayerServiceImpl(final PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Override
	public Player getByName(final String name) {
		return playerRepository.getByName(name);
	}

	@Override
	public Player save(final String author) {
		Player player = new Player();
		player.setName(author);
		return playerRepository.save(player);
	}
}
