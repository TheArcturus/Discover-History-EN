package fr.telecomnancy.anglais.data;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Canada {


    private final String name = "Canada" ;
    private String article = "" ;
    private ArrayList<Integer> dates = new ArrayList<Integer>() ;

    public Canada(int date) throws UnsupportedEncodingException {

        // Dates ou le Portugal a joué un  rôle
        List<Integer> intsToAdd = Arrays.asList(1492) ;
        dates.addAll(intsToAdd) ;

        if (this.hasDate(date)) {

            if (date == 1492) {

                this.article = URLEncoder.encode("Article Canada UTF-8 @çéàè", "UTF-8") ;

            }
            
        }

    }


    public String getArticle() {
        return this.article ;
    }

    public void setArticle(String article) {
        this.article = article ; 
    }

    public String getName() {
        return this.name ;
    }

    public boolean hasDate(int date) {
        return dates.contains(date) ;
    }
    
}

    
