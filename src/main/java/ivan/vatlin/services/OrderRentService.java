package ivan.vatlin.services;

import ivan.vatlin.components.AuthenticationFacade;
import ivan.vatlin.dao.jpa.OrderJpaDao;
import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.exceptions.BadAuthoritiesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(value = "database.api", havingValue = "jpa")
public class OrderRentService implements OrderService {
    @Autowired
    private OrderJpaDao orderJpaDao;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public List<OrderInfo> getAllOrders() {
        if (authenticationFacade.hasRole("USER")) {
            String name = authenticationFacade.getAuthentication().getName();
            return getOrdersByUserName(name);
        } else if (authenticationFacade.hasRole("ADMIN")) {
            return orderJpaDao.findAll();
        } else {
            String name = authenticationFacade.getAuthentication().getName();
            throw new BadAuthoritiesException("User " + name + "has no access to orders with its roles");
        }
    }

    @Override
    public OrderInfo getOrderById(long id) {
        if (authenticationFacade.hasRole("ADMIN")) {
            return orderJpaDao.findById(id).orElse(null);
        } else {
            return getUsersOrder(authenticationFacade.getAuthentication().getName(), id);
        }
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
