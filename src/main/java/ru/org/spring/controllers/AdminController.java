package ru.org.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.org.spring.model.User;
import ru.org.spring.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list.html";
    }


    @PostMapping("/user")
    public String create(@ModelAttribute("user") User person,
                         BindingResult bindingResult) {
        userService.save(person);
        return "redirect:/admin";
    }


    @PutMapping("/user")
    public String updateUser(@ModelAttribute("user") User person) {
        userService.update(person.getId(), person);
        return "redirect:/admin";
    }


    @DeleteMapping(value = "/user")
    public String delete(@ModelAttribute("user") User person) {

        userService.deleteById(person.getId());
        return "redirect:/admin";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public User findOne(long id) {
        User person = userService.getById(id);
        return person;
    }
}
