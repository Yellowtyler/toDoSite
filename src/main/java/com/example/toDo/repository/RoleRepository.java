package com.example.toDo.repository;

import com.example.toDo.entity.ERole;
import com.example.toDo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
      Optional<Role> findByName(ERole name);
}
