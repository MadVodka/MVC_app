package ivan.vatlin.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private long id;

    @Column(name = "cars_id")
    private long carId;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "users_id")
    private long userId;

    @Column(name = "price_per_day")
    private double pricePerDay;

    @Column(name = "total_price")
    private double totalPrice;

    public long getId() {
        return id;
    }

    public Order setId(long id) {
        this.id = id;
        return this;
    }

    public long getCarId() {
        return carId;
    }

    public Order setCarId(long carId) {
        this.carId = carId;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public Order setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public Order setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public Order setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public Order setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
        return this;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Order setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", carId=" + carId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", userId=" + userId +
//                ", orderStatus=" + orderStatus +
                ", pricePerDay=" + pricePerDay +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
