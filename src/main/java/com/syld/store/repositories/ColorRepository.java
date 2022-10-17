package com.syld.store.repositories;

import com.syld.store.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color,String> {

    @Query(value = "select * from color where name =?1", nativeQuery = true)
    Color findByName(String name);

    @Query(value = "select * from color where name ?1", nativeQuery = true)
    Color findByColorCode(String colorCode);

    @Query(value = "select * from color where slug = ?1", nativeQuery = true)
    Optional<Color> findBySlug(String slug);

}
