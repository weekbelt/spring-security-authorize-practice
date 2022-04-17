package me.weekbelt.corespringsecurity.service;

import java.util.List;
import me.weekbelt.corespringsecurity.domain.entity.Role;

public interface RoleService {

    Role getRole(long id);

    List<Role> getRoles();

    void createRole(Role role);

    void deleteRole(long id);
}