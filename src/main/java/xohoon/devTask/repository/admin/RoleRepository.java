package xohoon.devTask.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.admin.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String name);

    @Override
    void delete(Role role);
}

