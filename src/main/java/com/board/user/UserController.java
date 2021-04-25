package com.board.user;

import com.board.user.UserDto;
import com.board.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    @PostMapping("/register")
    public String signup(UserDto userDto){
        userService.saveUser(userDto);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String signin(UserDto userDto){
        return "login";
    }

    @GetMapping("/user/login/result")
    public String dispLoginResult() {
        return "/loginok";
    }
}
