package rocks.zipcode.hacker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class HackerApi {
    private final String getNewUrl = "https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty";
    private final String getStoryUrlTempl = "https://hacker-news.firebaseio.com/v0/item/%s.json?print=pretty";

    public String getNewTen() {
        StringBuilder result = new StringBuilder();
        result.append(this.getNewTenItems());
        return result.toString();
    }

    private String getNewTenItems() {
        String[] allItems = this.getAllItems();

        StringBuilder tenItems = new StringBuilder();
        int j = 0;
        for (String s : allItems) {
            if (s.length() == 0) continue;
            String t = getItem(s);
            // System.err.printf("%d: [%s] [%s] \n", j, s, convertStringToHex(t));
            tenItems.append(t);
            j++;
            
            if (j == 10) break;
        }
        return "[ " + tenItems.toString() + " ]\n";
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
        if (itemName.length() == 0) return "empty";
        String filledUrl = String.format(getStoryUrlTempl, itemName);
        return this.getStringFromURL(filledUrl);
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

// just a little test.
    public static void main(String[] args) {
        HackerApi h = new HackerApi();

        System.out.println("and now...");
        System.out.println(h.getNewTen());
    }

    // private String convertStringToHex(String str) {
    //     StringBuilder stringBuilder = new StringBuilder();

    //     char[] charArray = str.toCharArray();

    //     for (char c : charArray) {
    //         String charToHex = Integer.toHexString(c);
    //         stringBuilder.append(charToHex);
    //     }

    //     return stringBuilder.toString();
    // }
}
