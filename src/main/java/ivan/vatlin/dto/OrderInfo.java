package ivan.vatlin.dto;

import java.time.LocalDate;

public class OrderInfo {
    private long id;
    private User user;
    private CarInfo carInfo;
    private LocalDate startLocalDate;
    private LocalDate endLocalDate;
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

    public LocalDate getStartLocalDate() {
        return startLocalDate;
    }

    public OrderInfo setStartLocalDate(LocalDate startLocalDate) {
        this.startLocalDate = startLocalDate;
        return this;
    }

    public LocalDate getEndLocalDate() {
        return endLocalDate;
    }

    public OrderInfo setEndLocalDate(LocalDate endLocalDate) {
        this.endLocalDate = endLocalDate;
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
