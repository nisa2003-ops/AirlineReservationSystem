package airline.reservation.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AirplaneManager{

    public void insertAirplane(Airplane airplane) {
        String query = "INSERT INTO airplanes (airplane_id, model, size, current_location_airport_code) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, airplane.getId());
            stmt.setString(2, airplane.getModel());
            stmt.setString(3, airplane.getSize());
            stmt.setString(4, airplane.getLocation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
