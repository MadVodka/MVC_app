package ivan.vatlin.dto;

import ivan.vatlin.enums.CarStatus;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "cars_spec_id")
    private CarSpecification carSpecification;

    @Column(name = "price_per_day")
    private double pricePerDay;

    @Column(name = "reg_number")
    private String registrationNumber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
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
