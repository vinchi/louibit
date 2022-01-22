package kr.nexparan.louibit.controller.account;

import kr.nexparan.louibit.model.User;
import kr.nexparan.louibit.repository.UserRepository;
import kr.nexparan.louibit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/forgotpass")
    public String forgotpass() {
        return "account/forgotpass";
    }
}
