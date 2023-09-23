package fr.telecomnancy.anglais.ux;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import fr.telecomnancy.anglais.data.England;
import fr.telecomnancy.anglais.data.France;
import fr.telecomnancy.anglais.data.India;
import fr.telecomnancy.anglais.data.Portugal;
import fr.telecomnancy.anglais.data.Scotland;
import fr.telecomnancy.anglais.data.SouthAfrica;
import fr.telecomnancy.anglais.data.Spain;
import fr.telecomnancy.anglais.data.Australia;
import fr.telecomnancy.anglais.data.UnitedStates;
import fr.telecomnancy.anglais.data.Vietnam;
import fr.telecomnancy.anglais.model.Country;
import fr.telecomnancy.anglais.model.HistoryDate;

public class Data {

    ArrayList<HistoryDate> history = new ArrayList<>();
    ArrayList<Country> countrymap = new ArrayList<Country>();

    public Data() throws UnsupportedEncodingException {


        for (int i = 0; i < 2022; i++) {
            ArrayList<Country> countrymap2 = new ArrayList<Country>();
            France france = new France(i);
            if (!france.getArticle().equals("")) {
                Country fr = new Country(france.getName(), france.getArticle());
                countrymap2.add(fr);
                countrymap.add(fr);
            }

            England england = new England(i);
            if (!england.getArticle().equals("")) {
                Country en = new Country(england.getName(), england.getArticle());
                countrymap2.add(en);
                countrymap.add(en);
            }

            Portugal portugal = new Portugal(i);
            if (!portugal.getArticle().equals("")) {
                Country port = new Country(portugal.getName(), portugal.getArticle());
                countrymap2.add(port);
                countrymap.add(port);
            }

            UnitedStates us = new UnitedStates(i);
            if (!us.getArticle().equals("")) {
                Country usa = new Country(us.getName(), us.getArticle());
                countrymap2.add(usa);
                countrymap.add(usa);
            }

            India india = new India(i);
            if (!india.getArticle().equals("")) {
                Country port = new Country(india.getName(), india.getArticle());
                countrymap2.add(port);
                countrymap.add(port);
            }

            Australia australia = new Australia(i);
            if (!australia.getArticle().equals("")) {
                Country au = new Country(australia.getName(), australia.getArticle());
                countrymap2.add(au);
                countrymap.add(au);
            }

            SouthAfrica southafrica = new SouthAfrica(i);
            if (!southafrica.getArticle().equals("")) {
                Country sa = new Country(southafrica.getName(), southafrica.getArticle());
                countrymap2.add(sa);
                countrymap.add(sa);
            }

            Scotland scotland = new Scotland(i);
            if (!scotland.getArticle().equals("")) {
                Country scot = new Country(scotland.getName(), scotland.getArticle());
                countrymap2.add(scot);
                countrymap.add(scot);
            }

            Spain spain = new Spain(i);
            if (!spain.getArticle().equals("")) {
                Country spa = new Country(spain.getName(), spain.getArticle());
                countrymap2.add(spa);
                countrymap.add(spa);
            }
            Vietnam vietnam= new Vietnam(i);
            if (!vietnam.getArticle().equals("")) {
                Country spa = new Country(vietnam.getName(), vietnam.getArticle());
                countrymap2.add(spa);
                countrymap.add(spa);
            }

            if (!countrymap2.isEmpty())
                history.add(new HistoryDate(i, countrymap2));
            countrymap2 = new ArrayList<Country>();

        }

    }

    public ArrayList<HistoryDate> getHistory() {
        return this.history;
    }
    public ArrayList<Country> getMap() {
        return this.countrymap;
    }

}
