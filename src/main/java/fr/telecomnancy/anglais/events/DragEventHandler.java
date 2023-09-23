package fr.telecomnancy.anglais.events;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class DragEventHandler implements EventHandler<MouseEvent> {

    private Sphere earth ;
    private double mouseX, mouseY ;
    private AnimationTimer timer ;

    public DragEventHandler(Sphere earth, AnimationTimer timer) {
        this.earth = earth ;
        this.timer = timer ;
    }

    @Override
    public void handle(MouseEvent event) {

        timer.stop() ;
        
        double deltaX = event.getSceneX() - mouseX ;
        double deltaY = event.getSceneY() - mouseY ;

        earth.setRotationAxis(Rotate.X_AXIS) ;
        earth.setRotate(earth.getRotate() - deltaY * 0.1) ; 

        earth.setRotationAxis(Rotate.Y_AXIS);
        earth.setRotate(earth.getRotate() - deltaX * 0.1) ;

        mouseX = event.getSceneX() ;
        mouseY = event.getSceneY() ;

        event.consume() ;

    }
    
}
