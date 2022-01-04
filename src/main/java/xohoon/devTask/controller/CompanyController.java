package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xohoon.devTask.domain.dto.CompanyDto;
import xohoon.devTask.domain.dto.TaskDto;
import xohoon.devTask.domain.entity.Company;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.Task;
import xohoon.devTask.repository.TaskRepository;
import xohoon.devTask.service.CompanyService;
import xohoon.devTask.service.TaskService;

import java.util.List;

@Controller
@RequestMapping(value = "co")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final TaskService taskService;
    /*
     * company
     * */
    @GetMapping(value = "main")
    public String main(Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyService.getCompany(member.getId());
        // 리스트 전달
//        List<CompanyDto> comList = modelMapper.map(companyList, new TypeToken<List<CompanyDto>>() {}.getType());
        model.addAttribute("company", company);

        return "company/main";
    }

    @GetMapping(value = "register") // 기업 글쓰기 폼
    public String registerForm(Model model) {
        CompanyDto company = new CompanyDto();
        company.setCompany("newRegisterKey");
        model.addAttribute("company", company);

        return "company/register";
    }

    @PostMapping(value = "register") // 저장 및 수정
    public String register(CompanyDto companyDto) throws Exception {
        ModelMapper mapper = new ModelMapper();
        Company company = mapper.map(companyDto, Company.class);
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        companyService.register(company, member);

        return "redirect:/co/main";
    }

    @GetMapping(value = "detail/{id}") // 상세페이지
    public String detail(@PathVariable(value = "id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Company companyDetail = companyService.getCompanyDetail(Long.valueOf(id));
        CompanyDto company = modelMapper.map(companyDetail, CompanyDto.class);
        model.addAttribute("company", company);

        return "company/detail";
    }

    @GetMapping(value = "modify/{id}") // 수정 페이지
    public String modifyForm(@PathVariable(value="id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Company companyDetail = companyService.getCompanyDetail(Long.valueOf(id));
        CompanyDto company = modelMapper.map(companyDetail, CompanyDto.class);
        model.addAttribute("company", company);

        return "company/register";
    }

    @PostMapping(value = "delete/{id}") // 삭제
    public String delete(@PathVariable(value = "id") String id) {
        companyService.deleteCompany(Long.valueOf(id));

        return "redirect:/co/main";
    }


    /*
    * task
    * */
    @GetMapping(value = "task/list")
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
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskService.registerTask(task, member);

        return "redirect:/co/task/list";
    }

    @GetMapping(value = "task/detail/{id}")
    public String taskDetail(@PathVariable(value = "id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Task taskDetail = taskService.getTask(Long.valueOf(id));
        TaskDto task = modelMapper.map(taskDetail, TaskDto.class);
        model.addAttribute("task", task);

        return "company/task/detail";
    }

    @GetMapping(value = "task/modify/{id}")
    public String taskModifyForm(@PathVariable(value="id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Task taskDetail = taskService.getTask(Long.valueOf(id));
        TaskDto task = modelMapper.map(taskDetail, TaskDto.class);
        model.addAttribute("task", task);

        return "/company/task/register";
    }

    @PostMapping(value = "task/delete/{id}")
    public String taskDelete(@PathVariable(value = "id") String id) {
        taskService.deleteTask(Long.valueOf(id));

        return "redirect:/co/task/list";
    }
}
