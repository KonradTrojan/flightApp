// Simple abstract class with field flightId
// CargoEntity and FlightEntity extend this class


package com.airport.airportapp.model;

public abstract class Entity {
    private int flightId;

    public Entity(int flightId) {this.flightId = flightId;}

    public int getFlightId() {
        return flightId;
    }

}
