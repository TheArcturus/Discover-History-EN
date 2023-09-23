package fr.telecomnancy.anglais.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.ResourceBundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import fr.telecomnancy.anglais.Main;
import fr.telecomnancy.anglais.model.Country;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class CountryInfoController implements Initializable {

    public static Stage stage;
    public static Country country;
    public static ChoiceBox<String> country_choice;
    public static Button button_go;
    public static TextField tf_date;
    public static Button button_date;
    public static Button button_discover ;

    @FXML
    private WebView webview;

    public CountryInfoController() {
    }

    public Country getCountry() {
        return CountryInfoController.country;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Récupère les infos à propos du pays

        String article = "";
        try {
            article = URLDecoder.decode(country.getDescription(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Transforme l'article en HTML & Affiche sur la WebView
        System.out.println(article + "\n");
        Document doc = null;
        if (!article.equals("L'article n'a pas encore été écrit")) {
            File input = new File(article);
            try {
                doc = Jsoup.parse(input, "ISO-8859-9", "");

                // Chargement des images

                Elements images = doc.select("img");
                for (Element img : images) {
                    String src = img.attr("src");
                    String alt = img.attr("alt");
                    String title = img.attr("title");
                    InputStream in = getClass()
                            .getResourceAsStream("/fr/telecomnancy/anglais/data/" + tf_date.getText() + "/" + src);
                    byte[] bytes = new byte[in.available()];
                    in.read(bytes);
                    in.close();
                    img.attr("src", "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes));
                    img.attr("alt", alt);
                    img.attr("title", title);
                }

                // Application du style

                Element link = doc.head().appendElement("link");
                link.attr("rel", "stylesheet");
                URL stylefile = getClass().getResource("/fr/telecomnancy/anglais/css/style_html.css");
                link.attr("href", stylefile.toExternalForm());

                doc.select("body").addClass("body");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            doc = Jsoup.parse(article);
        }

        WebEngine engine = webview.getEngine();
        engine.loadContent(doc.html());

    }

    @FXML
    private void onButtonGoBack() {

        DefaultUIController ui = new DefaultUIController(button_discover,button_date, tf_date, country_choice, button_go);
        ui.setStage(stage);

        stage.setScene(Main.DEFAULT_SCENE);

    }

}
