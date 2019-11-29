package ivan.vatlin.dto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class OrderInfo {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cars_id")
    private Car car;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column
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

    public Car getCar() {
        return car;
    }

    public OrderInfo setCar(Car car) {
        this.car = car;
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
