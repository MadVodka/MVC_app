package ivan.vatlin.controllers;

import ivan.vatlin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;

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
                .anyMatch(role -> role.equals("ADMIN"));
        if (hasAdminRole) {
            modelAndView.addObject("userList", userService.getAllUsers());
        }

        return modelAndView;
    }
}
