package ivan.vatlin.controllers;

import ivan.vatlin.dto.Car;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.dto.User;
import ivan.vatlin.services.CarBaseService;
import ivan.vatlin.services.OrderBaseService;
import ivan.vatlin.services.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("rest")
public class RestApiController {
    @Autowired
    @Qualifier("userJpa")
    private UserBaseService userService;

    @Autowired
    @Qualifier("carJpa")
    private CarBaseService carService;

    @Autowired
    @Qualifier("orderJpa")
    private OrderBaseService orderService;

    @GetMapping({"/cars", "/cars/{pageNumber}"})
    public Map<String, Object> showCarsByPage(@PathVariable Optional<Integer> pageNumber) {
        int carsPerPage = 3;
        List<Car> carsByPage;
        int currentPage = 1;
        if (pageNumber.isPresent()) {
            carsByPage = carService.getCarsByPage(pageNumber.get(), carsPerPage);
            currentPage = pageNumber.get();
        } else {
            carsByPage = carService.getCarsByPage(1, carsPerPage);
        }

        long numberOfCars = carService.getNumberOfCars();
        int numberOfPages = (int) Math.ceil(numberOfCars * 1.0 / carsPerPage);

        Map<String, Object> carsInfo = new LinkedHashMap<>();
        carsInfo.put("currentPage", currentPage);
        carsInfo.put("numberOfPages", numberOfPages);
        carsInfo.put("cars", carsByPage);

        return carsInfo;
    }

    @GetMapping({"/users", "/users/{pageNumber}"})
    public Map<String, Object> showUsersByPage(@PathVariable Optional<Integer> pageNumber) {
        int usersPerPage = 3;
        List<User> usersByPage;
        int currentPage = 1;
        if (pageNumber.isPresent()) {
            usersByPage = userService.getUsersByPage(pageNumber.get(), usersPerPage);
            currentPage = pageNumber.get();
        } else {
            usersByPage = userService.getUsersByPage(1, usersPerPage);
        }

        long numberOfUsers = userService.getNumberOfUsers();
        int numberOfPages = (int) Math.ceil(numberOfUsers * 1.0 / usersPerPage);

        Map<String, Object> usersInfo = new LinkedHashMap<>();
        usersInfo.put("currentPage", currentPage);
        usersInfo.put("numberOfPages", numberOfPages);
        usersInfo.put("users", usersByPage);

        return usersInfo;
    }

    @GetMapping("/orders")
    public Map<String, Object> showOrders() {
        List<OrderInfo> orderInfoList = orderService.getAllOrders();

        Map<String, Object> ordersInfo = new LinkedHashMap<>();
        ordersInfo.put("orders", orderInfoList);

        return ordersInfo;
    }
}
