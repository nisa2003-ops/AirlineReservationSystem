package com.airline;

import airline.reservation.system.Flight;
import airline.reservation.system.FlightManager;
import airline.reservation.system.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;

public class SearchFlightsController {

    @FXML
    private TextField originField;
    @FXML
    private TextField destinationField;
    @FXML
    private DatePicker departureDatePicker;

    @FXML
    private TableView<Flight> flightsTable;
    @FXML
    private TableColumn<Flight, String> flightIdCol;
    @FXML
    private TableColumn<Flight, String> airplaneIdCol;
    @FXML
    private TableColumn<Flight, String> originCol;
    @FXML
    private TableColumn<Flight, String> destinationCol;
    @FXML
    private TableColumn<Flight, String> departureCol;
    @FXML
    private TableColumn<Flight, String> arrivalCol;

    private final FlightManager flightManager = new FlightManager();
    private User currentUser;

    @FXML
    public void initialize() {
        flightIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        airplaneIdCol.setCellValueFactory(new PropertyValueFactory<>("airplaneId"));
        originCol.setCellValueFactory(new PropertyValueFactory<>("origin"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        departureCol.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getDeparture().toString()));
        arrivalCol.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getArrival().toString()));
    }
    
    public void setUser(User user) {
        this.currentUser = user;
    }

    @FXML
    private void handleSearch() {
        String origin = originField.getText().trim();
        String destination = destinationField.getText().trim();
        LocalDate departureDate = departureDatePicker.getValue();

        if (origin.isEmpty() && destination.isEmpty()) {
            showAlert("Please enter at least origin or destination.");
            return;
        }

        List<Flight> flights = flightManager.getFlightsByAirport(origin);

        if (!destination.isEmpty()) {
            flights = flights.stream()
                    .filter(f -> f.getDestination().equalsIgnoreCase(destination))
                    .collect(Collectors.toList());
        }

        if (departureDate != null) {
            flights = flights.stream()
                    .filter(f -> f.getDeparture().toLocalDate().equals(departureDate))
                    .collect(Collectors.toList());
        }

        flightsTable.setItems(FXCollections.observableArrayList(flights));
    }

    @FXML
    private void handleBookFlight() {
        Flight selected = flightsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select a flight to book.");
            return;
        }

        showAlert("You selected flight: " + selected.getId());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Flight Search");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
