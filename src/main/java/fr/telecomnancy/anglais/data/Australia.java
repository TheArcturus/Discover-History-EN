package fr.telecomnancy.anglais.data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Australia {

    private final String name = "Australia";
    private String article = "";
    private ArrayList<Integer> dates = new ArrayList<Integer>();

    public Australia(int date) throws UnsupportedEncodingException {

        List<Integer> intsToAdd = Arrays.asList(1788);
        dates.addAll(intsToAdd);

        if (this.hasDate(date)) {

            String article = "L'article n'a pas encore été écrit";

            try {
                article = getClass()
                        .getResource("/fr/telecomnancy/anglais/data/" + Integer.toString(date) + "/australia.html")
                        .getPath();
            } catch (Exception e) {
            }

            this.article = URLEncoder.encode(article, "UTF-8");

        }

    }

    public String getArticle() {
        return this.article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasDate(int date) {
        return dates.contains(date);
    }

}
