package fr.telecomnancy.anglais.model;

import java.util.ArrayList;

public class History {

    public ArrayList<HistoryDate> dates ;

    public History(ArrayList<HistoryDate> dates) {
        this.dates = dates ;
    }

    public ArrayList<HistoryDate> getDates() {
        return this.dates ;
    }

    public void setDates(ArrayList<HistoryDate> dates) {
        this.dates = dates ;
    }

    public ArrayList<Country> getEventByDate(int date) {

        for (HistoryDate hd : dates) {

            if (hd.getDate() == date) {

                return hd.getCountries() ;

            }

        }

        return null ;

    }
    
}
