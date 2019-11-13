package ivan.vatlin.dao;

import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;

import java.util.List;

public interface OrderDao {
    List<OrderInfo> getAllOrders();

    OrderInfo getOrderById(long id);

//    int updateOrderStatus(long id, OrderStatus orderStatus);

    int createOrder(Order order);
}
