//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.13 at 11:15:48 AM SAMT 
//


package ivan.vatlin.soap.cars;

import ivan.vatlin.dto.Car;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cars" type="{https://www.ivan.vatlin/cars}Car"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "car"
})
@XmlRootElement(name = "CarCreateRequest")
public class CarCreateRequest {

    @XmlElement(required = true)
    protected Car car;

    /**
     * Gets the value of the cars property.
     * 
     * @return
     *     possible object is
     *     {@link Car }
     *     
     */
    public Car getCar() {
        return car;
    }

    /**
     * Sets the value of the cars property.
     * 
     * @param value
     *     allowed object is
     *     {@link Car }
     *     
     */
    public void setCar(Car value) {
        this.car = value;
    }

}
