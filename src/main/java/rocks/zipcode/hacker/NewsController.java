package rocks.zipcode.hacker;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

	private static final String template = "%s";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/news")
	public NewsPOJO news(@RequestParam(value = "kind", defaultValue = "New Top 10") String name) {
        HackerApi news = new HackerApi();
		return new NewsPOJO(counter.incrementAndGet(), String.format(template, news.getNewTen()));
	}
}
