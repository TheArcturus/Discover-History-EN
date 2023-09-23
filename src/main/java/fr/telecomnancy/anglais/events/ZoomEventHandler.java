package fr.telecomnancy.anglais.events;

import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Sphere;

public class ZoomEventHandler implements EventHandler<ScrollEvent> {

    private Sphere earth ;
    private static final double zoomFactor = 1.1 ;

    public ZoomEventHandler(Sphere earth) {
        this.earth = earth ;
    }

    @Override
    public void handle(ScrollEvent event) {

        // Se rapproche
        if (event.getDeltaY() > 0) {
            earth.setScaleX(earth.getScaleX() * zoomFactor) ;
            earth.setScaleY(earth.getScaleY() * zoomFactor) ;
            earth.setScaleZ(earth.getScaleZ() * zoomFactor) ;

        // Recule
        } else {
            earth.setScaleX(earth.getScaleX() / zoomFactor) ;
            earth.setScaleY(earth.getScaleY() / zoomFactor) ;
            earth.setScaleZ(earth.getScaleZ() / zoomFactor) ;
        }

        event.consume() ;

    }
    
}
