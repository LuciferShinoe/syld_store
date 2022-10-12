package com.syld.store.repositories;


import com.syld.store.entities.User;
import com.syld.store.entities.UserClient;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);

    List<UserClient> findAllByEmail2(String email);
    @Query(value = "SELECT * FROM UserClient WHERE EMAIL = ?1", nativeQuery = true)
    List<UserClient> findAllByEmail3(String email);
    @Query("select u from UserClient u where u.username like %?1% or  u.email like %?1%")
    Page<UserClient> findAll(String key, Pageable pageable);
    UserClient findFirstByEmail(String email);

}
