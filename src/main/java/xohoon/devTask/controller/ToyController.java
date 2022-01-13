package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.Toy.Toy;
import xohoon.devTask.domain.entity.Toy.ToyDetail;
import xohoon.devTask.service.toy.ToyDetailService;
import xohoon.devTask.service.toy.ToyService;
import xohoon.devTask.service.toy.ToySupportService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "toy")
@RequiredArgsConstructor
public class ToyController {
    private final ToyService toyService;
    private final ToyDetailService toyDetailService;
    private final ToySupportService toySupportService;

    /*
    * user
    * */
    @GetMapping(value = "list") // 리스트
    public String list(Model model) {
        List<Toy> toys = toyService.getToys();
        model.addAttribute("toys", toys);

        return "toy/list";
    }

    @GetMapping(value = "detail/{id}") // 상세보기
    public String toyDetail(@PathVariable(value = "id") String id, Model model, Principal principal) {
        Toy toy = toyService.getToyById(Long.valueOf(id));

        if (principal != null) {
            List<String> supportUser = new ArrayList<>();
            Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            for (int i = 0; i < toy.getToyDetails().size(); i++) { // detail size
                supportUser.add("notSupport");
                for (int j = 0; j < toy.getToyDetails().get(i).getToySupports().size(); j++) { // support size
                    if (member.getUsername().equals(toy.getToyDetails().get(i).getToySupports().get(j).getMember().getUsername())) {
                        supportUser.remove(i);
                        supportUser.add("supported");
                    }
                }
            }
            model.addAttribute("supportUser", supportUser);
        }
        model.addAttribute("toy", toy);

        return "toy/detail";
    }

    @PostMapping(value = "support")
    @ResponseBody
    public Object toySupport(@RequestParam("id") String id) throws Exception{
        JSONObject jsonObject = new JSONObject();
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ToyDetail toyDetail = toyDetailService.getToyDetailById(Long.valueOf(id));
        toySupportService.setToySupport(member, toyDetail);

        return jsonObject;
    }


}
