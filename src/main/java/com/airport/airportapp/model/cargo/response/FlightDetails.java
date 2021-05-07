package com.airport.airportapp.model.cargo.response;

// class is the basis for the json response
public class FlightDetails {
    private int flightId;
    private double cargoWeight;
    private double baggageWeight;
    private double totalWeight;
    private String weightUnit;

    {
        this.weightUnit = "kg";
    }

    public FlightDetails(int flightId, double cargoWeight, double baggageWeight, double totalWeight) {
        this.flightId = flightId;
        this.cargoWeight = cargoWeight;
        this.baggageWeight = baggageWeight;
        this.totalWeight = totalWeight;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public double getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public double getBaggageWeight() {
        return baggageWeight;
    }

    public void setBaggageWeight(double baggageWeight) {
        this.baggageWeight = baggageWeight;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }
}