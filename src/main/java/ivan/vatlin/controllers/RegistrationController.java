package ivan.vatlin.controllers;

import ivan.vatlin.dto.User;
import ivan.vatlin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;

@RequestMapping("/registration")
@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("registration");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping(value = "/check_username", produces = "application/json")
    public @ResponseBody
    Map<String, Boolean> checkUserExist(@RequestParam("username") String userName) {
        return Collections.singletonMap("result", userService.userNameExist(userName));
    }
}
