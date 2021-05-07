package com.airport.airportapp.model.flight;

import com.airport.airportapp.model.Entity;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.ZonedDateTime;

// class describes FlightEntity and gives possibility of parsing JSON to objects
public class FlightEntity extends Entity {

    private int flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    private ZonedDateTime departureDate;


    public FlightEntity(int flightId, int flightNumber, String departureAirportIATACode, String arrivalAirportIATACode, ZonedDateTime departureDate) {
        super(flightId);
        this.flightNumber = flightNumber;
        this.departureAirportIATACode = departureAirportIATACode;
        this.arrivalAirportIATACode = arrivalAirportIATACode;
        this.departureDate = departureDate;
    }

    public static FlightEntity parseJsonObject(JSONObject object) throws JSONException {

        int flightId = (int) object.get("flightId");
        int flightNumber = (int) object.get("flightNumber");

        String departureAirportIATACode = object.get("departureAirportIATACode").toString();
        String arrivalAirportIATACode =  object.get("arrivalAirportIATACode").toString();

        ZonedDateTime departureDate = ZonedDateTime.parse(object.get("departureDate").toString());

        return new FlightEntity(flightId, flightNumber, departureAirportIATACode, arrivalAirportIATACode, departureDate);
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureAirportIATACode() {
        return departureAirportIATACode;
    }

    public String getArrivalAirportIATACode() {
        return arrivalAirportIATACode;
    }

    public ZonedDateTime getDepartureDate() {
        return departureDate;
    }

}
