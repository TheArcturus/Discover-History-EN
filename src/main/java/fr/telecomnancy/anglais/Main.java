
package fr.telecomnancy.anglais ;

import java.io.UnsupportedEncodingException;

import fr.telecomnancy.anglais.controllers.DefaultUIController;
import fr.telecomnancy.anglais.events.DragEventHandler;
import fr.telecomnancy.anglais.events.ZoomEventHandler;
import fr.telecomnancy.anglais.json.CreatorJSON;
import fr.telecomnancy.anglais.ux.EarthUX;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class Main extends Application {

    private final String SPACE_BACKGROUND = getClass().getResource("images/space_background.jpg").toExternalForm() ;
    public static Scene DEFAULT_SCENE ;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Création du borderpane parent

        BorderPane root = new BorderPane() ;
        root.setBackground(new Background(new BackgroundImage(new Image(SPACE_BACKGROUND),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT))) ; // Mise en place de l'image de fond
        root.setPadding(new Insets(10)) ;

        // Ajout des champs gérant la timeline

        HBox hbox = new HBox() ;
        Button button_discover = new Button("Discover") ;
        Button button_date = new Button("Travel To") ;
        TextField date = new TextField("Enter a date") ;
        
        ChoiceBox<String> country_choice = new ChoiceBox<String>() ;
        Button button_go = new Button("Go !") ;

        DefaultUIController ui = new DefaultUIController(button_discover,button_date, date, country_choice, button_go) ;
        ui.setStage(primaryStage) ;

        hbox.getChildren().addAll(button_discover,button_date, date, country_choice, button_go) ;
        hbox.setAlignment(Pos.TOP_RIGHT) ;

        // Ajout du globe dans le borderpane

        StackPane globeContainer = new StackPane();
        root.setCenter(globeContainer) ;
        root.setTop(hbox) ;
        globeContainer.setPickOnBounds(false) ;

        // Création de la scène & Paramètrage du stage, pour la Terre
        
        Scene scene = new Scene(root, 800, 600, false, SceneAntialiasing.BALANCED) ;
        scene.setFill(Color.BLACK) ;
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm()) ;

        primaryStage.setScene(scene) ;
        primaryStage.setTitle("A TN history of the world") ;
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/icon.png"))) ;
        
        primaryStage.show() ;

        // Ajouts à la scène principale

        EarthUX earthux = new EarthUX() ;
        Sphere earth = earthux.getEarth() ;
        AnimationTimer timer = earthux.getTimer() ;
        globeContainer.getChildren().add(earth) ;
        
        // Zoom
        scene.setOnScroll(new ZoomEventHandler(earth)) ;
        // Drag pour tourner la Terre
        scene.setOnMouseDragged(new DragEventHandler(earth, timer)) ;
        scene.setOnMouseReleased(event -> { timer.start() ; }) ;

        // Ajout de données de test dans le JSON pour voir la structure

        try {
            new CreatorJSON() ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace() ;
        }

        // Sauvegarde de la scène de l'écran d'accueil

        DEFAULT_SCENE = scene ;

    }

}