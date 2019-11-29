package ivan.vatlin.controllers;

import ivan.vatlin.components.AuthenticationFacade;
import ivan.vatlin.dto.Car;
import ivan.vatlin.dto.Order;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.dto.User;
import ivan.vatlin.services.CarBaseService;
import ivan.vatlin.services.OrderBaseService;
import ivan.vatlin.services.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private OrderBaseService orderBaseService;

    @Autowired
    private CarBaseService carBaseService;

    @Autowired
    private UserBaseService userBaseService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping("/order")
    public ModelAndView showOrderInfo(@RequestParam Long id) {
        OrderInfo orderById = orderBaseService.getOrderById(id);
        ModelAndView modelAndView = new ModelAndView("order");
        modelAndView.addObject("order", orderById);
        return modelAndView;
    }

    @GetMapping("/add_order")
    public ModelAndView showAddOrderPage() {
        List<Car> allCars = carBaseService.getAllCars();
        ModelAndView modelAndView = new ModelAndView("add_order");
        modelAndView.addObject("cars", allCars);
        modelAndView.addObject("order", new Order());
        return modelAndView;
    }

    @PostMapping("/add_order/process")
    public String processNewOrder(@ModelAttribute Order order) {
        String userName = authenticationFacade.getAuthentication().getName();
        User userByUserName = userBaseService.getUserByUserName(userName);

        order.setUserId(userByUserName.getId());
        orderBaseService.createOrder(order);

        return "redirect:/cabinet";
    }

}
