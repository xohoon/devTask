package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xohoon.devTask.domain.entity.Dev;
import xohoon.devTask.service.DevService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final DevService devService;

    @GetMapping(value = {"/", "/home", "/index"})
    public String home() {
        return "home";
    }

    @GetMapping(value = "devDetail/{id}")
    public String devDetailForm(@PathVariable(value = "id") String id, Model model) {
        Dev dev = devService.getDevByMemberId(Long.valueOf(id));
        model.addAttribute("dev", dev);

        return "devDetail";
    }
}
