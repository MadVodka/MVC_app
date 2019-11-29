package ivan.vatlin.controllers;

import ivan.vatlin.dto.Car;
import ivan.vatlin.services.CarBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarBaseService carBaseService;

    @GetMapping
    public ModelAndView showCarPage(@RequestParam Long id) {
        Car carById = carBaseService.getCarById(id);
        ModelAndView modelAndView = new ModelAndView("car");
        modelAndView.addObject("car", carById);
        return modelAndView;
    }
}
