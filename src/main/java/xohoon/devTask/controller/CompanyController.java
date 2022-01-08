package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xohoon.devTask.domain.dto.CompanyDto;
import xohoon.devTask.domain.dto.task.TaskDetailDto;
import xohoon.devTask.domain.dto.task.TaskDto;
import xohoon.devTask.domain.entity.Company;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.domain.entity.task.TaskDetail;
import xohoon.devTask.service.CompanyService;
import xohoon.devTask.service.task.TaskDetailService;
import xohoon.devTask.service.task.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "co")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final TaskService taskService;
    private final TaskDetailService taskDetailService;

    /*
     * company CRUD
     * */
    @GetMapping(value = "main") // 기업 메인페이지 이동
    public String main(Model model) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyService.getCompany(member.getId());
        // 리스트 전달
//        List<CompanyDto> comList = modelMapper.map(companyList, new TypeToken<List<CompanyDto>>() {}.getType());
        model.addAttribute("company", company);

        return "company/main";
    }

    @GetMapping(value = "register") // 기업 글쓰기 폼 이동
    public String registerForm(Model model) {
        CompanyDto company = new CompanyDto();
        company.setCompany("newRegisterKey");
        model.addAttribute("company", company);

        return "company/register";
    }

    @PostMapping(value = "register") // 기업 저장 및 업데이트
    public String register(CompanyDto companyDto) throws Exception {
        ModelMapper mapper = new ModelMapper();
        Company company = mapper.map(companyDto, Company.class);
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        companyService.register(company, member);

        return "redirect:/co/main";
    }

    @GetMapping(value = "detail/{id}") // 기업 상세 보기
    public String detail(@PathVariable(value = "id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Company companyDetail = companyService.getCompanyDetail(Long.valueOf(id));
        CompanyDto company = modelMapper.map(companyDetail, CompanyDto.class);
        model.addAttribute("company", company);

        return "company/detail";
    }

    @GetMapping(value = "modify/{id}") // 기업 수정 페이지
    public String modifyForm(@PathVariable(value="id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Company companyDetail = companyService.getCompanyDetail(Long.valueOf(id));
        CompanyDto company = modelMapper.map(companyDetail, CompanyDto.class);
        model.addAttribute("company", company);

        return "company/register";
    }

    @PostMapping(value = "delete/{id}") // 기업
    public String delete(@PathVariable(value = "id") String id) {
        companyService.deleteCompany(Long.valueOf(id));

        return "redirect:/co/main";
    }


    /*
    * task CRUD
    * */
    @GetMapping(value = "task/list") // 과제 목록
    public String taskList(Model model) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyService.getCompany(member.getId());
        List<Task> tasks = company.getTasks();

        model.addAttribute("tasks", tasks);

        return "company/task/list";
    }

    @GetMapping(value = "task/register") // 과제 글쓰기 폼
    public String taskRegisterForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);

        return "company/task/register";
    }

    @PostMapping(value = "get/task/register") // 과제 저장
    @ResponseBody
    public Object taskRegister(@RequestBody Map<String, Object> params, TaskDto taskDto) throws Exception{
        JSONObject jsonObject = new JSONObject();
        ModelMapper mapper = new ModelMapper();
        Task task = new Task();
        // task set
        task.setTask_title((String) params.get("task_title"));
        task.setTask_dead_day((String) params.get("task_dead_day"));
        task.setTasking_status(1);
        // task save
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyService.getCompany(member.getId());
        taskService.saveTask(task, company);
        // task data remove
        params.remove("task_title");
        params.remove("task_dead_day");
        // taskDetail save
        for( String key : params.keySet() ){
            TaskDetailDto detailDto = mapper.map(params.get(key), TaskDetailDto.class);
            TaskDetail taskDetail = mapper.map(detailDto, TaskDetail.class);
            taskDetailService.saveTaskDetail(taskDetail, task);
        }

        return jsonObject;
    }

    @GetMapping(value = "task/detail/{id}") // 과제 상세 보기
    public String taskDetail(@PathVariable(value = "id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Task task = taskService.getTaskDetail(Long.valueOf(id));
        model.addAttribute("task", task);

        return "company/task/detail";
    }

    @GetMapping(value = "task/modify/{id}") // 과제 수정 폼
    public String taskModifyForm(@PathVariable(value="id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Task task = taskService.getTaskDetail(Long.valueOf(id));
        model.addAttribute("task", task);

        return "/company/task/register";
    }

    @PostMapping(value = "task/delete/{id}") // 과제 삭제
    public String taskDelete(@PathVariable(value = "id") String id) {

        return "redirect:/co/task/list";
    }
}
