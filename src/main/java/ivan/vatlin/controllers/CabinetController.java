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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {
    @Autowired
    @Qualifier("userJpa")
    private UserService userService;

    @Autowired
    @Qualifier("carJpa")
    private CarService carService;

    @Autowired
    @Qualifier("orderJpa")
    private OrderService orderService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping
    public String showCabinetPage() {
        return "redirect:cabinet/orders";
    }

    @GetMapping({"/users/{pageNumber}", "/users"})
    public ModelAndView showUsersByPage(@PathVariable Optional<Integer> pageNumber, HttpServletRequest request) {
        int usersPerPage = 3;
        int currentPage;
        PageInfo<User> userPageInfo;
        if (pageNumber.isPresent()) {
            userPageInfo = userService.getUsersByPage(pageNumber.get(), usersPerPage);
            currentPage = pageNumber.get();
        } else {
            userPageInfo = userService.getUsersByPage(1, usersPerPage);
            currentPage = 1;
        }

        ModelAndView modelAndView = new ModelAndView("all_users");
        modelAndView.addObject("userList", userPageInfo.getContent());
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("sectionUrl", request.getContextPath() + "/cabinet/users/");
        modelAndView.addObject("numberOfPages", userPageInfo.getNumberOfPages());
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
        List<OrderInfo> orderInfoList = null;
        if (authenticationFacade.hasRole("USER")) {
            String name = authenticationFacade.getAuthentication().getName();
            orderInfoList = orderService.getOrdersByUserName(name);
        } else if (authenticationFacade.hasRole("ADMIN")) {
            orderInfoList = orderService.getAllOrders();
        }

        ModelAndView modelAndView = new ModelAndView("all_orders");
        modelAndView.addObject("orderList", orderInfoList);
        return modelAndView;
    }

    @GetMapping({"/cars", "/cars/{pageNumber}"})
    public ModelAndView showCarsByPage(@PathVariable Optional<Integer> pageNumber, HttpServletRequest request) {
        int carsPerPage = 3;
        PageInfo<Car> carPageInfo;
        int currentPage;
        if (pageNumber.isPresent()) {
            carPageInfo = carService.getCarPageInfo(pageNumber.get(), carsPerPage);
            currentPage = pageNumber.get();
        } else {
            carPageInfo = carService.getCarPageInfo(1, carsPerPage);
            currentPage = 1;
        }

        ModelAndView modelAndView = new ModelAndView("all_cars");
        modelAndView.addObject("carList", carPageInfo.getContent());
        modelAndView.addObject("currentPage", currentPage);
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
