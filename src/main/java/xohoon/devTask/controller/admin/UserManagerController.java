package xohoon.devTask.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xohoon.devTask.domain.dto.MemberDto;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.admin.Role;
import xohoon.devTask.service.MemberService;
import xohoon.devTask.service.admin.RoleService;

import java.util.List;

@Controller
@RequestMapping(value="/hide")
public class UserManagerController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value="/admin/user")
    public String getUsers(Model model) throws Exception {

        List<Member> members = memberService.getUsers();
        model.addAttribute("members", members);

        return "admin/user/list";
    }

    @PostMapping(value="/admin/user")
    public String modifyUser(MemberDto memberDto) throws Exception {

        memberService.modifyUser(memberDto);

        return "redirect:/hide/admin/user";
    }

    @GetMapping(value = "/admin/user/{id}")
    public String getUser(@PathVariable(value = "id") Long id, Model model) {

        MemberDto memberDto = memberService.getUser(id);
        List<Role> roleList = roleService.getRoles();

        model.addAttribute("member", memberDto);
        model.addAttribute("roleList", roleList);

        return "admin/user/detail";
    }

    @GetMapping(value = "/admin/user/delete/{id}")
    public String removeUser(@PathVariable(value = "id") Long id, Model model) {

//        memberService.deleteUser(id);

        return "redirect:/hide/admin/user";
    }
}
