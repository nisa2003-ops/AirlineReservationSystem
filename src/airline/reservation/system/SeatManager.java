package airline.reservation.system;

import java.sql.*;

public class SeatManager {

    public boolean createOrUpdateSeat(String flightId, String seatClass, int totalSeats, int availableSeats) {
        String query = "INSERT INTO seats (flight_id, seat_class, total_seats, available_seats) "
                + "VALUES (?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE total_seats = ?, available_seats = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, flightId);
            stmt.setString(2, seatClass);
            stmt.setInt(3, totalSeats);
            stmt.setInt(4, availableSeats);
            stmt.setInt(5, totalSeats);
            stmt.setInt(6, availableSeats);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getAvailableSeats(String flightId, String seatClass) {
        String query = "SELECT available_seats FROM seats WHERE flight_id = ? AND seat_class = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, flightId);
            stmt.setString(2, seatClass);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("available_seats");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean updateAvailableSeats(String flightId, String seatClass, int seatsToBook) {
        String query = "UPDATE seats SET available_seats = available_seats - ? WHERE flight_id = ? AND seat_class = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, seatsToBook);
            stmt.setString(2, flightId);
            stmt.setString(3, seatClass);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
