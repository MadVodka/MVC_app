package ivan.vatlin.services;

import ivan.vatlin.dao.OrderDao;
import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBaseService implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<OrderInfo> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public OrderInfo getOrderById(long id) {
        try {
            return orderDao.getOrderById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<OrderInfo> getOrdersByUserName(String userName) {
        try {
            return orderDao.getOrdersByUserName(userName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public long createOrder(Order order) {
        return orderDao.createOrder(order);
    }
}
