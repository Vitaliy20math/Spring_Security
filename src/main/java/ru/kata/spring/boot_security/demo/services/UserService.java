package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.Dao.RoleDao;
import ru.kata.spring.boot_security.demo.Dao.UserDao;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
    private UserDao userDao;

    private RoleDao roleDao;

    @PersistenceContext
    private EntityManager em;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserDao(UserDao userRep) {
        this.userDao = userRep;
    }
    @Autowired
    public void setRoleDao(RoleDao roleRep) {
        this.roleDao = roleRep;
    }
    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' is not found!", username));
        }
        return user;

    }
    public User findUserById(Long userId) {
        Optional<User> userFromDb = userDao.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userDao.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userDao.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userDao.findById(userId).isPresent()) {
            userDao.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergetList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
