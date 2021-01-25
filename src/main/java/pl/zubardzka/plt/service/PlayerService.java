package pl.zubardzka.plt.service;

import pl.zubardzka.plt.domain.Player;

public interface PlayerService {

	Player getByName(String name);

	Player save(String author);
}
