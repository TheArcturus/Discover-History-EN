package fr.telecomnancy.anglais.json;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gson.Gson;

import fr.telecomnancy.anglais.model.HistoryDate;
import fr.telecomnancy.anglais.ux.Data;

public class CreatorJSON {

    public CreatorJSON() throws UnsupportedEncodingException {

        Data data = new Data() ;
        ArrayList<HistoryDate> history = data.getHistory() ;

        Gson gson = new Gson() ;
        try {
            FileWriter writer = new FileWriter("data.json") ;
            gson.toJson(history, writer) ;
            writer.close() ;
        } catch (IOException e) {
            e.printStackTrace() ;
        }

    }
    
}
