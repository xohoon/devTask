package xohoon.devTask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.repository.MemberRepository;
import xohoon.devTask.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    @Override
    public void createUser(Member member) {
        memberRepository.save(member);
    }
}
