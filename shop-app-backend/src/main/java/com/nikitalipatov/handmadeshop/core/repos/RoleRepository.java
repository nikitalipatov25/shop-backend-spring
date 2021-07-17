package com.nikitalipatov.handmadeshop.core.repos;


import com.nikitalipatov.handmadeshop.core.models.ERole;
import com.nikitalipatov.handmadeshop.core.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
