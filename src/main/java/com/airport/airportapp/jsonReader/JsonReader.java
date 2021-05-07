package com.airport.airportapp.jsonReader;

import com.airport.airportapp.model.cargo.CargoEntity;
import com.airport.airportapp.model.flight.FlightEntity;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.ArrayList;

// class has two static methods, which return ArrayList of CargoEntity or FlightEntity objects
public class JsonReader {

    public static ArrayList<FlightEntity> flightEntitiesFromJson(String url) throws JSONException, IOException {

        String response = responseContent(url);

        JSONArray array = new JSONArray(response);

        var list = new ArrayList<FlightEntity>();

        for (int i=0; i<array.length(); i++){
            list.add(FlightEntity.parseJsonObject(array.getJSONObject(i)));
        }

        return list;

    }

    public static ArrayList<CargoEntity> cargoEntitiesFromJson(String url) throws JSONException, ParseException, IOException {

        String response = responseContent(url);

        JSONArray array = new JSONArray(response);

        var list = new ArrayList<CargoEntity>();

        for (int i=0; i<array.length(); i++){
            list.add(CargoEntity.parseJsonObject(array.getJSONObject(i)));
        }

        return list;

    }


    private static String responseContent(String url) throws IOException {

        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }
}