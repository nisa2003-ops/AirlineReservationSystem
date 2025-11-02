package com.airline;

import airline.reservation.system.User;
import airline.reservation.system.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class UserManagementController {

    @FXML private TextField userIdField;
    @FXML private TextField nameField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> roleComboBox;

    @FXML private TableView<User> userTable;
    @FXML private TableColumn<User, String> colUserId;
    @FXML private TableColumn<User, String> colName;
    @FXML private TableColumn<User, String> colRole;

    private final UserService userService = new UserService();
    private ObservableList<User> userList;

    private User selectedUser;

    @FXML
    public void initialize() {
        roleComboBox.setItems(FXCollections.observableArrayList("ADMIN", "OPERATOR", "CUSTOMER"));

        colUserId.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getUserId()));
        colName.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        colRole.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getRole()));

        refreshUserTable();

        userTable.setOnMouseClicked(this::onRowSelected);
    }

    private void refreshUserTable() {
        userList = FXCollections.observableArrayList(userService.getAllUsers());
        userTable.setItems(userList);
    }

    private void onRowSelected(MouseEvent event) {
        selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userIdField.setText(selectedUser.getUserId());
            nameField.setText(selectedUser.getName());
            passwordField.setText(selectedUser.getPassword());
            roleComboBox.setValue(selectedUser.getRole());
        }
    }

    @FXML
    private void addUser() {
        String userId = userIdField.getText();
        String name = nameField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();

        if (userId.isEmpty() || name.isEmpty() || password.isEmpty() || role == null) {
            showAlert("All fields are required.");
            return;
        }

        User user = new User(userId, name, password, role);
        userService.createUser(user);
        refreshUserTable();
        clearFields();
    }

    @FXML
    private void updateUser() {
        if (selectedUser == null) {
            showAlert("Please select a user to update.");
            return;
        }

        selectedUser.setName(nameField.getText());
        selectedUser.setPassword(passwordField.getText());
        selectedUser.setRole(roleComboBox.getValue());

        userService.updateUser(selectedUser);
        refreshUserTable();
        clearFields();
    }

    @FXML
    private void deleteUser() {
        if (selectedUser == null) {
            showAlert("Please select a user to delete.");
            return;
        }

        userService.deleteUser(selectedUser.getUserId());
        refreshUserTable();
        clearFields();
    }

    private void clearFields() {
        userIdField.clear();
        nameField.clear();
        passwordField.clear();
        roleComboBox.setValue(null);
        selectedUser = null;
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // For AdminDashboard to pass the current user (if needed)
    private User currentUser;
    public void setUser(User user) {
        this.currentUser = user;
    }
}
