package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Dao.UserDao;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserDetailsServiceImpl;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminControllers {
    private final UserDetailsServiceImpl userService;

    private final RoleServiceImpl roleService;

    private final UserDao userDao;
    @Autowired
    public AdminControllers(UserDetailsServiceImpl userService, RoleServiceImpl roleService, UserDao userDao) {
        this.userService = userService;
        this.roleService = roleService;
        this.userDao = userDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "listUsers";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userId", userService.findUserById(id));
        return "userById";
    }

    /*@GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("user", new User());
        return "form_for_new_user";
    }*/

  /*  @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/users";
    }*/

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        List<Role> roles = roleService.getRoles();
        model.addAttribute("rolesAdd", roles);
        System.err.println("point one: " + userService.findUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        System.err.println("point three: " + user);
        userService.update(user, id);
        return "redirect:/admin";
    }

    /*@DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }*/

   /* @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "listUsers";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "edit";
    }

    @PatchMapping("admin/")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userId", userService.findUserById(id));
        return "userById";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("user", new User());
        return "form_for_new_user";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    *//*@GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "edit";
    }*//*

    *//*@PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users/{id}";
    }*//*

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }*/

}
