package rocks.zipcode.hacker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

	private static final String plaintemplate = "%s";
	private static final String prettytemplate = "<html><body><pre>%s</pre></body></html>";
    HackerApiService news = new HackerApiService();

	@GetMapping("/sample")
	public NewsPOJO news(@RequestParam(value = "kind", defaultValue = "New Top 10") String name) {
		return new NewsPOJO(String.format(plaintemplate, news.getNewTen()));
	}

	@GetMapping("/news")
    @ResponseBody
	public String getjson(@RequestParam(value = "kind", defaultValue = "New Top 10") String name) {
		return String.format(plaintemplate, news.getNewTen());
	}

    @GetMapping("/pretty")
    @ResponseBody
	public String getPretty() {
		return String.format(prettytemplate, news.getNewTen());
	}

// put index2.html in resources/static for spring to find and resolve the news.html HTML file.

}
