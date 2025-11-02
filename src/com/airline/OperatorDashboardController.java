package com.airline;

import airline.reservation.system.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class OperatorDashboardController {

    private User operatorId;
    private User currentUser;

     public void setUser(User user) {
        this.currentUser = user;
    }

    @FXML
    private void handleSearchFlights(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/SearchFlights.fxml"));
            Parent root = loader.load();

            SearchFlightsController controller = loader.getController();
            controller.setUser(currentUser);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Search Flights");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMyBookings(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/MyBookings.fxml"));
            Parent root = loader.load();

            MyBookingsController controller = loader.getController();
            controller.setUser(currentUser);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("My Bookings");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCreateBookingForCustomer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/CreateBooking.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Create Booking for Customer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOperationalReports(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/OperationalReports.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Operational Reports");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/LoginPage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
