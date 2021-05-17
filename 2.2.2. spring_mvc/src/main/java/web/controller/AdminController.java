package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model, @ModelAttribute("userNew") User userNew) {
        model.addAttribute("userList", userService.getAll());
        return "/admin";
    }

//    @GetMapping("/")
//    public String dontLoginUser(Model model, Principal principal) {
//        model.addAttribute("userName", principal.getName());
//        return "admin";
//    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/editor";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "/editor";
        }
        userService.edit(user, id);
        return "redirect:/admin";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("userNew") @Valid User userNew,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        userService.add(userNew);
        return "redirect:/admin";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.remove(id);

        return "redirect:/admin";
    }


}