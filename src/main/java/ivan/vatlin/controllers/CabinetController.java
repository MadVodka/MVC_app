package ivan.vatlin.controllers;

import ivan.vatlin.components.AuthenticationFacade;
import ivan.vatlin.dto.Car;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.dto.User;
import ivan.vatlin.services.CarService;
import ivan.vatlin.services.OrderService;
import ivan.vatlin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

//    @GetMapping
//    public ModelAndView showCabinetPage(Authentication authentication, Principal principal) {
//        ModelAndView modelAndView = new ModelAndView("cabinet");
//        modelAndView.addObject("username", principal.getName());
//
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        boolean hasAdminRole = authorities.stream()
//                .map(GrantedAuthority::getAuthority)
//                .anyMatch(role -> role.equals("ROLE_ADMIN"));
//        if (hasAdminRole) {
//            modelAndView.addObject("userList", userService.getAllUsers());
//        }
//
//        return modelAndView;
//    }

    @GetMapping
    public String showCabinetPage() {
        return "redirect:cabinet/orders";
    }

    @GetMapping({"/users/{pageNumber}", "/users"})
    public ModelAndView showUsersByPage(@PathVariable Optional<Integer> pageNumber) {
        int usersPerPage = 3;
        int currentPage;
        List<User> usersByPage;
        if (pageNumber.isPresent()) {
            usersByPage = userService.getUsersByPage(pageNumber.get(), usersPerPage);
            currentPage = pageNumber.get();
        } else {
            usersByPage = userService.getUsersByPage(1, usersPerPage);
            currentPage = 1;
        }

        int numberOfUsers = userService.getNumberOfUsers();
        int numberOfPages = (int) Math.ceil(numberOfUsers * 1.0 / usersPerPage);

        ModelAndView modelAndView = new ModelAndView("all_users");
        modelAndView.addObject("userList", usersByPage);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("sectionUrl", "/mvc/cabinet/users/");
        modelAndView.addObject("numberOfPages", numberOfPages);
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
        if (hasRole("USER")) {
            String name = authenticationFacade.getAuthentication().getName();
            orderInfoList = orderService.getOrdersByUserName(name);
        } else if (hasRole("ADMIN")) {
            orderInfoList = orderService.getAllOrders();
        }

        ModelAndView modelAndView = new ModelAndView("all_orders");
        modelAndView.addObject("orderList", orderInfoList);
        return modelAndView;
    }

    @GetMapping({"/cars", "/cars/{pageNumber}"})
    public ModelAndView showCarsByPage(@PathVariable Optional<Integer> pageNumber) {
        int carsPerPage = 3;
        List<Car> carsByPage;
        int currentPage;
        if (pageNumber.isPresent()) {
            carsByPage = carService.getCarsByPage(pageNumber.get(), carsPerPage);
            currentPage = pageNumber.get();
        } else {
            carsByPage = carService.getCarsByPage(1, carsPerPage);
            currentPage = 1;
        }

        int numberOfCars = carService.getNumberOfCars();
        int numberOfPages = (int) Math.ceil(numberOfCars * 1.0 / carsPerPage);

        ModelAndView modelAndView = new ModelAndView("all_cars");
        modelAndView.addObject("carList", carsByPage);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("sectionUrl", "/mvc/cabinet/cars/");
        modelAndView.addObject("numberOfPages", numberOfPages);
        return modelAndView;
    }

    @GetMapping("/cars/search")
    public ModelAndView showCarsBySearch(@RequestParam String text, @RequestParam(defaultValue = "brand") String searchBy) {
        List<Car> carsBySearch = carService.getCarsBySearch(text, searchBy);
        ModelAndView modelAndView = new ModelAndView("all_cars");
        modelAndView.addObject("carList", carsBySearch);
        return modelAndView;
    }

    private boolean hasRole(String userRole) {
        Collection<? extends GrantedAuthority> authorities = authenticationFacade.getAuthentication().getAuthorities();
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_" + userRole));
    }
}
