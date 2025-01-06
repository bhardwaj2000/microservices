package com.example.solaceToDB.SolaceToDB;

import java.time.LocalDateTime;
import java.util.List;

public class InputRQ {

    private String airline;
    private String region;
    private int requestId;
    private Flight flight;
    private List<Item> items;
    private int totalPassengers;
    private String lastUpdated;

    // Getters and Setters
    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotalPassengers() {
        return totalPassengers;
    }

    public void setTotalPassengers(int totalPassengers) {
        this.totalPassengers = totalPassengers;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // Inner class for Flight
    public static class Flight {
        private String flightModel;
        private String flightRoute;

        // Getters and Setters
        public String getFlightModel() {
            return flightModel;
        }

        public void setFlightModel(String flightModel) {
            this.flightModel = flightModel;
        }

        public String getFlightRoute() {
            return flightRoute;
        }

        public void setFlightRoute(String flightRoute) {
            this.flightRoute = flightRoute;
        }
    }

    // Inner class for Items
    public static class Item {
        private String origin;
        private String destination;
        private String status;

        // Getters and Setters
        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
