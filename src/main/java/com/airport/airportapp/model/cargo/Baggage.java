package com.airport.airportapp.model.cargo;

import org.json.JSONException;
import org.json.JSONObject;

// class describes JsonObject, part of CargoEntity
// two Arraylist of objects of this class are fields in CargoEntity class
public class Baggage {

    private int id;
    private double weight;
    private String weightUnit;
    int pieces;

    public Baggage(int id, double weight, String weightUnit, int pieces) {
        this.id = id;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.pieces = pieces;
    }

    public static Baggage parseJsonObject(JSONObject object) throws JSONException {

        int id = (int) object.get("id");
        int weight = (int) object.get("weight");
        String weightUnit = (String) object.get("weightUnit");
        int pieces = (int) object.get("pieces");

        return new Baggage(id, weight, weightUnit, pieces);
    }

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public int getPieces() {
        return pieces;
    }

}
