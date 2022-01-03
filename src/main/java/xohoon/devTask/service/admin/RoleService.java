package xohoon.devTask.service.admin;

import xohoon.devTask.domain.entity.admin.Role;

import java.util.List;

public interface RoleService {
    Role getRole(long id);

    List<Role> getRoles();

    void createRole(Role role);

    void deleteRole(long id);
}
