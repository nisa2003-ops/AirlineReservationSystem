package com.airline;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import airline.reservation.system.UserService;
import airline.reservation.system.User;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class ProfileController {
    
    private StackPane contentArea;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField roleField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private UserService userService;

    private User currentUser; 

    public ProfileController() {
        userService = new UserService();
    }

    public void setUser(User user) {
        this.currentUser = user;
        populateUserDetails();
    }

    private void populateUserDetails() {
        if (currentUser != null) {
            usernameField.setText(currentUser.getName());
            roleField.setText(currentUser.getRole());
            passwordField.setText("");
        }
    }

    @FXML
    private void handleUpdateProfile() {
        String newPassword = passwordField.getText();

        if (newPassword.isEmpty()) {
            errorLabel.setText("Please enter a new password.");
            return;
        }

        currentUser.setPassword(newPassword);

        int result = userService.updateUser(currentUser);
        if (result > 0) {
            errorLabel.setText("Profile updated successfully.");
        } else {
            errorLabel.setText("Failed to update profile.");
        }
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
}
