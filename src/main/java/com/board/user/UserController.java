package com.board.user;

import com.board.user.UserDto;
import com.board.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user/register")
    public String register() {
        return "register.html";
    }

    @PostMapping("/user/register")
    public String signup(UserDto userDto,HttpServletRequest request,HttpServletResponse response) throws ServletException {
        userService.saveUser(userDto,request,response);
        request.login(userDto.getUsername(),userDto.getPassword());
        return "redirect:/board/list";
    }

    @GetMapping("/user/login")
    public String signin(UserDto userDto){
        return "login";
    }

    @GetMapping("/user/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

}
