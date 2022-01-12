package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xohoon.devTask.domain.dto.DevDto;
import xohoon.devTask.domain.entity.Dev;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.task.TaskDetail;
import xohoon.devTask.domain.entity.task.TaskSupport;
import xohoon.devTask.service.DevService;
import xohoon.devTask.service.task.TaskSupportService;

import java.util.List;

@Controller
@RequestMapping(value = "dev")
@RequiredArgsConstructor
public class DevController {

    private final DevService devService;
    private final TaskSupportService taskSupportService;

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

}
