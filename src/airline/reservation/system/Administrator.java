package airline.reservation.system;

public class Administrator extends User {

    public Administrator(String userId, String name, String password) {
        super(userId, name, password, "ADMINISTRATOR");
    }

}
