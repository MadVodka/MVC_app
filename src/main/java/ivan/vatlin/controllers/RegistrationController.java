package ivan.vatlin.controllers;

import ivan.vatlin.dto.User;
import ivan.vatlin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(value = "/check_username", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Boolean> checkRegistrationData(@RequestParam("username") String userName) {
//        return userService.userNameExist(userName);
        return Collections.singletonMap("result", userService.userNameExist(userName));
    }
}
