package xohoon.devTask.service;

import xohoon.devTask.domain.dto.MemberDto;
import xohoon.devTask.domain.entity.Member;

import java.util.List;

public interface MemberService {
    void createUser(Member member);

    void modifyUser(MemberDto memberDto);

    List<Member> getUsers();

    MemberDto getUser(Long id);

    void deleteUser(Long idx);

    void order();

    void updateUser(MemberDto memberDto);

//    Member getMember(String username);
}
