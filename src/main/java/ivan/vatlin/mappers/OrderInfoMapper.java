package ivan.vatlin.mappers;

import ivan.vatlin.dto.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderInfoMapper implements RowMapper<OrderInfo> {
    public OrderInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderInfo orderInfo = new OrderInfo();

        User user = new User();
        user.setId(rs.getLong("users_id"))
                .setFirstName(rs.getString("first_name"))
                .setSecondName(rs.getString("second_name"));

        CarSpecification carSpecification = new CarSpecification();
        carSpecification.setBrand(rs.getString("brand"))
                .setModel(rs.getString("model"))
                .setYearMade(rs.getInt("year_made"));

        Car car = new Car();
        car.setId(rs.getLong("car_id"))
                .setCarSpecification(carSpecification);

        orderInfo.setId(rs.getLong("id"))
                .setUser(user)
                .setStartDate(LocalDate.parse(rs.getString("start_date")))
                .setEndDate(LocalDate.parse(rs.getString("end_date")))
                .setCar(car)
                .setStatus(rs.getString("status"));

        return orderInfo;
    }
}
