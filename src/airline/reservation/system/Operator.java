package airline.reservation.system;

public class Operator extends User {

    public Operator(String userId, String name, String password) {
        super(userId, name, password, "OPERATOR");
    }

}
