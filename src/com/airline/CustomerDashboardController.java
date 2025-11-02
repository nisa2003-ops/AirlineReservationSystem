package com.airline;

import airline.reservation.system.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import com.airline.ProfileController;

public class CustomerDashboardController {

    private ProfileController profileController;

    @FXML
    private StackPane contentArea;
    private String username;
    private User user;

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleSearchFlights() {
        showSearchFlightsPopup();

    }

    @FXML
    private void handleMyBookings() {
        showMyBookingsPopup();

    }

    @FXML
    private void handleProfile() {
        showProfilePopup();
        profileController.setUser(currentUser);
    }

    @FXML
    private void handleLogout() {
        try {
            Parent loginView = FXMLLoader.load(getClass().getResource("/com/airline/LoginPage.fxml"));
            Stage stage = (Stage) contentArea.getScene().getWindow();
            stage.setScene(new Scene(loginView));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stage currentPopup;

    private void loadView(String fxmlFile) {
        try {
            if (currentPopup != null) {
                currentPopup.close();
                currentPopup = null;
            }

            Parent view = FXMLLoader.load(getClass().getResource(fxmlFile));
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Label welcomeLabel;
    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
        if (welcomeLabel != null) {
            welcomeLabel.setText("Welcome, " + user.getName() + "!");
        }
    }

    private void showProfilePopup() {
        try {
            if (currentPopup != null) {
                currentPopup.close();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/Profile.fxml"));
            Parent root = loader.load();

            ProfileController controller = loader.getController();
            controller.setUser(currentUser);

            currentPopup = new Stage();
            currentPopup.initModality(Modality.APPLICATION_MODAL);
            currentPopup.setTitle("User Profile");
            currentPopup.setScene(new Scene(root));

            Stage mainStage = (Stage) contentArea.getScene().getWindow();
            currentPopup.initOwner(mainStage);

            currentPopup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMyBookingsPopup() {
        try {
            if (currentPopup != null) {
                currentPopup.close();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/MyBookings.fxml"));
            Parent root = loader.load();

            MyBookingsController controller = loader.getController();
            controller.setUser(user);

            currentPopup = new Stage();
            currentPopup.initModality(Modality.APPLICATION_MODAL);
            currentPopup.setTitle("My Bookings");
            currentPopup.setScene(new Scene(root));

            Stage mainStage = (Stage) contentArea.getScene().getWindow();
            currentPopup.initOwner(mainStage);

            currentPopup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSearchFlightsPopup() {
        try {
            if (currentPopup != null) {
                currentPopup.close();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/SearchFlights.fxml"));
            Parent root = loader.load();

            SearchFlightsController controller = loader.getController();
            controller.setUser(currentUser);

            currentPopup = new Stage();
            currentPopup.initModality(Modality.APPLICATION_MODAL);
            currentPopup.setTitle("User Profile");
            currentPopup.setScene(new Scene(root));

            Stage mainStage = (Stage) contentArea.getScene().getWindow();
            currentPopup.initOwner(mainStage);

            currentPopup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
