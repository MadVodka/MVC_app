package ivan.vatlin.services;

import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(value = "database.api", havingValue = "jpa")
public class OrderRentService implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderInfo> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderInfo getOrderById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<OrderInfo> getOrdersByUserName(String userName) {
        return orderRepository.findByUser_UserName(userName);
    }

    @Override
    public long createOrder(Order order) {
        Order saveOrder = orderRepository.save(order);
        if (saveOrder == null) {
            return -1;
        }
        return 1;
    }

    @Override
    public OrderInfo getUsersOrder(String userName, long orderId) {
        return orderRepository.findByIdAndUser_UserName(orderId, userName);
    }
}
