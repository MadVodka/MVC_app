package ivan.vatlin.services;

import ivan.vatlin.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    private UserBaseService userBaseService;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userBaseService.getUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with name " + userName);
        }
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
                .password(user.getPassword())
                .roles(user.getUserRole().toString());

        return userBuilder.build();
    }
}
