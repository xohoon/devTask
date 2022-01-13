package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "toy")
@RequiredArgsConstructor
public class ToyController {

    /*
    * user
    * */
    @GetMapping(value = "list") // 리스트
    public String list() {
        return "toy/list";
    }

    @GetMapping(value = "detail") // 상세보기
    public String detailView() {
        return "toy/detail";
    }

}
