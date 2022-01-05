package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xohoon.devTask.domain.dto.CompanyDto;
import xohoon.devTask.domain.dto.task.TaskDto;
import xohoon.devTask.domain.entity.Company;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.service.CompanyService;
import xohoon.devTask.service.task.TaskService;

import java.util.List;

@Controller
@RequestMapping(value = "co")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final TaskService taskService;

    /*
     * company CRUD
     * */
    @GetMapping(value = "main") // 기업 메인페이지 이동
    public String main(Model model) {
        ModelMapper modelMapper = new ModelMapper();
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
        ModelMapper modelMapper = new ModelMapper();
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task> taskList = taskService.getTaskList(member.getId());
        List<TaskDto> tasks = modelMapper.map(taskList, new TypeToken<List<TaskDto>>() {}.getType());
        model.addAttribute("tasks", tasks);

        return "company/task/list";
    }

    @GetMapping(value = "task/register") // 과제 글쓰기 폼
    public String taskRegisterForm(Model model) {
        TaskDto task = new TaskDto();
        model.addAttribute("task", task);

        return "company/task/register";
    }

    @PostMapping(value = "task/register") // 과제 저장
    public String taskRegister(TaskDto taskDto) {
        ModelMapper mapper = new ModelMapper();
        taskDto.setTasking_status(1);
        Task task = mapper.map(taskDto, Task.class);
        Company company = (Company) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskService.registerTask(task, company);

        return "redirect:/co/task/list";
    }

    @GetMapping(value = "task/detail/{id}") // 과제 상세 보기
    public String taskDetail(@PathVariable(value = "id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Task taskDetail = taskService.getTask(Long.valueOf(id));
        TaskDto task = modelMapper.map(taskDetail, TaskDto.class);
        model.addAttribute("task", task);

        return "company/task/detail";
    }

    @GetMapping(value = "task/modify/{id}") // 과제 수정 폼
    public String taskModifyForm(@PathVariable(value="id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Task taskDetail = taskService.getTask(Long.valueOf(id));
        TaskDto task = modelMapper.map(taskDetail, TaskDto.class);
        model.addAttribute("task", task);

        return "/company/task/register";
    }

    @PostMapping(value = "task/delete/{id}") // 과제 삭제
    public String taskDelete(@PathVariable(value = "id") String id) {
        taskService.deleteTask(Long.valueOf(id));

        return "redirect:/co/task/list";
    }
}
