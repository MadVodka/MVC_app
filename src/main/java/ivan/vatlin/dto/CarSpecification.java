package ivan.vatlin.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "cars_specification")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carSpecification", propOrder = {
        "id",
        "brand",
        "model",
        "yearMade"
})
public class CarSpecification {
    @Id
    private long id;

    @Column
    @XmlElement(required = true)
    private String brand;

    @Column
    @XmlElement(required = true)
    private String model;

    @Column(name = "year_made")
    @XmlElement(required = true)
    private int yearMade;

    public long getId() {
        return id;
    }

    public CarSpecification setId(long id) {
        this.id = id;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public CarSpecification setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarSpecification setModel(String model) {
        this.model = model;
        return this;
    }

    public int getYearMade() {
        return yearMade;
    }

    public CarSpecification setYearMade(int yearMade) {
        this.yearMade = yearMade;
        return this;
    }

    @Override
    public String toString() {
        return "CarSpecification{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearMade=" + yearMade +
                '}';
    }
}
