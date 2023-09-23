package fr.telecomnancy.anglais.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.controlsfx.control.textfield.TextFields;

import fr.telecomnancy.anglais.events.ButtonTravelEventHandler;
import fr.telecomnancy.anglais.json.HistoryJSON;
import fr.telecomnancy.anglais.model.Country;
import fr.telecomnancy.anglais.model.History;
import fr.telecomnancy.anglais.ux.Data;
import fr.telecomnancy.anglais.ux.Playlist;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DefaultUIController {

    private Stage stage = new Stage() ;

    private Button button_date ;
    private TextField tf_date ;
    private ChoiceBox<String> country_choice ;
    private Button button_go ;
    private Button button_discover ;

    private static final List<String> set_dates = Arrays.asList("1066", "1492", "1994", "1632", "1788","1955","1861","1789","1632","1320","722") ;
    private static final Playlist playlist = new Playlist(Arrays.asList("Interstellar.mp3", "Nuvole.mp3")) ;
    public DefaultUIController(Button button_discover,Button button_date, TextField date, ChoiceBox<String> country_choice, Button button_go) {

        // Affecte les composants

        this.button_date = button_date ;
        this.tf_date = date ;
        this.country_choice = country_choice ;
        this.button_go = button_go ;
        this.button_discover=button_discover;

        // Spécialise les composants
        actualiser() ; 

    }

    public void setStage(Stage stage) {
        this.stage = stage ;
    }
    public Stage getStage() {
        return this.stage ;
    }

    private void actualiser() {

        // Choix du pays invisible au début par défaut

        country_choice.setVisible(false) ;
        button_go.setVisible(false) ;
        country_choice.setManaged(false) ;
        button_go.setManaged(false);

        CountryInfoController.country_choice = country_choice ;
        CountryInfoController.button_go = button_go ;
        CountryInfoController.tf_date = tf_date ;
        CountryInfoController.button_date = button_date ;
        CountryInfoController.button_discover=button_discover;

        // Gestion musique

        playlist.play() ;

        // Style

        button_date.getStyleClass().add("button_date") ;
        tf_date.getStyleClass().add("textfield_date") ;
        button_go.getStyleClass().add("button_date") ;
        country_choice.getStyleClass().add("choicebox_country") ;
        button_discover.getStyleClass().add("button_discover");

        // Dates binding auto-completion

        TextFields.bindAutoCompletion(tf_date, set_dates) ;

        // Actions

        button_date.setOnAction(new ButtonTravelEventHandler(tf_date, country_choice, button_go)) ;
        button_go.setOnAction(event -> { goToCountryInfo() ; }) ;
        button_discover.setOnAction(event->{ try {
            DiscoverCountryInfo();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } });

    }

    public void goToCountryInfo()  {
        // Remplacer par fonction de sélection

        String country_name = country_choice.getSelectionModel().getSelectedItem() ;
        int date = Integer.parseInt(tf_date.getText()) ;

        // Lecture du fichier & Récupération infos pays

        HistoryJSON histjson = new HistoryJSON() ;
        History history = histjson.getHistory() ;

        ArrayList<Country> countries = history.getEventByDate(date) ;

        Country current = null ;

        for (Country country : countries) {
            if (country_name.equals(country.getName())) {
                current = country ;
            }
        }

        CountryInfoController.country = current ;

        // Affichage de la nouvelle page

        FXMLLoader loader = new FXMLLoader() ;
        URL url = getClass().getResource("/fr/telecomnancy/anglais/fxml/page_article.fxml") ;

        Parent root ;
        try {
            loader.setControllerFactory(ic -> { return new CountryInfoController() ; }) ;
            root = FXMLLoader.load(url) ;
            stage.setScene(new Scene(root)) ;
            CountryInfoController.stage = stage ;

        } catch (IOException e) {
            e.printStackTrace() ;
        }

        playlist.stop() ;


    }




    public void DiscoverCountryInfo() throws UnsupportedEncodingException
    {
         Data data=new Data();
        ArrayList<Country> allcountries=data.getMap();

        int n =allcountries.size();
        int choice=(int) (Math.random()*(n));
        Country current = allcountries.get(choice);
        CountryInfoController.country = current ;

        FXMLLoader loader = new FXMLLoader() ;
        URL url = getClass().getResource("/fr/telecomnancy/anglais/fxml/page_article.fxml") ;

        Parent root ;
        try {
        
            loader.setControllerFactory(ic -> { return new CountryInfoController() ; }) ;
            root = FXMLLoader.load(url) ;
            stage.setScene(new Scene(root)) ;
            CountryInfoController.stage = stage ;


        } catch (IOException e) {
            e.printStackTrace() ;
        }

        playlist.stop() ;
        
    }
}
