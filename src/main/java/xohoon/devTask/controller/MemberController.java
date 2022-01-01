package xohoon.devTask.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xohoon.devTask.domain.dto.MemberDto;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String createUser() {
        return "member/register";
    }

    @PostMapping(value = "/users")
    public String createUser(MemberDto memberDto) throws Exception {
        System.out.println("memberDto = " + memberDto);
        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberDto, Member.class); // dto로 받은 데이터를 entity에 set
        member.setPassword(passwordEncoder.encode(member.getPassword())); // ps encode 후 다시 set
        memberService.createUser(member);

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model,
                        @RequestParam(value = "exception", required = false) String exception) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "member/login";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login";
    }

}
