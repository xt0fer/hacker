package rocks.zipcode.hacker;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

	private static final String template = "%s";
	private static final String htmltemplate = "<html><body><pre>%s</pre></body></html>";
	private final AtomicLong counter = new AtomicLong();
    HackerApiService news = new HackerApiService();

	@GetMapping("/news")
	public NewsPOJO news(@RequestParam(value = "kind", defaultValue = "New Top 10") String name) {
		return new NewsPOJO(counter.incrementAndGet(), String.format(template, news.getNewTen()));
	}

    @GetMapping("/pretty")
    @ResponseBody
	public String getjson(@RequestParam(value = "kind", defaultValue = "New Top 10") String name) {
		return String.format(htmltemplate, news.getNewTen());
	}



}
