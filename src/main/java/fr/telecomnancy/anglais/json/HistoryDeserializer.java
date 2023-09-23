
package fr.telecomnancy.anglais.json ;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import fr.telecomnancy.anglais.model.Country;
import fr.telecomnancy.anglais.model.History;
import fr.telecomnancy.anglais.model.HistoryDate;

public class HistoryDeserializer implements JsonDeserializer<History> {

    @Override
    public History deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {

        ArrayList<HistoryDate> dates = new ArrayList<>() ;
        JsonArray jsonArray = json.getAsJsonArray() ; 

        for (JsonElement jsonElement : jsonArray) {

            JsonObject jsonObject = jsonElement.getAsJsonObject() ;
            int date = jsonObject.get("date").getAsInt() ;
            JsonArray countriesArray = jsonObject.get("countries").getAsJsonArray() ;
            ArrayList<Country> countries = new ArrayList<>() ;

            for (JsonElement countryJson : countriesArray) {

                JsonObject countryObject = countryJson.getAsJsonObject() ;
                String name = countryObject.get("name").getAsString() ;
                String description = countryObject.get("description").getAsString() ; 

                countries.add(new Country(name, description)) ;

            }

            dates.add(new HistoryDate(date, countries)) ;

        }

        return new History(dates) ;
        
    }
}