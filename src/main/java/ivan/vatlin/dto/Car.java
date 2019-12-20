package ivan.vatlin.dto;

import ivan.vatlin.enums.CarStatus;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "cars")
@DiscriminatorColumn
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Car", propOrder = {
        "id",
        "carSpecification",
        "pricePerDay",
        "registrationNumber",
        "carStatus"
})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cars_spec_id")
    @XmlElement(required = true)
    private CarSpecification carSpecification;

    @Column(name = "price_per_day")
    @XmlElement(required = true)
    private double pricePerDay;

    @Column(name = "reg_number")
    @XmlElement(required = true)
    private String registrationNumber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @XmlElement
    private CarStatus carStatus = CarStatus.IDLE; // by default is IDLE

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return id == car.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
