package fr.telecomnancy.anglais.model;

import java.util.ArrayList;

public class HistoryDate {

    public int date ;
    public ArrayList<Country> countries ;

    public HistoryDate(int date, ArrayList<Country> countries) {
        this.date = date ; 
        this.countries = countries ;
    }

    public int getDate() {
        return this.date ;
    }

    public void setDate(int date) {
        this.date = date ;
    }

    public ArrayList<Country> getCountries() {
        return this.countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

}
