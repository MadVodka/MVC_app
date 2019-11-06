package ivan.vatlin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/registration")
@Controller
public class RegistrationController {
    @GetMapping
    public ModelAndView showRegistrationPage() {
        return new ModelAndView("registration");
    }
}
