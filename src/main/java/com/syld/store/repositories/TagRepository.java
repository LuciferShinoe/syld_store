package com.syld.store.repositories;

import com.syld.store.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

    @Query(value = "select * from tag where tag_name = ?1",nativeQuery = true)
    Tag findByTagName(String tag_name);

}
