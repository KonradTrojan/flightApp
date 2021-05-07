package com.airport.airportapp.model.flight.reponse;

// class is the basis for the json response
public class AirportDetails {
    private String airportCode;
    private int flightsFrom;
    private int flightsTo;
    private int piecesFrom;
    private int piecesTo;

    public AirportDetails(String airportCode, int flightsFrom, int flightsTo, int piecesFrom, int piecesTo) {
        this.airportCode = airportCode;
        this.flightsFrom = flightsFrom;
        this.flightsTo = flightsTo;
        this.piecesFrom = piecesFrom;
        this.piecesTo = piecesTo;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public int getFlightsFrom() {
        return flightsFrom;
    }

    public void setFlightsFrom(int flightsFrom) {
        this.flightsFrom = flightsFrom;
    }

    public int getFlightsTo() {
        return flightsTo;
    }

    public void setFlightsTo(int flightsTo) {
        this.flightsTo = flightsTo;
    }

    public int getPiecesFrom() {
        return piecesFrom;
    }

    public void setPiecesFrom(int piecesFrom) {
        this.piecesFrom = piecesFrom;
    }

    public int getPiecesTo() {
        return piecesTo;
    }

    public void setPiecesTo(int piecesTo) {
        this.piecesTo = piecesTo;
    }
}
