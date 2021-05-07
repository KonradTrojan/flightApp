package com.airport.airportapp.controllers;

import com.airport.airportapp.jsonReader.JsonReader;
import com.airport.airportapp.model.cargo.CargoEntity;
import com.airport.airportapp.model.cargo.response.FlightDetails;
import com.airport.airportapp.model.flight.FlightEntity;
import com.airport.airportapp.model.flight.reponse.AirportDetails;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@RestController
public class Controller {


    ArrayList<FlightEntity> flightEntities;
    ArrayList<CargoEntity> cargoEntities;


    // url to jsons from application properties
    @Autowired
    private Controller(@Value("${urlFlightEntity.json}") String urlFlight, @Value("${urlCargoEntity.json}") String urlCargo) throws ParseException, IOException, JSONException {
        this.flightEntities = JsonReader.flightEntitiesFromJson(urlFlight);
        this.cargoEntities = JsonReader.cargoEntitiesFromJson(urlCargo);

    }

    // For requested IATA Airport Code and date responds :
    // Number of flights departing from this airport,
    // Number of flights arriving to this airport,
    // Total number (pieces) of baggage arriving to this airport,
    // Total number (pieces) of baggage departing from this airpor
    @PostMapping(value = "api/airport")
    @ResponseBody
    public ResponseEntity airportDetails(@RequestParam String code, @RequestParam String date) {

            try {
                LocalDate givenDate = LocalDate.parse(date);


                // Flights from given Airport
                var flightsIdFromAirport = new TreeSet<Integer>();
                int flightFrom = (int) flightEntities.stream()
                        .filter(f -> f.getDepartureAirportIATACode().equals(code))
                        .filter(f -> f.getDepartureDate().toLocalDate().isEqual(givenDate))
                        .map(f -> flightsIdFromAirport.add(f.getFlightId()))
                        .count();

                // Sum of pieces from given airport
                int piecesFrom = cargoEntities.stream()
                        .filter(f -> flightsIdFromAirport.contains(f.getFlightId()))
                        .mapToInt(CargoEntity::getAllPieces).sum();


                // Flights to given Airport
                var flightsIdToAirport = new TreeSet<Integer>();
                int flightTo = (int) flightEntities.stream()
                        .filter(f -> f.getArrivalAirportIATACode().equals(code))
                        .filter(f -> f.getDepartureDate().toLocalDate().isEqual(givenDate))
                        .map(f -> flightsIdToAirport.add(f.getFlightId()))
                        .count();

                // Sum of pieces to given airport
                int piecesTo = cargoEntities.stream()
                        .filter(f -> flightsIdToAirport.contains(f.getFlightId()))
                        .mapToInt(CargoEntity::getAllPieces).sum();


                AirportDetails airportDetails = new AirportDetails(code, flightFrom, flightTo, piecesFrom, piecesTo);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(airportDetails);

            } catch (NullPointerException e){

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Flight not found.");

            } catch (DateTimeParseException e){
                e.printStackTrace();
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Wrong date format.");

            }
    }

    //For requested Flight Number and date will respond with following :
    // Cargo Weight for requested Flight
    // Baggage Weight for requested Flight
    // Total Weight for requested Flight

    @PostMapping(value = "api/cargo")
    @ResponseBody
    public ResponseEntity flightWeight(@RequestParam int flightNumber, @RequestParam String date) {

        try {

            LocalDate givenDate = LocalDate.parse(date);

            // looking for a flightId corresponding to the flightNumber and date
            int flightId = Objects.requireNonNull(flightEntities.stream()
                    .filter(f -> f.getFlightNumber() == flightNumber)
                    .filter(f -> f.getDepartureDate().toLocalDate().isEqual(givenDate))
                    .findAny().orElse(null)).getFlightId();

            double baggageSum = cargoEntities.stream()
                    .filter(b -> b.getFlightId() == flightId)
                    .mapToDouble(CargoEntity::getBaggageWeight).sum();

            double cargoSum = cargoEntities.stream()
                    .filter(b -> b.getFlightId() == flightId)
                    .mapToDouble(CargoEntity::getCargoWeight).sum();

            double totalWeight = baggageSum + cargoSum;

            FlightDetails flightDetails = new FlightDetails(
                    flightId, cargoSum, baggageSum, totalWeight
            );
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(flightDetails);

        } catch (NullPointerException e){

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Flight not found.");

        }catch (DateTimeParseException e){
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Wrong date format.");

        }
    }
}
