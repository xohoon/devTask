package xohoon.devTask.service.impl;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import xohoon.devTask.domain.entity.Member;

import java.util.Collection;

@Getter
public class MemberContext extends User { // UserDetails 를 상속받은 User class 를 상속받아 db에 있는 회원정보를 UserDetails 타입으로 반환
    private final Member member;

    public MemberContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.member = member;
    }
}
