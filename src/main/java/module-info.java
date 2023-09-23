
module fr.telecomnancy.anglais {
    
    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    requires transitive org.controlsfx.controls;
    requires transitive javafx.graphics ;
    requires transitive javafx.web ;
    requires transitive javafx.base ;
    requires transitive javafx.media ;

    requires transitive com.google.gson ;
    requires org.geotools.main ;
    requires org.geotools.shapefile ;

    requires org.jsoup ;
    requires transitive com.jfoenix ;

    opens fr.telecomnancy.anglais to javafx.fxml ;
    opens fr.telecomnancy.anglais.model to com.google.gson ;
    opens fr.telecomnancy.anglais.controllers to javafx.fxml, com.jfoenix ;
    exports fr.telecomnancy.anglais;

}