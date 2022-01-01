package xohoon.devTask.service;

import org.springframework.stereotype.Service;
import xohoon.devTask.domain.entity.Member;

@Service
public interface MemberService {
    void createUser(Member member);
}
