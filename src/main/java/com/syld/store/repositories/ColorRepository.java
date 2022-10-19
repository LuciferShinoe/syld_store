package com.syld.store.repositories;

import com.syld.store.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, String> {

    @Query(value = "select * from color where color_name =?1", nativeQuery = true)
    Color findByName(String name);

    @Query(value = "select * from color where color_code =  ?1", nativeQuery = true)
    Color findByColorCode(String colorCode);

    @Query(value = "select * from color where color_name = ?1 and id !=?2", nativeQuery = true)
    Optional<Color> getByNameNotSame(String color_name, String id);

    @Query(value = "select * from color where color_name = ?1", nativeQuery = true)
    Optional<Color> findByColor_name(String color_name);
}
