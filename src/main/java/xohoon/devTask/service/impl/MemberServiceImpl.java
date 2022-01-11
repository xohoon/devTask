package xohoon.devTask.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.dto.MemberDto;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.admin.Role;
import xohoon.devTask.repository.MemberRepository;
import xohoon.devTask.repository.admin.RoleRepository;
import xohoon.devTask.service.MemberService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(Member Member){

        Role role = roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Member.setUserRoles(roles);
        memberRepository.save(Member);
    }

    @Transactional
    @Override
    public void modifyUser(MemberDto memberDto){

        ModelMapper modelMapper = new ModelMapper();
        Member Member = modelMapper.map(memberDto, Member.class);

        if(memberDto.getRoles() != null){
            Set<Role> roles = new HashSet<>();
            memberDto.getRoles().forEach(role -> {
                Role r = roleRepository.findByRoleName(role);
                roles.add(r);
            });
            Member.setUserRoles(roles);
        }
        Member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberRepository.save(Member);

    }

    @Transactional
    public MemberDto getUser(Long id) {

        Member Member = memberRepository.findById(id).orElse(new Member());
        ModelMapper modelMapper = new ModelMapper();
        MemberDto MemberDto = modelMapper.map(Member, MemberDto.class);

        List<String> roles = Member.getUserRoles()
                .stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toList());

        MemberDto.setRoles(roles);
        return MemberDto;
    }

    @Transactional
    public List<Member> getUsers() {
        return memberRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    @Secured("ROLE_MANAGER")
    public void order() {
        System.out.println("order");
    }

    @Transactional
    public void updateUser(MemberDto memberDto) {
        ModelMapper modelMapper = new ModelMapper();
        Member Member = modelMapper.map(memberDto, Member.class);

        Member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberRepository.save(Member);
    }

//    @Transactional
//    public Member getMember(String username) {
//        return memberRepository.findByUsername(username);
//    }

}
