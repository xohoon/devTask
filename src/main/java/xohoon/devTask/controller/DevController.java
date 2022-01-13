package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xohoon.devTask.domain.dto.DevDto;
import xohoon.devTask.domain.dto.task.TaskDetailDto;
import xohoon.devTask.domain.dto.toy.ToyDetailDto;
import xohoon.devTask.domain.entity.Company;
import xohoon.devTask.domain.entity.Dev;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.Toy.Toy;
import xohoon.devTask.domain.entity.Toy.ToyDetail;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.domain.entity.task.TaskDetail;
import xohoon.devTask.domain.entity.task.TaskSupport;
import xohoon.devTask.service.DevService;
import xohoon.devTask.service.task.TaskSupportService;
import xohoon.devTask.service.toy.ToyDetailService;
import xohoon.devTask.service.toy.ToyService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "dev")
@RequiredArgsConstructor
public class DevController {

    private final DevService devService;
    private final TaskSupportService taskSupportService;
    private final ToyService toyService;
    private final ToyDetailService toyDetailService;

    /*
    * dev CRUD
    * */
    @GetMapping(value = "main") // 메인페이지
    public String main(Model model) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dev dev = devService.getDevByMemberId(member.getId());

        model.addAttribute("dev", dev);

        return "dev/main";
    }

    @GetMapping(value = "register") // 등록 폼
    public String registerForm(Model model) {
        DevDto dev = new DevDto();
        model.addAttribute("dev", dev);

        return "dev/register";
    }

    @PostMapping(value = "register") // 등록
    public String register(DevDto devDto) {
        System.out.println("devDto = " + devDto.toString());
        ModelMapper mapper = new ModelMapper();
        Dev dev = mapper.map(devDto, Dev.class);
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        devService.register(dev, member);

        return "redirect:/dev/main";
    }

    @GetMapping(value = "detail/{id}") // 상세 페이지
    public String detail(@PathVariable(value = "id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Dev devDetail = devService.getDevById(Long.valueOf(id));
        DevDto dev = modelMapper.map(devDetail, DevDto.class);
        model.addAttribute("dev", dev);

        return "dev/detail";
    }

    @GetMapping(value = "modify/{id}") // 수정 페이지
    public String modifyForm(@PathVariable(value="id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Dev devDetail = devService.getDevById(Long.valueOf(id));
        DevDto dev = modelMapper.map(devDetail, DevDto.class);
        model.addAttribute("dev", dev);

        return "dev/register";
    }

    @PostMapping(value = "delete/{id}") // 삭제
    public String delete(@PathVariable(value = "id") String id) {
        devService.deleteDev(Long.valueOf(id));

        return "redirect:/dev/main";
    }

    /*
    * task support
    * */
    @GetMapping(value = "task/support") // 지원 현황
    public String taskList(Model model) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TaskSupport> taskSupport = taskSupportService.getSupportByMemberId(member.getId());
        model.addAttribute("taskSupport", taskSupport);

        return "dev/support";
    }

    /*
    * toy CRUD
    * */
    @GetMapping(value = "toy/main")
    public String toyList(Model model) {

        return "dev/toy/main";
    }

    @GetMapping(value = "toy/register")
    public String toyRegisterForm(Model model) {
        Toy toy = new Toy();
        model.addAttribute("toy", toy);

        return "dev/toy/register";
    }

    @PostMapping(value = "toy/register")
    @ResponseBody
    public Object toyRegister(@RequestBody Map<String, Object> params) throws Exception {
        System.out.println("params = " + params.toString());
        JSONObject jsonObject = new JSONObject();
        ModelMapper mapper = new ModelMapper();
        Toy toy = new Toy();
        // toy set
        toy.setToy_title((String) params.get("toy_title"));
        toy.setToy_content((String) params.get("toy_content"));
        toy.setToy_dead_day((String) params.get("toy_dead_day"));
        toy.setToy_status(1);
        System.out.println("params1 = " + params.toString());
        if(params.containsKey("id")) { // update
            toy.setId(Long.parseLong((String) params.get("id")));
        }
        // toy save
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        toyService.saveToy(toy, member);
        // toy data remove
        if(params.containsKey("id")) { // toy update
            params.remove("id");
        }
        params.remove("toy_title");
        params.remove("toy_content");
        params.remove("toy_dead_day");
        // toyDetail save
        System.out.println("key = " + params.toString());
        for( String key : params.keySet() ){
            System.out.println("key = " + key.toString());
            ToyDetailDto toyDetailDto = mapper.map(params.get(key), ToyDetailDto.class);
            ToyDetail toyDetail = mapper.map(toyDetailDto, ToyDetail.class);
            toyDetailService.saveToyDetail(toyDetail, toy);
        }

        return jsonObject;
    }

}
