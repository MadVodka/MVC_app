package ivan.vatlin.controllers;

import ivan.vatlin.components.AuthenticationFacade;
import ivan.vatlin.dto.Car;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.dto.User;
import ivan.vatlin.pagination.PageInfo;
import ivan.vatlin.services.CarService;
import ivan.vatlin.services.OrderService;
import ivan.vatlin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {
    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping
    public String showCabinetPage() {
        return "redirect:cabinet/orders";
    }


    @GetMapping({"/users/{pageNumber}", "/users"})
    public ModelAndView showUsersByPage(@PathVariable(required = false) Integer pageNumber, HttpServletRequest request) {
        PageInfo<User> userPageInfo = userService.getUsersByPage(pageNumber);

        ModelAndView modelAndView = new ModelAndView("all_users");
        modelAndView.addObject("userList", userPageInfo.getContent())
                .addObject("currentPage", pageNumber)
                .addObject("sectionUrl", request.getContextPath() + "/cabinet/users/")
                .addObject("numberOfPages", userPageInfo.getNumberOfPages());
        return modelAndView;
    }

    @GetMapping("/users/search")
    public ModelAndView showUsersBySearch(@RequestParam String text, @RequestParam(defaultValue = "first_name") String searchBy) {
        List<User> usersBySearch = userService.getUsersBySearch(text, searchBy);
        ModelAndView modelAndView = new ModelAndView("all_users");
        modelAndView.addObject("userList", usersBySearch);
        return modelAndView;
    }

    @GetMapping("/orders")
    public ModelAndView showOrders() {
        List<OrderInfo> orderInfoList = orderService.getAllOrders();

        ModelAndView modelAndView = new ModelAndView("all_orders");
        modelAndView.addObject("orderList", orderInfoList);
        return modelAndView;
    }

    @GetMapping({"/cars", "/cars/{pageNumber}"})
    public ModelAndView showCarsByPage(@PathVariable(required = false) Integer pageNumber, HttpServletRequest request) {
        PageInfo<Car> carPageInfo = carService.getCarPageInfo(pageNumber);

        ModelAndView modelAndView = new ModelAndView("all_cars");
        modelAndView.addObject("carList", carPageInfo.getContent());
        modelAndView.addObject("currentPage", pageNumber);
        modelAndView.addObject("sectionUrl", request.getContextPath() + "/cabinet/cars/");
        modelAndView.addObject("numberOfPages", carPageInfo.getNumberOfPages());
        return modelAndView;
    }

    @GetMapping("/cars/search")
    public ModelAndView showCarsBySearch(@RequestParam String text, @RequestParam(defaultValue = "brand") String searchBy) {
        List<Car> carsBySearch = carService.getCarsBySearch(text, searchBy);
        ModelAndView modelAndView = new ModelAndView("all_cars");
        modelAndView.addObject("carList", carsBySearch);
        return modelAndView;
    }
}
