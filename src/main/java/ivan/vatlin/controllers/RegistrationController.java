package ivan.vatlin.controllers;

import ivan.vatlin.dto.User;
import ivan.vatlin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping
    public String registerUser(@ModelAttribute User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        long result = userService.registerUser(user);
        if (result >= 0) {
            return "redirect:login";
        } else {
            return "redirect:error";
        }
    }

    @GetMapping(value = "/check_username", produces = "application/json")
    public @ResponseBody
    Map<String, Boolean> checkUserExist(@RequestParam("username") String userName) {
        return Collections.singletonMap("result", userService.userNameExist(userName));
    }
}
