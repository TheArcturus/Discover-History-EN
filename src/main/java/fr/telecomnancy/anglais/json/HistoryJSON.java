package fr.telecomnancy.anglais.json;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.telecomnancy.anglais.model.History;

public class HistoryJSON {

    public HistoryJSON() {
    }

    public History getHistory() {

        GsonBuilder gsonBuilder = new GsonBuilder() ;
        gsonBuilder.registerTypeAdapter(History.class, new HistoryDeserializer()) ;
        Gson gson = gsonBuilder.create() ;
        History history = null ;
        
        try {

            FileReader reader = new FileReader("data.json") ;
            history = gson.fromJson(reader, History.class) ;
            reader.close() ;

        } catch (IOException e) {
            e.printStackTrace() ;
        }

        return history ;

    }
    
}
