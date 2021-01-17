package pl.zubardzka.plt.controller;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.zubardzka.plt.domain.Forum;
import pl.zubardzka.plt.service.ForumService;

@Controller
public class ForumReaderController {

	private final ForumService forumService;

	public ForumReaderController(final ForumService forumService) {
		this.forumService = forumService;
	}

	@GetMapping("/posts")
	public String getPosts(Model model) {
		//TODO can add check if is new post use count from repository
		model.addAttribute("elements", getListFromDB());
		return "posts";
	}

	private List<Forum> getListFromDB() {
		return forumService.getAll();
	}
	private List<Forum> getListFromPage() {
		List<Forum> posts = new ArrayList<>();
		String url = "https://joemonster.org/phorum/list.php?f=42";
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.getElementsByClass("phorum-post-title");
			for (Element element : elements) {
				if (element.html().contains("PLT")) {
					Forum forum = new Forum(element.html(), element.attr("abs:href"));
					posts.add(forum);
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		return posts;
	}
}
