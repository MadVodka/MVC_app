package ivan.vatlin.dto;

import java.time.LocalDate;

public class OrderInfo {
    private long id;
    private User user;
    private CarInfo carInfo;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public long getId() {
        return id;
    }

    public OrderInfo setId(long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public OrderInfo setUser(User user) {
        this.user = user;
        return this;
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public OrderInfo setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public OrderInfo setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public OrderInfo setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OrderInfo setStatus(String status) {
        this.status = status;
        return this;
    }
}
