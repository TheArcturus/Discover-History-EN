package fr.telecomnancy.anglais.events;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import fr.telecomnancy.anglais.model.Country;
import fr.telecomnancy.anglais.model.History;
import fr.telecomnancy.anglais.json.HistoryJSON;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ButtonTravelEventHandler implements EventHandler<ActionEvent> {

    private TextField tf_date ;
    private ChoiceBox<String> country_choice ;
    private Button button_go ;

    public static final String ANSI_RED = "\u001B[31m" ; 
    public static final String ANSI_RESET = "\u001B[0m" ;

    public ButtonTravelEventHandler(TextField date, ChoiceBox<String> country_choice, Button button_go) {
        this.tf_date = date ;
        this.country_choice = country_choice ;
        this.button_go = button_go ;
    }

    @Override
    public void handle(ActionEvent event) {

        // Clear de la choicebox
        country_choice.getItems().clear() ;

        // Récupère le texte + Gestion erreur
        
        String text_date = tf_date.getText() ;
        int date = 0 ;

        try {
            date = Integer.parseInt(text_date) ;
        } catch (Exception e) {
            // DO NOTHING

            ZonedDateTime now = ZonedDateTime.now() ;
            System.out.println(ANSI_RED + "[LOG " + now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss z")) 
                + "] Error while parsing the value in the textfield. A date should be an integer !" + ANSI_RESET) ;

            return ;

        }

        // Récupèration des infos sur les pays par rapport à la date  

        HistoryJSON json = new HistoryJSON() ;
        History history = json.getHistory() ;

        ArrayList<Country> countries = history.getEventByDate(date) ;

        if (countries == null) {
            ZonedDateTime now = ZonedDateTime.now() ;
            System.out.println(ANSI_RED + "[LOG " + now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss z")) 
                + "] Error while reading the date, this date is not handled yet" + ANSI_RESET) ;
        }
        
        // Affichage reste composants + Affectations aux choix

        for (Country country : countries) {
            country_choice.getItems().add(country.getName()) ;
        }
        
        country_choice.setVisible(true) ;
        country_choice.setManaged(true) ;
        country_choice.setValue(country_choice.getItems().get(0)) ;
        button_go.setVisible(true) ;
        button_go.setManaged(true) ;
        
    }

}
