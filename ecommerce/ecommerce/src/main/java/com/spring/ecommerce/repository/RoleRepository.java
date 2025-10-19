package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.Role;
import com.spring.ecommerce.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName name);
}
