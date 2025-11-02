package com.airline;

import airline.reservation.system.BookingManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CreateBookingController {

    @FXML
    private TextField customerIdField;
    @FXML
    private TextField flightIdField;
    @FXML
    private ComboBox<String> seatClassBox;
    @FXML
    private TextField seatNumberField;
    @FXML
    private Label statusLabel;

    private final BookingManager bookingManager = new BookingManager();

    @FXML
    public void initialize() {
        seatClassBox.getItems().addAll("ECONOMY", "BUSINESS", "FIRST");
    }

    @FXML
    private void handleCreateBooking() {
        String customerId = customerIdField.getText().trim();
        String flightId = flightIdField.getText().trim();
        String seatClass = seatClassBox.getValue();
        String seatNumber = seatNumberField.getText().trim();

        if (customerId.isEmpty() || flightId.isEmpty() || seatClass == null || seatNumber.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        boolean success = bookingManager.createBooking(customerId, flightId, seatClass, seatNumber);
        if (success) {
            statusLabel.setStyle("-fx-text-fill: green;");
            statusLabel.setText("Booking created successfully!");
        } else {
            statusLabel.setStyle("-fx-text-fill: red;");
            statusLabel.setText("Failed to create booking.");
        }
    }
}
