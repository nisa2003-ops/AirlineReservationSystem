package com.airline;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import airline.reservation.system.Booking;
import airline.reservation.system.BookingManager;
import airline.reservation.system.User;
import java.time.LocalDateTime;
import java.util.List;
import javafx.collections.FXCollections;

public class MyBookingsController {

    @FXML
    private TableView<Booking> bookingsTable;
    @FXML
    private TableColumn<Booking, String> bookingIdCol;
    @FXML
    private TableColumn<Booking, String> flightIdCol;
    @FXML
    private TableColumn<Booking, LocalDateTime> bookingDateCol;
    @FXML
    private TableColumn<Booking, String> seatClassCol;
    @FXML
    private TableColumn<Booking, String> seatNumberCol;
    @FXML
    private TableColumn<Booking, String> statusCol;

    private String username;

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
        loadBookings();
    }

    public void initialize() {
        bookingIdCol.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        flightIdCol.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        bookingDateCol.setCellValueFactory(new PropertyValueFactory<>("bookingTime"));
        seatClassCol.setCellValueFactory(new PropertyValueFactory<>("seatClass"));
        seatNumberCol.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));

    }

    private void loadBookings() {
        if (currentUser == null) {
            System.out.println("Error: currentUser is null. Cannot load bookings.");
            return;
        }

        BookingManager service = new BookingManager();

        List<Booking> bookings = service.getBookingsByUser(currentUser.getName());

        if (bookings != null && !bookings.isEmpty()) {
            bookingsTable.setItems(FXCollections.observableArrayList(bookings));
        } else {
            System.out.println("No bookings found for the user.");
        }
    }

}
