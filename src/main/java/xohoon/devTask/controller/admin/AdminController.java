package xohoon.devTask.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping(value="/hide/admin")
    public String home() throws Exception {
        return "admin/home";
    }
}
