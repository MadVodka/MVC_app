package ivan.vatlin.dao.jdbc;

import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.mappers.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(value = "database.api", havingValue = "jdbc")
public class OrderDao implements IOrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IUserDao userDao;

    @Override
    public List<OrderInfo> getAllOrders() {
        String sql = "select order_user.id, order_user.users_id, order_user.first_name, order_user.second_name, order_user.start_date, order_user.end_date, order_user.status, cars_info.car_id, cars_info.brand, cars_info.model, cars_info.year_made from \n" +
                "(select o.id, o.users_id, u.first_name, u.second_name, o.cars_id, o.start_date, o.end_date, o.status from orders o inner join users u on o.users_id=u.id) as order_user inner join \n" +
                "(select cars.id as car_id, c_s.brand, c_s.model, c_s.year_made from cars_specification c_s inner join cars on c_s.id = cars.cars_spec_id) as cars_info \n" +
                "on cars_info.car_id=order_user.cars_id";
        return jdbcTemplate.query(sql, new OrderInfoMapper());
    }

    @Override
    public OrderInfo getOrderById(long id) {
        String sql = "select order_user.id, order_user.users_id, order_user.first_name, order_user.second_name, order_user.start_date, order_user.end_date, order_user.status, cars_info.car_id, cars_info.brand, cars_info.model, cars_info.year_made from \n" +
                "(select o.id, o.users_id, u.first_name, u.second_name, o.cars_id, o.start_date, o.end_date, o.status from orders o inner join users u on o.users_id=u.id) as order_user inner join " +
                "(select cars.id as car_id, c_s.brand, c_s.model, c_s.year_made from cars_specification c_s inner join cars on c_s.id = cars.cars_spec_id) as cars_info " +
                "on cars_info.car_id=order_user.cars_id  where id = " + id;
        return jdbcTemplate.queryForObject(sql, new OrderInfoMapper());
    }

    @Override
    public List<OrderInfo> getOrdersByUserName(String userName) {
        String sql = "select order_user.id, order_user.users_id, order_user.first_name, order_user.second_name, order_user.start_date, order_user.end_date, order_user.status, cars_info.car_id, cars_info.brand, cars_info.model, cars_info.year_made from \n" +
                "(select o.id, o.users_id, u.first_name, u.second_name, o.cars_id, o.start_date, o.end_date, o.status from orders o inner join users u on o.users_id=u.id where u.user_name = ?) as order_user inner join " +
                "(select cars.id as car_id, c_s.brand, c_s.model, c_s.year_made from cars_specification c_s inner join cars on c_s.id = cars.cars_spec_id) as cars_info " +
                "on cars_info.car_id=order_user.cars_id";
        return jdbcTemplate.query(sql, new OrderInfoMapper(), userName);
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
                return jdbcTemplate.queryForObject(sql, new OrderInfoMapper(), userName);
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
    }
}
