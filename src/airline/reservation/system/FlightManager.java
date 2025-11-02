package airline.reservation.system;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightManager {

    public Flight getFlightById(String flightId) {
        String query = "SELECT * FROM flights WHERE flight_id = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, flightId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String airplaneId = rs.getString("airplane_id");
                String originCode = rs.getString("origin_code");
                String destinationCode = rs.getString("destination_code");
                Timestamp departureTime = rs.getTimestamp("departure_time");
                Timestamp arrivalTime = rs.getTimestamp("arrival_time");

                return new Flight(flightId, airplaneId, originCode, destinationCode,
                        departureTime.toLocalDateTime(), arrivalTime.toLocalDateTime(), null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Flight> getFlightsByAirport(String airportCode) {
        List<Flight> flights = new ArrayList<>();
        String query = "SELECT * FROM flights WHERE origin_code = ? OR destination_code = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, airportCode);
            stmt.setString(2, airportCode);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String flightId = rs.getString("flight_id");
                String airplaneId = rs.getString("airplane_id");
                String originCode = rs.getString("origin_code");
                String destinationCode = rs.getString("destination_code");
                Timestamp departureTime = rs.getTimestamp("departure_time");
                Timestamp arrivalTime = rs.getTimestamp("arrival_time");

                Flight flight = new Flight(flightId, airplaneId, originCode, destinationCode,
                        departureTime.toLocalDateTime(), arrivalTime.toLocalDateTime(), null);
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
}
