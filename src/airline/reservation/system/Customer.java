package airline.reservation.system;

public class Customer extends User {

    public Customer(String userId, String name, String password) {
        super(userId, name, password, "CUSTOMER");
    }

}
