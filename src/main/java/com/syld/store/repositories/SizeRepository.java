package com.syld.store.repositories;

import com.syld.store.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, String> {
    @Query(value = "select  * from size where size_name = ?1", nativeQuery = true)
    Size getByName(String name);
}
