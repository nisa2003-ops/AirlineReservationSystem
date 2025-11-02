package com.airline;

import airline.reservation.system.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightManagementController {

    @FXML
    private TextField airplaneIdField;
    @FXML
    private TextField airplaneModelField;
    @FXML
    private TextField airplaneSizeField;
    @FXML
    private TextField airplaneLocationField;
    @FXML
    private TableView<Airplane> airplaneTable;
    @FXML
    private TableColumn<Airplane, String> airplaneIdCol;
    @FXML
    private TableColumn<Airplane, String> airplaneModelCol;
    @FXML
    private TableColumn<Airplane, String> airplaneSizeCol;
    @FXML
    private TableColumn<Airplane, String> airplaneLocationCol;

    @FXML
    private TextField airportCodeField;
    @FXML
    private TextField airportNameField;
    @FXML
    private TextField airportCountryField;
    @FXML
    private TableView<Airport> airportTable;
    @FXML
    private TableColumn<Airport, String> airportCodeCol;
    @FXML
    private TableColumn<Airport, String> airportNameCol;
    @FXML
    private TableColumn<Airport, String> airportCountryCol;

    @FXML
    private TextField flightIdField;
    @FXML
    private TextField scheduleAirplaneIdField;
    @FXML
    private TextField originCodeField;
    @FXML
    private TextField destinationCodeField;
    @FXML
    private TextField departureField;
    @FXML
    private TextField arrivalField;

    private ObservableList<Airplane> airplanes = FXCollections.observableArrayList();
    private ObservableList<Airport> airports = FXCollections.observableArrayList();
    private final AirportManager airportManager = new AirportManager();
    private final AirplaneManager airplaneManager = new AirplaneManager();

    @FXML
    public void initialize() {
        airplaneIdCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getId()));
        airplaneModelCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getModel()));
        airplaneSizeCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSize()));
        airplaneLocationCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getLocation()));
        airplaneTable.setItems(airplanes);

        airportCodeCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCode()));
        airportNameCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        airportCountryCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCountry()));
        airportTable.setItems(airports);
    }

    @FXML
    private void handleAddAirplane() {
        Airplane airplane = new Airplane(
                airplaneIdField.getText(),
                airplaneModelField.getText(),
                airplaneSizeField.getText(),
                airplaneLocationField.getText()
        );
        airplaneManager.insertAirplane(airplane);
        airplanes.add(airplane);
        clearAirplaneFields();
    }

    @FXML
    private void handleAddAirport() {
        Airport airport = new Airport(
                airportCodeField.getText(),
                airportNameField.getText(),
                airportCountryField.getText()
        );
        airportManager.insertAirport(airport);
        airports.add(airport);
        clearAirportFields();
    }

    @FXML
    private void handleScheduleFlight() {
        try {
            String flightId = flightIdField.getText();
            String airplaneId = scheduleAirplaneIdField.getText();
            String origin = originCodeField.getText();
            String destination = destinationCodeField.getText();
            LocalDateTime departure = LocalDateTime.parse(departureField.getText());
            LocalDateTime arrival = LocalDateTime.parse(arrivalField.getText());

            Flight flight = new Flight(flightId, airplaneId, origin, destination, departure, arrival, null);

            FlightManager manager = new FlightManager();
            System.out.println("Flight scheduled: " + flightId);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Invalid flight data.");
        }
    }

    private void clearAirplaneFields() {
        airplaneIdField.clear();
        airplaneModelField.clear();
        airplaneSizeField.clear();
        airplaneLocationField.clear();
    }

    private void clearAirportFields() {
        airportCodeField.clear();
        airportNameField.clear();
        airportCountryField.clear();
    }

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
    }
}
