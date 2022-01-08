package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xohoon.devTask.domain.dto.CompanyDto;
import xohoon.devTask.domain.dto.task.TaskDto;
import xohoon.devTask.domain.entity.Company;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.domain.entity.task.TaskDetail;
import xohoon.devTask.service.task.TaskService;

import java.util.List;
import java.util.Optional;

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
//        ModelMapper modelMapper = new ModelMapper();
//        List<TaskDto> tasks = modelMapper.map(taskList, new TypeToken<List<TaskDto>>() {}.getType());
        model.addAttribute("tasks", tasks);

        return "task/list";
    }

    @GetMapping(value = "detail/{id}") // 상세보기
    public String taskDetail(@PathVariable(value = "id") String id,  Model model) {
        Task task = taskService.getTaskDetail(Long.valueOf(id));
//        ModelMapper modelMapper = new ModelMapper();
//        TaskDto task = modelMapper.map(taskData, TaskDto.class);

        model.addAttribute("task", task);

        return "task/detail";
    }

}
