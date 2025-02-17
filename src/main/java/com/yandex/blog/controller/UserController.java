package com.yandex.blog.controller;

import com.yandex.blog.model.User;
import com.yandex.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping // GET запрос /users
    public String users(Model model) {
        List<User> users = service.findAll();
        model.addAttribute("users", users);

        return "users"; // Возвращаем название шаблона — users.html
    }

}