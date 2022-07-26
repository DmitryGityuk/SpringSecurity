package com.example.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/only_for_admins/**").hasRole("ADMIN")
                .antMatchers("/read_profile/**").hasRole("READ_PROFILE")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");
    }

//    /**
//     In Memory
//     */
//    @Bean
//    public UserDetailsService users(){
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$10$QRAhS0L4UdXP7dT/BAp1p.TrFRaEVfJuaFppHX1h7tj3kp5U66Zki")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$WUfsvqQyZIMqqD6iYvDRAOVSzxpga9pFpo92MekAhUOgPnhB4FG.W")
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    /**
     * jdbc Authentication
     */
    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$10$QRAhS0L4UdXP7dT/BAp1p.TrFRaEVfJuaFppHX1h7tj3kp5U66Zki")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$WUfsvqQyZIMqqD6iYvDRAOVSzxpga9pFpo92MekAhUOgPnhB4FG.W")
//                .roles("ADMIN", "USER")
//                .build();
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        if (jdbcUserDetailsManager.userExists(user.getUsername())) {
//            jdbcUserDetailsManager.deleteUser(user.getUsername());
//        }
//        if (jdbcUserDetailsManager.userExists(admin.getUsername())) {
//            jdbcUserDetailsManager.deleteUser(admin.getUsername());
//        }
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(admin);
        return jdbcUserDetailsManager;
    }
}
