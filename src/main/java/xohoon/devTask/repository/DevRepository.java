package xohoon.devTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.Dev;

public interface DevRepository extends JpaRepository<Dev, Long> {
    Dev findByMember_id(Long id);
}
