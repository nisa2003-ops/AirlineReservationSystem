package com.airline;

import airline.reservation.system.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML
    private StackPane mainContent;
    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
    }

    @FXML
    private void handleUserManagement(ActionEvent event) {
        showUserPopup();
    }

    @FXML
    private void handleFlightManagement(ActionEvent event) {
        showFlightPopup();
    }

    @FXML
    private void handleBookingReports(ActionEvent event) {
        loadContent("BookingReports.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            Parent loginView = FXMLLoader.load(getClass().getResource("/com/airline/LoginPage.fxml"));
            Stage stage = (Stage) mainContent.getScene().getWindow();
            stage.setScene(new Scene(loginView));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Logout clicked");
    }

    private void loadContent(String fxmlFile) {
        try {
            Node content = FXMLLoader.load(getClass().getResource("/airline/reservation/system/" + fxmlFile));
            mainContent.getChildren().setAll(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stage currentPopup;

    private void showUserPopup() {
        try {
            if (currentPopup != null) {
                currentPopup.close();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/UserManagement.fxml"));
            Parent root = loader.load();

            UserManagementController controller = loader.getController();
            controller.setUser(currentUser);

            currentPopup = new Stage();
            currentPopup.initModality(Modality.APPLICATION_MODAL);
            currentPopup.setTitle("User Profile");
            currentPopup.setScene(new Scene(root));

            Stage mainStage = (Stage) mainContent.getScene().getWindow();

            currentPopup.initOwner(mainStage);

            currentPopup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        private void showFlightPopup() {
        try {
            if (currentPopup != null) {
                currentPopup.close();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/FlightManagement.fxml"));
            Parent root = loader.load();

            FlightManagementController controller = loader.getController();
            controller.setUser(currentUser);

            currentPopup = new Stage();
            currentPopup.initModality(Modality.APPLICATION_MODAL);
            currentPopup.setTitle("User Profile");
            currentPopup.setScene(new Scene(root));

            Stage mainStage = (Stage) mainContent.getScene().getWindow();

            currentPopup.initOwner(mainStage);

            currentPopup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
