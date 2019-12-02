package ivan.vatlin.services;

import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;

import java.util.List;

public interface OrderService {
    List<OrderInfo> getAllOrders();

    OrderInfo getOrderById(long id);

    List<OrderInfo> getOrdersByUserName(String userName);

    long createOrder(Order order);

    OrderInfo getUsersOrder(String userName, long orderId);
}
