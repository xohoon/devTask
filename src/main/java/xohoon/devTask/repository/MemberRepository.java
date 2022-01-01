package xohoon.devTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
}
