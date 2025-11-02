package airline.reservation.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AirportManager {
    public void insertAirport(Airport airport) {
        String query = "INSERT INTO airports (code, name, country) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, airport.getCode());
            stmt.setString(2, airport.getName());
            stmt.setString(3, airport.getCountry());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
