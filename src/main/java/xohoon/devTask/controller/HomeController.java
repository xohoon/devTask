package xohoon.devTask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/home", "/index"})
    public String home() {
        return "home";
    }

    @GetMapping(value="mypage")
    public String mypage() {
        return "mypage";
    }
    @GetMapping(value="manager")
    public String manage() {
        return "manager";
    }
}
