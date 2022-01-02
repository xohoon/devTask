package xohoon.devTask.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import xohoon.devTask.domain.dto.MemberDto;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.Role;
import xohoon.devTask.service.MemberService;
import xohoon.devTask.service.RoleService;

import java.util.List;

@Controller
public class UserManagerController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value="/admin/accounts")
    public String getUsers(Model model) throws Exception {

//        List<Member> members = memberService.getUsers();
//        model.addAttribute("members", members);

        return "admin/user/list";
    }

    @PostMapping(value="/admin/members")
    public String modifyUser(MemberDto memberDto) throws Exception {

//        memberService.modifyUser(memberDto);

        return "redirect:/admin/accounts";
    }

    @GetMapping(value = "/admin/members/{id}")
    public String getUser(@PathVariable(value = "id") Long id, Model model) {

//        MemberDto accountDto = memberService.getUser(id);
        List<Role> roleList = roleService.getRoles();

//        model.addAttribute("member", memberDto);
        model.addAttribute("roleList", roleList);

        return "admin/user/detail";
    }

    @GetMapping(value = "/admin/members/delete/{id}")
    public String removeUser(@PathVariable(value = "id") Long id, Model model) {

//        memberService.deleteUser(id);

        return "redirect:/admin/users";
    }
}
