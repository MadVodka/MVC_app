package ivan.vatlin.dao;

import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.mappers.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<OrderInfo> getAllOrders() {
        String sql = "select order_user.id, order_user.users_id, order_user.first_name, order_user.second_name, order_user.start_date, order_user.end_date, order_user.status, cars_info.car_id, cars_info.brand, cars_info.model, cars_info.year_made from \n" +
                "(select o.id, o.users_id, u.first_name, u.second_name, o.cars_id, o.start_date, o.end_date, o.status from orders o inner join users u on o.users_id=u.id) as order_user inner join \n" +
                "(select cars.id as car_id, c_s.brand, c_s.model, c_s.year_made from cars_specification c_s inner join cars on c_s.id = cars.cars_spec_id) as cars_info \n" +
                "on cars_info.car_id=order_user.cars_id";
        return jdbcTemplate.query(sql, new OrderInfoMapper());
    }

    public OrderInfo getOrderById(long id) {
        String sql = "select order_user.id, order_user.users_id, order_user.first_name, order_user.second_name, order_user.start_date, order_user.end_date, order_user.status, cars_info.car_id, cars_info.brand, cars_info.model, cars_info.year_made from \n" +
                "(select o.id, o.users_id, u.first_name, u.second_name, o.cars_id, o.start_date, o.end_date, o.status from orders o inner join users u on o.users_id=u.id) as order_user inner join " +
                "(select cars.id as car_id, c_s.brand, c_s.model, c_s.year_made from cars_specification c_s inner join cars on c_s.id = cars.cars_spec_id) as cars_info " +
                "on cars_info.car_id=order_user.cars_id  where id = " + id;
        return jdbcTemplate.queryForObject(sql, new OrderInfoMapper());
    }

    public List<OrderInfo> getOrdersByUserName(String userName) {
        String sql = "select order_user.id, order_user.users_id, order_user.first_name, order_user.second_name, order_user.start_date, order_user.end_date, order_user.status, cars_info.car_id, cars_info.brand, cars_info.model, cars_info.year_made from \n" +
                "(select o.id, o.users_id, u.first_name, u.second_name, o.cars_id, o.start_date, o.end_date, o.status from orders o inner join users u on o.users_id=u.id where u.user_name = ?) as order_user inner join " +
                "(select cars.id as car_id, c_s.brand, c_s.model, c_s.year_made from cars_specification c_s inner join cars on c_s.id = cars.cars_spec_id) as cars_info " +
                "on cars_info.car_id=order_user.cars_id";
        return jdbcTemplate.query(sql, new OrderInfoMapper(), userName);
    }

    public int createOrder(Order order) {
        String sql = "insert into orders (start_date, end_date, users_id, cars_id, price_per_day, total_price)" +
                " values (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, order.getStartDate(), order.getEndDate(), order.getUserId(),
                order.getCarId(), 0, 0);
    }
}
