package ivan.vatlin.controllers;

import ivan.vatlin.dto.User;
import ivan.vatlin.services.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserBaseService userBaseService;

    @GetMapping()
    public ModelAndView showUserInfo(@RequestParam Long id) {
        User userById = userBaseService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", userById);

        return modelAndView;
    }
}
