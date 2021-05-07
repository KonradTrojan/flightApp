package com.airport.airportapp.model.cargo;

import com.airport.airportapp.model.Entity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.util.ArrayList;

// class describes CargoEntity and gives possibility of parsing JSON to objects
public class CargoEntity extends Entity {

    private ArrayList<Baggage> baggage;
    private ArrayList<Baggage> cargo;

    public CargoEntity(int flightId, ArrayList<Baggage> baggage, ArrayList<Baggage> cargo) {
        super(flightId);
        this.baggage = baggage;
        this.cargo = cargo;
    }

    private double getSumWeight(ArrayList<Baggage> baggage){
        double sum = 0;
        double kgToLbRatio = 0.454;
        for (Baggage bag: baggage){
            if (bag != null){
                if (bag.getWeightUnit().equals("kg"))
                    sum += bag.getWeight();
                else if (bag.getWeightUnit().equals("lb"))
                    sum += kgToLbRatio * bag.getWeight();
            }
        }
        return sum;
    }

    private int getPieces(ArrayList<Baggage> baggage){
        int pieces = 0;
        for (Baggage bag: baggage){
            if (bag != null){
                if (bag.getWeightUnit().equals("kg"))
                    pieces += bag.getPieces();
            }
        }
        return pieces;
    }

    public static CargoEntity parseJsonObject(JSONObject object) throws JSONException, ParseException {

        int flightId = (int) object.get("flightId");

        var baggage = new ArrayList<Baggage>();

        JSONArray array  = (JSONArray) object.get("baggage");
        for (int i=0; i<array.length(); i++){
            baggage.add(Baggage.parseJsonObject(array.getJSONObject(i)));
        }

        var cargo = new ArrayList<Baggage>();

        array  = (JSONArray) object.get("cargo");
        for (int i=0; i<array.length(); i++){
            cargo.add(Baggage.parseJsonObject(array.getJSONObject(i)));
        }

        return new CargoEntity(flightId, baggage, cargo);
    }

    public int getAllPieces(){
        return getPieces(this.baggage) + getPieces(this.cargo);
    }

    public double getBaggageWeight(){
        return getSumWeight(this.baggage);
    }

    public double getCargoWeight(){
        return getSumWeight(this.cargo);
    }

}