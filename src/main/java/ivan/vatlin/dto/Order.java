package ivan.vatlin.dto;

public class Order {
    private long id;
    private long carId;
    private String startDate;
    private String endDate;
    private long userId;
    private double pricePerDay;
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

//    public OrderStatus getOrderStatus() {
//        return orderStatus;
//    }
//
//    public Order setOrderStatus(OrderStatus orderStatus) {
//        this.orderStatus = orderStatus;
//        return this;
//    }

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
