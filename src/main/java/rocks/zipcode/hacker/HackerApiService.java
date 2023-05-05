package rocks.zipcode.hacker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HackerApiService {
    // class to fetch JSON strings about current New stories on HackerNews
    private final String getNewUrl = "https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty";
    private final String getStoryUrlTempl = "https://hacker-news.firebaseio.com/v0/item/%s.json?print=pretty";

    public String getNewTen() {
        String[] tenItems = Arrays.copyOfRange(this.getAllItems(), 0, 10);
        ArrayList<String> result = new ArrayList<>();
        for (String s : tenItems) {
            result.add(this.getItem(s));
        }
        return "[ " + String.join(",\n", result) + " ]\n";
    }

    private String[] getAllItems() {
        String allItemsString = this.getStringFromURL(getNewUrl);
        String[] items = allItemsString.replace('[', ' ')
                .replace(']', ' ')
                .replace(',', ' ')
                .replaceAll(" +", " ")
                .trim()
                .split(" ");
        return items;
    }

    private String getItem(String itemName) {
        if (itemName.length() == 0)
            return "empty";
        String filledUrl = String.format(getStoryUrlTempl, itemName);
        return this.getStringFromURL(filledUrl);
    }

    // takes String as a URL
    // returns a String of the GET'd result
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
            e.printStackTrace();
        }
        return "";
    }

    // just a little test. use the HackerApplication class for running.
    public static void main(String[] args) {
        HackerApiService h = new HackerApiService();

        System.out.println("and now...");
        System.out.println(h.getNewTen());
    }

    // private String convertStringToHex(String str) {
    // StringBuilder stringBuilder = new StringBuilder();

    // char[] charArray = str.toCharArray();

    // for (char c : charArray) {
    // String charToHex = Integer.toHexString(c);
    // stringBuilder.append(charToHex);
    // }

    // return stringBuilder.toString();
    // }
}
