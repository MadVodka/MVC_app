package ivan.vatlin.dto;

import ivan.vatlin.enums.CarStatus;

public class Car {
    private long id;
    private CarSpecification carSpecification;
    private double pricePerDay;
    private String registrationNumber;
    private CarStatus carStatus;

    public long getId() {
        return id;
    }

    public Car setId(long id) {
        this.id = id;
        return this;
    }

    public CarSpecification getCarSpecification() {
        return carSpecification;
    }

    public Car setCarSpecification(CarSpecification carSpecification) {
        this.carSpecification = carSpecification;
        return this;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public Car setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
        return this;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Car setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public Car setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
        return this;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carSpecification=" + carSpecification +
                ", pricePerDay=" + pricePerDay +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", carStatus=" + carStatus +
                '}' + "\n";
    }
}
