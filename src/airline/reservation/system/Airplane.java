package airline.reservation.system;

public class Airplane {

    private String id;
    private String model;
    private String size;
    private String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Airplane(String id, String model, String size, String location) {
        this.id = id;
        this.model = model;
        this.size = size;
        this.location = location;
    }

}
