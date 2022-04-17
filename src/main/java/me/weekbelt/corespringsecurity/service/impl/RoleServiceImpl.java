package me.weekbelt.corespringsecurity.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import me.weekbelt.corespringsecurity.domain.entity.Role;
import me.weekbelt.corespringsecurity.repository.RoleRepository;
import me.weekbelt.corespringsecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role getRole(long id) {
        return roleRepository.findById(id).orElse(new Role());
    }

    @Transactional
    public List<Role> getRoles() {

        return roleRepository.findAll();
    }

    @Transactional
    public void createRole(Role role) {

        roleRepository.save(role);
    }

    @Transactional
    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }
}