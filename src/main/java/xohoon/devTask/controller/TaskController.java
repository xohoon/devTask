package xohoon.devTask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController(value = "task")
public class TaskController {

    @GetMapping(value = "list") // 리스트
    public String list() {
        return "task/list";
    }

    @GetMapping(value = "register") // 글쓰기 폼
    public String createForm() {
        return "task/register";
    }

    @PostMapping(value = "register") // 글 저장
    public String create() {
        return "redirect:/task/detail";
    }

    @GetMapping(value = "detail") // 상세보기
    public String detailView() {
        return "task/detail";
    }

    @GetMapping(value = "modify") // 수정 페이지
    public String modifyForm() {
        return "task/register";
    }

    @PostMapping(value = "modify") // 수정하기
    public String modify() {
        return "redirect:/task/detail";
    }

    @PostMapping(value = "delete") // 삭제
    public String delete() {
        return "redirect:/task/list";
    }
}
