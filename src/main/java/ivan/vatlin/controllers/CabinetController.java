package ivan.vatlin.controllers;

import ivan.vatlin.dto.User;
import ivan.vatlin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView showCabinetPage(Authentication authentication, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("cabinet");
        modelAndView.addObject("username", principal.getName());

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean hasAdminRole = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));
        if (hasAdminRole) {
            modelAndView.addObject("userList", userService.getAllUsers());
        }

        return modelAndView;
    }

    @GetMapping({"/users/{pageNumber}", "/users"})
    public ModelAndView showUsersByPage(@PathVariable Optional<Integer> pageNumber) {
        int usersPerPage = 5;
        List<User> usersByPage;
        if (pageNumber.isPresent()) {
            usersByPage = userService.getUsersByPage(pageNumber.get(), usersPerPage);
        } else {
            usersByPage = userService.getUsersByPage(1, usersPerPage);
        }

        ModelAndView modelAndView = new ModelAndView("cabinet");
        modelAndView.addObject("userList", usersByPage);
        return modelAndView;
    }

    @GetMapping("/orders")
    public ModelAndView showOrders() {
        ModelAndView modelAndView = new ModelAndView("cabinet");
        return modelAndView;
    }

    @GetMapping("/cars")
    public ModelAndView showCars() {
        ModelAndView modelAndView = new ModelAndView("cabinet");
        return modelAndView;
    }
}
