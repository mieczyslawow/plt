package pl.zubardzka.plt.controller;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.zubardzka.plt.domain.*;
import pl.zubardzka.plt.service.*;

@Controller
public class MatchesReaderController {

	private final ForumService forumService;
	private final TeamService teamService;
	private final MatchService matchService;
	private final PlayerService playerService;

	Logger logger = LoggerFactory.getLogger(MatchesReaderController.class);

	public MatchesReaderController(final ForumService forumService,
								   final TeamService teamService,
								   final MatchService matchService,
								   final PlayerService playerService) {
		this.forumService = forumService;
		this.teamService = teamService;
		this.matchService = matchService;
		this.playerService = playerService;
	}

	private static boolean isProperMatch(final String line) {
		return line.contains(" - ") && line.matches(".*\\d.*\\d");
	}

	@GetMapping("/forum/{id}")
	public String getPostDetails(Model model, @PathVariable final Integer id) {
		Forum forum = forumService.getById(id);
		List<Match> matches = matchService.getAll(forum);
		Map<Player, List<Match>> map = matches.stream().collect(Collectors.groupingBy(Match::getPlayer));
		model.addAttribute("map", map);
		String url = forum.getLink();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.getElementsByClass("postBox");
			for (Element element : elements) {
				String author = element.attributes().get("data-author_username");
				Player player = playerService.getByName(author);
				if (player == null) {
					player = playerService.save(author);
				}
				Elements postElement = element.getElementsByClass("postBody");
				if (postElement != null) {
					String body = prepareBody(postElement.get(0).html());
					if (isExtraBet(body)) {
						logger.info(player.getName() + " " + extraBetResult(body));
					}
					prepareMatches(body, forum, player);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "post";
	}

	@GetMapping("/forum/{id}/calculate")
	public String calculate(Model model, @PathVariable final Integer id) {
		Forum forum = forumService.getById(id);
		List<Match> matches = matchService.getAll(forum);
		for (Match match : matches) {
			if (match.getHomeScore() == match.getMatchResult().getHomeScore()
				&& match.getAwayScore() == match.getMatchResult().getAwayScore()) {
				match.setScore(2);
				setHitScore(match, 2);
			} else if (match.getHomeScore() == match.getAwayScore()
					   && match.getMatchResult().getHomeScore() == match.getMatchResult().getAwayScore() || (
						   match.getHomeScore() > match.getAwayScore()
						   && match.getMatchResult().getHomeScore() > match.getMatchResult().getAwayScore()) || (
						   match.getHomeScore() < match.getAwayScore()
						   && match.getMatchResult().getHomeScore() < match.getMatchResult().getAwayScore())) {
				match.setScore(1);
				setHitScore(match, 1);
			} else {
				match.setScore(0);
			}
			if (matchService.getById(match.getId()).getScore() == null) {
				matchService.save(match);
			}
		}
		Map<Player, List<Match>> map = matches.stream().collect(Collectors.groupingBy(Match::getPlayer));
		model.addAttribute("map", map);
		Map<Player, Integer> score = matches.stream()
											.collect(Collectors.toMap(Match::getPlayer, Match::getScore, (a, b) -> a + b));
		model.addAttribute("score", score);
		return "post";
	}

	private void setHitScore(final Match match, final int point) {
		if (match.getMatchResult().isHit()) {
			match.setScore(match.getScore() + point);
		}
	}

	private boolean isExtraBet(final String body) {
		return body.contains("ZD:") || body.contains("Zd:") || body.contains("Z.D.") || body.contains("zd");
	}

	private String extraBetResult(final String body) {
		int length = 0;
		if (body.contains("ZD:")) {
			length = body.indexOf("ZD:");
		} else if (body.contains("Zd:")) {
			length = body.indexOf("Zd:");
		} else if (body.contains("Z.D.")) {
			length = body.indexOf("Z.D.");
		} else if (body.contains("zd")) {
			length = body.indexOf("zd");
		}
		return body.substring(length);
	}

	private String prepareBody(final String body) {
		String br = "<br>";
		String hit = " HIT";
		String blank = "";
		String result = body.replace(br, blank);
		result = result.replaceAll("\\(?\\d{2}:\\d{2}\\)", blank);
		result = result.replaceAll("\\d{2}.\\d{2}.\\d{4}", blank);
		result = result.replace("\n\n", "\n");
		result = result.replaceFirst("\\d{1,2}.\\d{2}.\\d{4}", blank);
		result = result.replace(hit, blank);
		return result;
	}

	private List<Match> prepareMatches(final String body, final Forum forum, final Player player) {
		String[] lineList = body.split("\n");
		List<String> stringMatches = Arrays.stream(lineList)
										   .map(String::trim)
										   .filter(MatchesReaderController::isProperMatch)
										   .collect(Collectors.toList());
		List<Match> matches = new ArrayList<>();
		for (String s : stringMatches) {
			int indexOfHyphen = s.indexOf(" ", s.length() - 3);
			if (indexOfHyphen > 0) {
				s = s.substring(0, indexOfHyphen) + ":" + s.substring(indexOfHyphen + 1);
			}
			String homeTeamName = s.substring(0, s.indexOf(" - "));
			String awayTeamName = s.substring(s.indexOf(" - ") + 3, s.lastIndexOf(" "));
			Team homeTeam = teamService.getByName(homeTeamName);
			Team awayTeam = teamService.getByName(awayTeamName);
			if (homeTeam == null) {
				homeTeam = teamService.getAll()
									  .stream()
									  .filter(team -> team.getName().startsWith(homeTeamName))
									  .findFirst()
									  .orElse(new Team());
				logger.error("Błędna nazwa drużyny " + homeTeamName + " podstawiam " + homeTeam.getName());
			}
			if (awayTeam == null) {
				awayTeam = teamService.getAll()
									  .stream()
									  .filter(team -> team.getName().startsWith(awayTeamName))
									  .findFirst()
									  .orElse(new Team());
				logger.error("Błędna nazwa drużyny " + awayTeamName + " podstawiam " + awayTeam.getName());
			}
			s = s.replaceAll("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ -]* ", "");
			if (s.length() > 3) {
				s = s.replace(":", "");
			}
			int homeScore = Integer.parseInt(s.substring(0, 1));
			int awayScore = Integer.parseInt(s.substring(2, 3));
			Match m = new Match();
			m.setHomeScore(homeScore);
			m.setAwayScore(awayScore);
			m.setHomeTeam(homeTeam);
			m.setAwayTeam(awayTeam);
			m.setForum(forum);
			m.setPlayer(player);
			matchService.save(m);
			matches.add(m);
		}
		return matches;
	}

}
