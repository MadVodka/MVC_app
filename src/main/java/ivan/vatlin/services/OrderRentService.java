package ivan.vatlin.services;

import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.dao.jpa.OrderJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(value = "database.api", havingValue = "jpa")
public class OrderRentService implements OrderService {
    @Autowired
    private OrderJpaDao orderJpaDao;

    @Override
    public List<OrderInfo> getAllOrders() {
        return orderJpaDao.findAll();
    }

    @Override
    public OrderInfo getOrderById(long id) {
        return orderJpaDao.findById(id).orElse(null);
    }

    @Override
    public List<OrderInfo> getOrdersByUserName(String userName) {
        return orderJpaDao.findByUser_UserName(userName);
    }

    @Override
    public long createOrder(Order order) {
        Order saveOrder = orderJpaDao.save(order);
        if (saveOrder == null) {
            return -1;
        }
        return 1;
    }

    @Override
    public OrderInfo getUsersOrder(String userName, long orderId) {
        return orderJpaDao.findByIdAndUser_UserName(orderId, userName);
    }
}
