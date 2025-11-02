package airline.reservation.system;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {


    public boolean createBooking(String userId, String flightId, String seatClass, String seatNumber) {
        String query = "INSERT INTO bookings (user_id, flight_id, booking_date, seat_class, seat_number, status) " +
                       "VALUES (?, ?, NOW(), ?, ?, 'CONFIRMED')";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, userId);
            stmt.setString(2, flightId);
            stmt.setString(3, seatClass);
            stmt.setString(4, seatNumber);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Booking> getBookingsByUser(String username) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(2, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String bookingId = rs.getString("booking_id");
                String flightId = rs.getString("flight_id");
                String seatClass = rs.getString("seat_class");
                String seatNumber = rs.getString("seat_number");
                Timestamp bookingTime = rs.getTimestamp("booking_date");
                String status = rs.getString("status");

                Booking booking = new Booking(bookingId, username, flightId, seatClass, seatNumber, bookingTime.toLocalDateTime());
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

   public List<Booking> getBookingsByUserId(String userId) {
    List<Booking> bookings = new ArrayList<>();
    String sql = "SELECT * FROM bookings WHERE user_id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, userId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Booking booking = new Booking();
            booking.setBookingId(rs.getString("booking_id"));
            booking.setCustomerId(rs.getString("user_id"));
            booking.setFlightId(rs.getString("flight_id"));
            booking.setSeatClass(rs.getString("seat_class"));
            booking.setSeatNumber(rs.getString("seat_number"));
            booking.setBookingTime(rs.getTimestamp("booking_date").toLocalDateTime());

            bookings.add(booking);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return bookings;
}

}
