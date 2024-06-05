package com.example.java_thanhquang_0406.Services;

import com.example.java_thanhquang_0406.Entities.Role;
import com.example.java_thanhquang_0406.Repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService{
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public void addRole(Role role) {
        roleRepository.save(role);
    }

    public void updateRole(Role role) {
        Role existingRole = roleRepository.findById(role.getRole_id())
                .orElseThrow(() -> new IllegalStateException("Role with ID " +
                        role.getRole_id() + " does not exist."));
        existingRole.setRole_name(role.getRole_name());
        roleRepository.save(existingRole);
    }

    public void deleteRoleById(Long id ) {
        if(!roleRepository.existsById(id)) {
            throw new IllegalStateException("Role with ID " + id + " does not exist.");
        }
        roleRepository.deleteById(id);
    }
}
