package com.epam.jmp.jmpm7springboot.repository;

import com.epam.jmp.jmpm7springboot.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoleRepository
 * Date: 03/13/2023
 *
 * @author Yauheni Antsipenka
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
