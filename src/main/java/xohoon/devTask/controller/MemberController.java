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
import org.springframework.web.bind.annotation.ResponseBody;
import xohoon.devTask.domain.dto.MemberDto;
import xohoon.devTask.domain.entity.Company;
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

    @GetMapping("/users") // 회원가입 페이지로 이동
    public String createUser() {
        return "member/register";
    }

    @PostMapping(value = "/users") // 회원가입
    public String createUser(MemberDto memberDto) throws Exception {
        System.out.println("memberDto = " + memberDto);
        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberDto, Member.class); // dto로 받은 데이터를 entity에 set
        member.setPassword(passwordEncoder.encode(member.getPassword())); // ps encode 후 다시 set
        memberService.createUser(member);

        return "redirect:/";
    }

    @GetMapping(value = "/login") // 로그인 페이지 이동
    public String login(@RequestParam(value = "error", required = false) String error, Model model,
                        @RequestParam(value = "exception", required = false) String exception) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "member/login";
    }

    @GetMapping(value = "/logout") // 로그아웃
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login";
    }

    @GetMapping("/denied") // 접근 거부 페이지
    public String accessDenied(@RequestParam(value = "exception", required = false) String exception, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        model.addAttribute("username", member.getUsername());
        model.addAttribute("exception", exception);

        return "member/denied";
    }

    @GetMapping(value = "mypage")
    public String mypage(Model model) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("member", member);

        return "member/mypage";
    }

    @PostMapping(value = "modify")
    @ResponseBody
    public Object userModify(MemberDto memberDto) {
//        System.out.println("memberDto = " + memberDto.toString());
//        memberService.updateUser(memberDto);

        return "";
    }
}
