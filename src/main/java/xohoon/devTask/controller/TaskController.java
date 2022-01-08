package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.service.task.TaskService;

import java.util.List;

@Controller
@RequestMapping(value = "task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /*
    * task
    * */
    @GetMapping(value = "list") // 리스트
    public String taskList(Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);

        return "task/list";
    }

    @GetMapping(value = "detail/{id}") // 상세보기
    public String taskDetail(@PathVariable(value = "id") String id,  Model model) {
        ModelMapper modelMapper = new ModelMapper();

        return "task/detail";
    }

}
