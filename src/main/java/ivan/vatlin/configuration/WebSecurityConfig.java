package ivan.vatlin.configuration;

import ivan.vatlin.services.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AppUserDetailService appUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login**", "/registration**").anonymous()
                .antMatchers("/rest**").permitAll()
                .antMatchers("/cabinet", "/cabinet/orders**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/cabinet/**").hasRole("ADMIN")
                .antMatchers("/add_order**").hasRole("USER")
                .antMatchers("/car**").hasRole("ADMIN")
                .antMatchers("/order**").hasRole("ADMIN")
                .antMatchers("/user**").hasRole("ADMIN")

                .and()

                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/cabinet")
                .loginProcessingUrl("/perform_login")
                .failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()

                .and()

                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
