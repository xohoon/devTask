package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xohoon.devTask.domain.entity.Company;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.domain.entity.task.TaskDetail;
import xohoon.devTask.service.CompanyService;
import xohoon.devTask.service.task.TaskDetailService;
import xohoon.devTask.service.task.TaskService;
import xohoon.devTask.service.task.TaskSupportService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "task")
@RequiredArgsConstructor
public class TaskController {

    private final CompanyService companyService;
    private final TaskService taskService;
    private final TaskDetailService taskDetailService;
    private final TaskSupportService taskSupportService;

    /*
    * task
    * */

    @GetMapping(value = "test")
    public void test(Principal principal) {
        if (principal != null) {
            Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Company company = companyService.getCompany(member.getId());
//            List<Task> task = taskService.test(company.getId());
        }
    }

    @GetMapping(value = "list") // 리스트
    public String taskList(Model model) {
        List<Task> tasks = taskService.getTasks();
//        ModelMapper modelMapper = new ModelMapper();
//        List<TaskDto> tasks = modelMapper.map(taskList, new TypeToken<List<TaskDto>>() {}.getType());
        model.addAttribute("tasks", tasks);

        return "task/list";
    }

    @GetMapping(value = "detail/{id}") // 상세보기
    public String taskDetail(@PathVariable(value = "id") String id, Model model, Principal principal) {
        Task task = taskService.getTask(Long.valueOf(id));

        if (principal != null) {
            List<String> supportUser = new ArrayList<>();
            Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            for (int i = 0; i < task.getTaskDetails().size(); i++) {
//                int count = taskSupportService.searchSupport(Long.valueOf(id), member.getId());
            }
            model.addAttribute("username", member.getUsername());
        }

//        for (int j = 0; j < task.getTaskDetails().get(i).getTaskSupports().size(); j++) {
//                    if (member.getUsername() == task.getTaskDetails().get(i).getTaskSupports().get(j).getMembers().getUsername()) {
//                        supportUser.add("supported");
//                        break;
//                    }else {
//                        supportUser.add("notSupport");
//                    }
//                }

        model.addAttribute("task", task);

        return "task/detail";
    }

    @PostMapping(value = "support")
    @ResponseBody
    public Object taskSupport(@RequestParam("td_id") String td_id) throws Exception{
        JSONObject jsonObject = new JSONObject();
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TaskDetail taskDetail = taskDetailService.getTaskDetail(Long.valueOf(td_id));
        taskSupportService.setTaskSupport(member, taskDetail);

        return jsonObject;
    }

}
