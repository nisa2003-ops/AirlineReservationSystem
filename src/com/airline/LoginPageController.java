package com.airline;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import airline.reservation.system.UserService;
import airline.reservation.system.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LoginPageController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private UserService userService;

    public LoginPageController() {
        userService = new UserService();
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please fill in both username and password.");
            return;
        }

        User user = userService.getUserByUsernameAndPassword(username, password);

        if (user != null) {
            if (user.getRole().equals("ADMINISTRATOR")) {
                navigateToAdminDashboard(user);
            } else if (user.getRole().equals("CUSTOMER")) {
                navigateToCustomerDashboard(user);
            } else if (user.getRole().equals("OPERATOR")) {
                navigateToOperatorDashboard(user);
            }
        } else {
            errorLabel.setText("Invalid credentials, please try again.");
        }
    }

    private void navigateToCustomerDashboard(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/CustomerDashboard.fxml"));
            Parent dashboardRoot = loader.load();

            CustomerDashboardController dashboardController = loader.getController();
            dashboardController.setUser(user);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene dashboardScene = new Scene(dashboardRoot);
            stage.setScene(dashboardScene);
            stage.setTitle("Customer Dashboard");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Failed to load the Customer Dashboard.");
        }
    }

    private void navigateToAdminDashboard(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/AdminDashboard.fxml"));
            Parent dashboardRoot = loader.load();

            AdminDashboardController dashboardController = loader.getController();
            dashboardController.setUser(user);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene dashboardScene = new Scene(dashboardRoot);
            stage.setScene(dashboardScene);
            stage.setTitle("Admin Dashboard");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Failed to load the Admin Dashboard.");
        }
    }

    private void navigateToOperatorDashboard(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/airline/OperatorDashboard.fxml"));
            Parent dashboardRoot = loader.load();

            OperatorDashboardController dashboardController = loader.getController();
            dashboardController.setUser(user);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene dashboardScene = new Scene(dashboardRoot);
            stage.setScene(dashboardScene);
            stage.setTitle("Operator Dashboard");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Failed to load the Customer Dashboard.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
