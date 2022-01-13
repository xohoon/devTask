package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xohoon.devTask.domain.entity.Toy.Toy;
import xohoon.devTask.service.toy.ToyService;

import java.util.List;

@Controller
@RequestMapping(value = "toy")
@RequiredArgsConstructor
public class ToyController {
    private final ToyService toyService;

    /*
    * user
    * */
    @GetMapping(value = "list") // 리스트
    public String list(Model model) {
        List<Toy> toys = toyService.getToys();
        model.addAttribute("toys", toys);

        return "toy/list";
    }

    @GetMapping(value = "detail") // 상세보기
    public String detailView() {
        return "toy/detail";
    }

}
