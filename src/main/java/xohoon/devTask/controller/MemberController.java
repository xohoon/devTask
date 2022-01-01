package xohoon.devTask.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import xohoon.devTask.domain.dto.MemberDto;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.service.MemberService;

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
}
