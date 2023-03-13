package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDetailsServiceImpl extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username);
    User findUserById(Long userId);
    List<User> allUsers();

    boolean saveUser(User user);

    boolean deleteUser(Long userId);

    void update(User user);

    List<User> usergetList();

}
