package xohoon.devTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xohoon.devTask.domain.dto.TaskDto;
import xohoon.devTask.repository.TaskRepository;

@Controller
@RequestMapping(value = "task")
public class TaskController {

    /*
    * user
    * */
    @GetMapping(value = "list") // 리스트
    public String list() {
        return "task/list";
    }

    @GetMapping(value = "detail") // 상세보기
    public String detailView() {
        return "task/detail";
    }

}
