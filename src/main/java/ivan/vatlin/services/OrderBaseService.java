package ivan.vatlin.services;

import ivan.vatlin.dao.IOrderDao;
import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@ConditionalOnProperty(value = "database.api", havingValue = "jdbc")
public class OrderBaseService implements OrderService {
    @Autowired
    private IOrderDao orderDao;

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
            return Collections.emptyList();
        }
    }

    @Override
    public long createOrder(Order order) {
        return orderDao.createOrder(order);
    }

    @Override
    public OrderInfo getUsersOrder(String userName, long orderId) {
        return orderDao.getUsersOrder(userName, orderId);
    }
}
