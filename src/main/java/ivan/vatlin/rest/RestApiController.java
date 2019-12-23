package ivan.vatlin.rest;

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

@RestController
@RequestMapping("rest")
public class RestApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/car/{id}")
    public Car showCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @GetMapping({"/cars", "/cars/{pageNumber}"})
    public Map<String, Object> showCarsByPage(@PathVariable(required = false) Integer pageNumber) {
        PageInfo<Car> carPageInfo= carService.getCarPageInfo(pageNumber);

        Map<String, Object> carsInfo = new LinkedHashMap<>();
        carsInfo.put("currentPage", pageNumber);
        carsInfo.put("numberOfPages", carPageInfo.getNumberOfPages());
        carsInfo.put("cars", carPageInfo.getContent());

        return carsInfo;
    }

    @GetMapping({"/users", "/users/{pageNumber}"})
    public Map<String, Object> showUsersByPage(@PathVariable Integer pageNumber) {
        PageInfo<User> userPageInfo=userService.getUsersByPage(pageNumber);

        Map<String, Object> usersInfo = new LinkedHashMap<>();
        usersInfo.put("currentPage", pageNumber);
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
