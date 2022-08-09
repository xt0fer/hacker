package rocks.zipcode.hacker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.xml.transform.Result;

public class HackerApi {
    private final String getNewUrl = "https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty";
    private final String getStoryUrlTempl = "https://hacker-news.firebaseio.com/v0/item/%s.json?print=pretty";

    public String getNewTen() {
        StringBuilder result = new StringBuilder();
        result.append(this.getNewTenItems());
        return result.toString();
    }

    private String[] getNewTenItems() {
        int limit = 10;
        String[] allItems = this.getAllItems();

        String[] tenItems = new String[limit];
        for (int i = 0; i < limit; i++) {
            tenItems[i] = this.getItem(allItems[i]);
        }
        return tenItems;
    }
    private String[] getAllItems() {
        String allItemsString = this.getStringFromURL(getNewUrl);
        String[] items = allItemsString.replace('[',' ')
        .replace(']',' ')
        .replace(',',' ')
        .trim()
                .split(" ");
        return items;
    }

    private String getItem(String itemName) {

        return "";
    }

    private String getStringFromURL(String urlString) {
        URL url;
        try {
            url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            String text = new BufferedReader(
            new InputStreamReader(is, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
            return text;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
}
