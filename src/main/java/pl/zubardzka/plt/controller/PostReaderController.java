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
public class PostReaderController {

	private final PostService postService;
	private final ForumService forumService;
	private final PlayerService playerService;
	private final TeamService teamService;
	private final MatchService matchService;

	Logger logger = LoggerFactory.getLogger(PostReaderController.class);

	public PostReaderController(final PostService postService,
								final ForumService forumService,
								final PlayerService playerService,
								final TeamService teamService,
								final MatchService matchService) {
		this.postService = postService;
		this.forumService = forumService;
		this.playerService = playerService;
		this.teamService = teamService;
		this.matchService = matchService;
	}

	private static boolean isProperMatch(final String line) {
		return line.contains(" - ") && line.matches(".*\\d.*\\d");
	}

	@GetMapping("/post/{id}")
	public String getPostDetails(Model model, @PathVariable final Integer id) {

		Forum forum = forumService.getById(id);
		String url = forum.getLink();

		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.getElementsByClass("postBox");
			for (Element element : elements) {
				Post post = new Post();
				post.setForum(forum);
				String author = element.attributes().get("data-author_username");
				Player player = playerService.getByName(author);
				if (player == null) {
					player = playerService.save(author);
				}
				post.setPlayer(player);
				Elements postElement = element.getElementsByClass("postBody");
				if (postElement != null) {
					String body = prepareBody(postElement.get(0).html());
					List<Match> matches = prepareMatches(body, post);
					post.setMatches(matches);
				}
				postService.save(post);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "forum";
	}

	private String prepareBody(final String body) {
		String br = "<br>";
		String hit = "HIT";
		String blank = "";
		String result = body.replace(br, blank);
		result = result.replaceAll("\\(?\\d{2}:\\d{2}\\)", blank);
		result = result.replaceAll("\\d{2}.\\d{2}.\\d{4}", blank);
		result = result.replace("\n\n", "\n");
		result = result.replaceFirst("\\d{1,2}.\\d{2}.\\d{4}", blank);
		result = result.replace(hit, blank);
		return result;
	}

	private List<Match> prepareMatches(final String body, final Post post) {
		String[] lineList = body.split("\n");
		List<String> stringMatches = Arrays.stream(lineList)
										   .map(String::trim)
										   .filter(PostReaderController::isProperMatch)
										   .collect(Collectors.toList());
		List<Match> matches = new ArrayList<>();
		for (String s : stringMatches) {
			int indexOfHyphen = s.indexOf(" ", s.length() - 3);
			if (indexOfHyphen > 0) {
				s = s.substring(0, indexOfHyphen) + ":" + s.substring(indexOfHyphen + 1);
			}
			Team homeTeam = teamService.getByName(s.substring(0, s.indexOf(" - ")));
			Team awayTeam = teamService.getByName(s.substring(s.indexOf(" - ") + 3, s.lastIndexOf(" ")));
			s = s.replaceAll("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ -]* ", "");
			int homeScore = Integer.parseInt(s.substring(0, 1));
			int awayScore = Integer.parseInt(s.substring(2, 3));
			Match m = new Match();
			m.setHomeScore(homeScore);
			m.setAwayScore(awayScore);
			m.setHomeTeam(homeTeam);
			m.setAwayTeam(awayTeam);
			m.setPost(post);
			matchService.save(m);
			matches.add(m);
		}
		return matches;
	}

}
