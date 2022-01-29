package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xohoon.devTask.domain.entity.Dev;
import xohoon.devTask.domain.entity.Toy.Toy;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.service.DevService;
import xohoon.devTask.service.task.TaskService;
import xohoon.devTask.service.toy.ToyService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final TaskService taskService;
    private final ToyService toyService;
    private final DevService devService;

    @GetMapping(value = {"/", "/home", "/index"})
    public String home(Model model) {
        List<Task> tasks = taskService.getTasks();
        List<Toy> toys = toyService.getToys();

        model.addAttribute("tasks", tasks);
        model.addAttribute("toys", toys);

        return "home";
    }

    @GetMapping(value = "devDetail/{id}")
    public String devDetailForm(@PathVariable(value = "id") String id, Model model) {
        Dev dev = devService.getDevByMemberId(Long.valueOf(id));
        model.addAttribute("dev", dev);

        return "devDetail";
    }
}
