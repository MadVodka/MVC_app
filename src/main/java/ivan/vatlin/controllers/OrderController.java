package ivan.vatlin.controllers;

import ivan.vatlin.components.AuthenticationFacade;
import ivan.vatlin.dto.Car;
import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.dto.User;
import ivan.vatlin.services.CarService;
import ivan.vatlin.services.OrderService;
import ivan.vatlin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    @Qualifier("orderJpa")
    private OrderService orderService;

    @Autowired
    @Qualifier("carJpa")
    private CarService carService;

    @Autowired
    @Qualifier("userJpa")
    private UserService userService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping("/order")
    public ModelAndView showOrderInfo(@RequestParam Long id) {
        OrderInfo orderById;
        if (authenticationFacade.hasRole("ADMIN")) {
            orderById = orderService.getOrderById(id);
        } else {
            orderById = orderService.getUsersOrder(authenticationFacade.getAuthentication().getName(), id);
        }

        if (orderById == null) {
            return new ModelAndView("error");
        }

        ModelAndView modelAndView = new ModelAndView("order");
        modelAndView.addObject("order", orderById);
        return modelAndView;
    }

    @GetMapping("/add_order")
    public ModelAndView showAddOrderPage() {
        List<Car> allCars = carService.getAllCars();
        ModelAndView modelAndView = new ModelAndView("add_order");
        modelAndView.addObject("cars", allCars);
        modelAndView.addObject("order", new Order());
        return modelAndView;
    }

    @PostMapping("/add_order/process")
    public String processNewOrder(@ModelAttribute Order order) {
        String userName = authenticationFacade.getAuthentication().getName();
        User userByUserName = userService.getUserByUserName(userName);

        order.setUserId(userByUserName.getId());
        orderService.createOrder(order);

        return "redirect:/cabinet";
    }

}
