package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xohoon.devTask.domain.entity.Dev;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.domain.entity.task.TaskDetail;
import xohoon.devTask.service.DevService;
import xohoon.devTask.service.task.TaskDetailService;
import xohoon.devTask.service.task.TaskService;
import xohoon.devTask.service.task.TaskSupportService;

import javax.persistence.EntityManager;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "task")
@RequiredArgsConstructor
public class TaskController {

    private final DevService devService;
    private final TaskService taskService;
    private final TaskDetailService taskDetailService;
    private final TaskSupportService taskSupportService;

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
    public String taskDetail(@PathVariable(value = "id") String id, Model model, Principal principal) {
        Task task = taskService.getTaskById(Long.valueOf(id));
        // 양방향 매핑을 단방향으로 변경.. 예정 코드
        List<TaskDetail> taskDetails = taskDetailService.getTaskDetailByTaskId((Long.valueOf(id)));
//        model.addAttribute("taskDetails", taskDetails);
        if (principal != null) {
            List<String> supportUser = new ArrayList<>();
            Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            for (int i = 0; i < task.getTaskDetails().size(); i++) { // detail size
                supportUser.add("notSupport");
                for (int j = 0; j < task.getTaskDetails().get(i).getTaskSupports().size(); j++) { // support size
                    if (member.getUsername().equals(task.getTaskDetails().get(i).getTaskSupports().get(j).getMember().getUsername())) {
                        supportUser.remove(i);
                        supportUser.add("supported");
                    }
                }
            }
            model.addAttribute("supportUser", supportUser);
        }

        model.addAttribute("task", task);

        return "task/detail";
    }

    @PostMapping(value = "support")
    @ResponseBody
    public Object taskSupport(@RequestParam("id") String id) throws Exception{
        JSONObject jsonObject = new JSONObject();
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TaskDetail taskDetail = taskDetailService.getTaskDetailById(Long.valueOf(id));
        taskSupportService.setTaskSupport(member, taskDetail);

        return jsonObject;
    }

    @GetMapping(value = "portfolio")
    @ResponseBody
    public Object portfolioCheck() {
        JSONObject jsonObject = new JSONObject();
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dev dev = devService.getDevByMemberId(member.getId());
        if (dev == null) {
            jsonObject.put("result", "fail");
        }else {
            jsonObject.put("result", "success");
        }

        return jsonObject;
    }

}
