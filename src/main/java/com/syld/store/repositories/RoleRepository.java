package com.syld.store.repositories;

import com.syld.store.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {

    @Query(value = "select * from role where role_name = ?1",nativeQuery = true)
    Optional<Role> findByRole_name(String role_name);

}
