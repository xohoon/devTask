package xohoon.devTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.RoleHierarchy;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {
    RoleHierarchy findByChildName(String roleName);
}

