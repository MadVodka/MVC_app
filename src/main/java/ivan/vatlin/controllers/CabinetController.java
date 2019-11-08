package ivan.vatlin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {
    @GetMapping
    public ModelAndView showCabinetPage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("cabinet");
        modelAndView.addObject("username", principal.getName());
        return modelAndView;
    }
}
