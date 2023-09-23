package fr.telecomnancy.anglais.ux ;

import java.io.InputStream ;

import fr.telecomnancy.anglais.Main;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class EarthUX {
    
    private final InputStream EARTH_TEXTURE = Main.class.getResourceAsStream("images/earth_texture.jpg") ;

    private Sphere earth = new Sphere() ;
    private AnimationTimer timer ;
    private static final int rotation_speed = 50_000_000 ; // En ms

    public EarthUX() {

        // Récupèration de l'image & Création texture

        Image earthTexture = new Image(EARTH_TEXTURE) ;
        PhongMaterial earthMaterial = new PhongMaterial() ;
        earthMaterial.setDiffuseMap(earthTexture) ;

        // Calcul le radius par rapport à la diffuse map

        double width = earthMaterial.getDiffuseMap().getWidth() ;
        double height = earthMaterial.getDiffuseMap().getHeight() ;
        double radius = Math.max(width, height) / 30.0 ; // Ratio modifiable

        earth.setRadius(radius) ; // Application du radius
        earth.setMaterial(earthMaterial) ; // Application de la texture à la Terre

        // Rotation
        
        Rotate rotate = new Rotate(0, Rotate.Y_AXIS) ;
        earth.getTransforms().add(rotate) ;

        // Animation de rotation de la Terre
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate ;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= rotation_speed) {
                    rotate.setAngle(rotate.getAngle() + 1) ;
                    lastUpdate = now ;
                }
            }
        } ;
        timer.start() ;

        this.timer = timer ;

    }

    public Sphere getEarth() {
        return this.earth  ;
    }

    public AnimationTimer getTimer() {
        return this.timer ;
    }
    
}
