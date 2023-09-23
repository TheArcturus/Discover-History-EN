package fr.telecomnancy.anglais.model;

public class Country {

    public String name ;
    public String description ;

    public Country(String name, String description) {
        this.name = name ;
        this.description = description ;
    }

    public String getName() {
        return this.name ;
    }

    public void setName(String name) {
        this.name = name ;
    }

    public String getDescription() {
        return this.description ;
    }

    public void setDescription(String description) {
        this.description = description ;
    }

    
}
