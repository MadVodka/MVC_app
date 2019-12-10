package ivan.vatlin.controllers;

import ivan.vatlin.dto.Car;
import ivan.vatlin.dto.OrderInfo;
import ivan.vatlin.dto.User;
import ivan.vatlin.pagination.PageInfo;
import ivan.vatlin.services.CarService;
import ivan.vatlin.services.OrderService;
import ivan.vatlin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;

    @GetMapping({"/cars", "/cars/{pageNumber}"})
    public Map<String, Object> showCarsByPage(@PathVariable Optional<Integer> pageNumber) {
        int carsPerPage = 3;
        PageInfo<Car> carPageInfo;
        int currentPage = 1;
        if (pageNumber.isPresent()) {
            carPageInfo = carService.getCarPageInfo(pageNumber.get(), carsPerPage);
            currentPage = pageNumber.get();
        } else {
            carPageInfo = carService.getCarPageInfo(1, carsPerPage);
        }

        Map<String, Object> carsInfo = new LinkedHashMap<>();
        carsInfo.put("currentPage", currentPage);
        carsInfo.put("numberOfPages", carPageInfo.getNumberOfPages());
        carsInfo.put("cars", carPageInfo.getContent());

        return carsInfo;
    }

    @GetMapping({"/users", "/users/{pageNumber}"})
    public Map<String, Object> showUsersByPage(@PathVariable Optional<Integer> pageNumber) {
        int usersPerPage = 3;
        PageInfo<User> userPageInfo;
        int currentPage = 1;
        if (pageNumber.isPresent()) {
            userPageInfo = userService.getUsersByPage(pageNumber.get(), usersPerPage);
            currentPage = pageNumber.get();
        } else {
            userPageInfo = userService.getUsersByPage(1, usersPerPage);
        }

        Map<String, Object> usersInfo = new LinkedHashMap<>();
        usersInfo.put("currentPage", currentPage);
        usersInfo.put("numberOfPages", userPageInfo.getNumberOfPages());
        usersInfo.put("users", userPageInfo.getContent());

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
