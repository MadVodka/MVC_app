package ivan.vatlin.mappers;

import ivan.vatlin.dto.CarInfo;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.dto.User;
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

        CarInfo carInfo = new CarInfo();
        carInfo.setCarId(rs.getLong("car_id"))
                .setBrand(rs.getString("brand"))
                .setModel(rs.getString("model"))
                .setYear(rs.getString("year_made"));

        orderInfo.setId(rs.getLong("id"))
                .setUser(user)
                .setStartDate(LocalDate.parse(rs.getString("start_date")))
                .setEndDate(LocalDate.parse(rs.getString("end_date")))
                .setCarInfo(carInfo)
                .setStatus(rs.getString("status"));

        return orderInfo;
    }
}
