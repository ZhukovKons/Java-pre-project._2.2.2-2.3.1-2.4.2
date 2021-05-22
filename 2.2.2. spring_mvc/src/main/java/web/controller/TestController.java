package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.Role;
import web.model.User;
import web.service.UserService;;

import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/test")
public class TestController{

    @Autowired
    UserService service;

    @GetMapping(value = "/more")
    public String addMoreUser(){
        User user;
        for (int i = 0; i < 20; i++) {
            user = new User("User_name_" + i, "Last_name_" + i, i + "@user", "p" + i, Collections.singleton(new Role(2L, "ROLE_USER")));
            service.add(user);
        }
        System.out.println("Add more user");
        return "redirect:/";
    }

    @GetMapping(value = "/admin")
    public String addAdmin(){
            service.add(new User("Admin", "Admin_Last", "admin", "admin", Collections.singleton(new Role(1L, "ROLE_ADMIN"))));
        System.out.println("Add admin");
            return "redirect:/";
    }

    private static String runString() {
        String string = (new Random()).ints((long)(Math.random() * 10.0D + 8.0D) / 2L, 0, "abcdefghijklmnopqrstuvwxyz".length()).mapToObj("abcdefghijklmnopqrstuvwxyz"::charAt).map(Object::toString).collect(Collectors.joining());
        return Character.toString(string.charAt(0)).toUpperCase() + string.substring(1);
    }
}
