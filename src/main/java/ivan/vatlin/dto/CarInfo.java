package ivan.vatlin.dto;

public class CarInfo {
    private long carId;
    private String brand;
    private String model;
    private String year;

    public long getCarId() {
        return carId;
    }

    public CarInfo setCarId(long carId) {
        this.carId = carId;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public CarInfo setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarInfo setModel(String model) {
        this.model = model;
        return this;
    }

    public String getYear() {
        return year;
    }

    public CarInfo setYear(String year) {
        this.year = year;
        return this;
    }
}
