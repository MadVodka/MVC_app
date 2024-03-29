package ivan.vatlin.dao.jdbc;

import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;

import java.util.List;

public interface IOrderDao {
    List<OrderInfo> getAllOrders();

    OrderInfo getOrderById(long id);

    List<OrderInfo> getOrdersByUserName(String userName);

    int createOrder(Order order);

    OrderInfo getUsersOrder(String userName, long orderId);
}
