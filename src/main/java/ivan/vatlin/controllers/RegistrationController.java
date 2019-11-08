package ivan.vatlin.controllers;

import ivan.vatlin.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/registration")
@Controller
public class RegistrationController {
    @GetMapping
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("registration");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
