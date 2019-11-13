package ivan.vatlin.controllers;

import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ModelAndView showOrderInfo(@RequestParam Long id) {
        OrderInfo orderById = orderService.getOrderById(id);
        ModelAndView modelAndView = new ModelAndView("order");
        modelAndView.addObject("order", orderById);
        return modelAndView;
    }

}
