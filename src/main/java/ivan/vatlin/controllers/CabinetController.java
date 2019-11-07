package ivan.vatlin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {
    @GetMapping
    public ModelAndView showCabinetPage() {
        return new ModelAndView("cabinet");
    }
}
