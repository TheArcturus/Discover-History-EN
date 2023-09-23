package fr.telecomnancy.anglais.ux;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Playlist {

    private MediaPlayer mediaPlayer ;

    private int currentSongIndex ;
    private ArrayList<String> songs = new ArrayList<>() ;

    public Playlist(List<String> list) {

        this.songs.addAll(list) ;

    }

    public void play() {

        this.currentSongIndex = new Random().nextInt(songs.size()) ;
        playSong() ;

    }

    public void stop() {

        // Ne pas arrêter d'un coup le son

        // A REGLER

        FadeTransition ft = new FadeTransition() ;
        ft.setDuration(Duration.millis(5000)) ;
        ft.setFromValue(1) ;
        ft.setToValue(0) ;
        ft.setOnFinished(e -> mediaPlayer.stop()) ;
        ft.play() ;

    }

    private void playSong() {

        URL url = getClass().getResource("/fr/telecomnancy/anglais/sounds/" + songs.get(currentSongIndex)) ;

        Media song = null ;
        song = new Media(url.toExternalForm()) ;

        mediaPlayer = new MediaPlayer(song) ; 
        mediaPlayer.setOnEndOfMedia(this::playNextSong) ;
        mediaPlayer.play() ;

    }

    private void playNextSong() {

        currentSongIndex = (currentSongIndex + 1) % songs.size() ;
        playSong() ;
        
    }
    
}
