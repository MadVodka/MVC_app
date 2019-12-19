package ivan.vatlin.services;

import ivan.vatlin.components.AuthenticationFacade;
import ivan.vatlin.dao.jdbc.IOrderDao;
import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.exceptions.BadAuthoritiesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@ConditionalOnProperty(value = "database.api", havingValue = "jdbc", matchIfMissing = true)
public class OrderBaseService implements OrderService {
    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public List<OrderInfo> getAllOrders() {
        if (authenticationFacade.hasRole("USER")) {
            String name = authenticationFacade.getAuthentication().getName();
            return getOrdersByUserName(name);
        } else if (authenticationFacade.hasRole("ADMIN")) {
            return orderDao.getAllOrders();
        } else {
            String name = authenticationFacade.getAuthentication().getName();
            throw new BadAuthoritiesException("User " + name + "has no access to orders with its roles");
        }
    }

    @Override
    public OrderInfo getOrderById(long id) {
        try {
            if (authenticationFacade.hasRole("ADMIN")) {
                return orderDao.getOrderById(id);
            } else {
                return getUsersOrder(authenticationFacade.getAuthentication().getName(), id);
            }
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
