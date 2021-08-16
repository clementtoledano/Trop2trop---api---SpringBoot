package com.tro2tro.api.dao;

import com.tro2tro.api.entity.Category;
import com.tro2tro.api.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("http://localhost:4200")
// @RepositoryRestResource(collectionResourceRel = "category", path = "category")
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
}
