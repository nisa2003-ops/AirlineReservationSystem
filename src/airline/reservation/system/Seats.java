package airline.reservation.system;

public class Seats {

    private int firstClass;
    private int businessClass;
    private int economyClass;
    private double firstClassPrice;
    private double businessClassPrice;
    private double economyClassPrice;

    public int getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(int firstClass) {
        this.firstClass = firstClass;
    }

    public int getBusinessClass() {
        return businessClass;
    }

    public void setBusinessClass(int businessClass) {
        this.businessClass = businessClass;
    }

    public int getEconomyClass() {
        return economyClass;
    }

    public void setEconomyClass(int economyClass) {
        this.economyClass = economyClass;
    }

    public double getFirstClassPrice() {
        return firstClassPrice;
    }

    public void setFirstClassPrice(double firstClassPrice) {
        this.firstClassPrice = firstClassPrice;
    }

    public double getBusinessClassPrice() {
        return businessClassPrice;
    }

    public void setBusinessClassPrice(double businessClassPrice) {
        this.businessClassPrice = businessClassPrice;
    }

    public Double getEconomyClassPrice() {
        return economyClassPrice;
    }

    public void setEconomyClassPrice(Double economyClassPrice) {
        this.economyClassPrice = economyClassPrice;
    }

    public Seats(int firstClass, int businessClass, int economyClass, double firstClassPrice, double businessClassPrice, Double economyClassPrice) {
        this.firstClass = firstClass;
        this.businessClass = businessClass;
        this.economyClass = economyClass;
        this.firstClassPrice = firstClassPrice;
        this.businessClassPrice = businessClassPrice;
        this.economyClassPrice = economyClassPrice;
    }

}
