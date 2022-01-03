package xohoon.devTask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xohoon.devTask.domain.dto.TaskDto;

@Controller
@RequestMapping(value = "co")
public class CompanyController {

    /*
     * company
     * */
    @GetMapping(value = "main")
    public String main() {
        return "company/main";
    }
    @GetMapping(value = "register") // 기업 글쓰기 폼
    public String registerForm() {
        return "company/register";
    }

    @PostMapping(value = "register") // 기업 글 저장
    public String register(TaskDto taskDto) throws Exception {

        return "redirect:/company/detail";
    }

    @GetMapping(value = "detail")
    public String detail() {
        return "company/detail";
    }

    @GetMapping(value = "modify") // 수정 페이지
    public String modifyForm() {

        return "company/register";
    }

    @PostMapping(value = "modify") // 수정하기
    public String modify() {
        return "redirect:/co/detail";
    }

    @PostMapping(value = "co/delete") // 삭제
    public String delete() {
        return "redirect:/co/main";
    }


    /*
    * task
    * */
    @GetMapping(value = "task/list")
    public String taskList() {
        return "comapny/task/list";
    }

    @GetMapping(value = "task/register") // 과제 글쓰기 폼
    public String taskRegisterForm() {
        return "company/task/register";
    }

    @PostMapping(value = "task/register") // 과제 저장
    public String taskRegister() {
        return "redirect:/co/task/detail";
    }

    @GetMapping(value = "task/detail")
    public String taskDetail() {
        return "company/task/detail";
    }

    @GetMapping(value = "task/modify")
    public String taskModifyForm() {
        return "company/task/register";
    }

    @PostMapping(value = "task/modify")
    public String taskModify() {
        return "redirect:/co/task/detail";
    }

    @PostMapping(value = "task/delete")
    public String taskDelete() {
        return "redirect:/co/task/list";
    }
}
