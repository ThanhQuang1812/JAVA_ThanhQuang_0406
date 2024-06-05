package com.example.java_thanhquang_0406.Repositories;

import com.example.java_thanhquang_0406.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
