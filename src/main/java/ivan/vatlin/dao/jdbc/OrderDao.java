package ivan.vatlin.dao.jdbc;

import ivan.vatlin.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@ConditionalOnBean(JdbcTemplate.class)
public class OrderDao implements IOrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private RowMapper<OrderInfo> orderInfoRowMapper = (resultSet, rowNum) -> {
        OrderInfo orderInfo = new OrderInfo();

        User user = new User();
        user.setId(resultSet.getLong("users_id"))
                .setFirstName(resultSet.getString("first_name"))
                .setSecondName(resultSet.getString("second_name"));

        CarSpecification carSpecification = new CarSpecification();
        carSpecification.setBrand(resultSet.getString("brand"))
                .setModel(resultSet.getString("model"))
                .setYearMade(resultSet.getInt("year_made"));

        Car car = new Car();
        car.setId(resultSet.getLong("car_id"))
                .setCarSpecification(carSpecification);

        orderInfo.setId(resultSet.getLong("id"))
                .setUser(user)
                .setStartDate(LocalDate.parse(resultSet.getString("start_date")))
                .setEndDate(LocalDate.parse(resultSet.getString("end_date")))
                .setCar(car)
                .setStatus(resultSet.getString("status"));

        return orderInfo;
    };

    @Override
    public List<OrderInfo> getAllOrders() {
        String sql = "select order_user.id, order_user.users_id, order_user.first_name, order_user.second_name, order_user.start_date, order_user.end_date, order_user.status, cars_info.car_id, cars_info.brand, cars_info.model, cars_info.year_made from \n" +
                "(select o.id, o.users_id, u.first_name, u.second_name, o.cars_id, o.start_date, o.end_date, o.status from orders o inner join users u on o.users_id=u.id) as order_user inner join \n" +
                "(select cars.id as car_id, c_s.brand, c_s.model, c_s.year_made from cars_specification c_s inner join cars on c_s.id = cars.cars_spec_id) as cars_info \n" +
                "on cars_info.car_id=order_user.cars_id";
        return jdbcTemplate.query(sql, orderInfoRowMapper);
    }

    @Override
    public OrderInfo getOrderById(long id) {
        String sql = "select order_user.id, order_user.users_id, order_user.first_name, order_user.second_name, order_user.start_date, order_user.end_date, order_user.status, cars_info.car_id, cars_info.brand, cars_info.model, cars_info.year_made from \n" +
                "(select o.id, o.users_id, u.first_name, u.second_name, o.cars_id, o.start_date, o.end_date, o.status from orders o inner join users u on o.users_id=u.id) as order_user inner join " +
                "(select cars.id as car_id, c_s.brand, c_s.model, c_s.year_made from cars_specification c_s inner join cars on c_s.id = cars.cars_spec_id) as cars_info " +
                "on cars_info.car_id=order_user.cars_id  where id = " + id;
        return jdbcTemplate.queryForObject(sql, orderInfoRowMapper);
    }

    @Override
    public List<OrderInfo> getOrdersByUserName(String userName) {
        String sql = "select order_user.id, order_user.users_id, order_user.first_name, order_user.second_name, order_user.start_date, order_user.end_date, order_user.status, cars_info.car_id, cars_info.brand, cars_info.model, cars_info.year_made from \n" +
                "(select o.id, o.users_id, u.first_name, u.second_name, o.cars_id, o.start_date, o.end_date, o.status from orders o inner join users u on o.users_id=u.id where u.user_name = ?) as order_user inner join " +
                "(select cars.id as car_id, c_s.brand, c_s.model, c_s.year_made from cars_specification c_s inner join cars on c_s.id = cars.cars_spec_id) as cars_info " +
                "on cars_info.car_id=order_user.cars_id";
        return jdbcTemplate.query(sql, orderInfoRowMapper, userName);
    }

    @Override
    public int createOrder(Order order) {
        String sql = "insert into orders (start_date, end_date, users_id, cars_id, price_per_day, total_price)" +
                " values (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, order.getStartDate(), order.getEndDate(), order.getUserId(),
                order.getCarId(), 0, 0);
    }

    @Override
    public OrderInfo getUsersOrder(String userName, long orderId) {
            String sql = "SELECT \n" +
                    "    order_user.id,\n" +
                    "    order_user.users_id,\n" +
                    "    order_user.first_name,\n" +
                    "    order_user.second_name,\n" +
                    "    order_user.start_date,\n" +
                    "    order_user.end_date,\n" +
                    "    order_user.status,\n" +
                    "    cars_info.car_id,\n" +
                    "    cars_info.brand,\n" +
                    "    cars_info.model,\n" +
                    "    cars_info.year_made\n" +
                    "FROM\n" +
                    "    (SELECT \n" +
                    "        o.id,\n" +
                    "            o.users_id,\n" +
                    "            u.first_name,\n" +
                    "            u.second_name,\n" +
                    "            o.cars_id,\n" +
                    "            o.start_date,\n" +
                    "            o.end_date,\n" +
                    "            o.status\n" +
                    "    FROM\n" +
                    "        orders o\n" +
                    "    INNER JOIN users u ON o.users_id = u.id where u.user_name = ?) AS order_user\n" +
                    "        INNER JOIN\n" +
                    "    (SELECT \n" +
                    "        cars.id AS car_id, c_s.brand, c_s.model, c_s.year_made\n" +
                    "    FROM\n" +
                    "        cars_specification c_s\n" +
                    "    INNER JOIN cars ON c_s.id = cars.cars_spec_id) AS cars_info ON cars_info.car_id = order_user.cars_id\n" +
                    "WHERE\n" +
                    "    id = " + orderId;
            try {
                return jdbcTemplate.queryForObject(sql, orderInfoRowMapper, userName);
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
    }
}
