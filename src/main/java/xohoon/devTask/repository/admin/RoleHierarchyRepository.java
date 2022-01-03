package xohoon.devTask.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.admin.RoleHierarchy;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {
    RoleHierarchy findByChildName(String roleName);
}

