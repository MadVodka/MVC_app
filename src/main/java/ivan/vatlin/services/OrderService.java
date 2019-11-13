package ivan.vatlin.services;

import ivan.vatlin.dao.OrderDao;
import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public List<OrderInfo> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public OrderInfo getOrderById(long id) {
        try {
            return orderDao.getOrderById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

//    public int approveOrder(long id) {
//        return orderDao.updateOrderStatus(id, OrderStatus.APPROVED);
//    }
//
//    public int denyOrder(long id) {
//        return orderDao.updateOrderStatus(id, OrderStatus.DENIED);
//    }

    public int createOrder(Order order) {
        return orderDao.createOrder(order);
    }
}
