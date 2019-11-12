package ivan.vatlin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("error")
@Controller
public class ErrorController {
    public ModelAndView showErrorPage() {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", "error");
        return modelAndView;
    }
}
