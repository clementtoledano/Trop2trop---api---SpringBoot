package com.tro2tro.api.dao;

import com.tro2tro.api.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;


@Repository
@CrossOrigin("http://localhost:4200")
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
      // Page<User> findByNameContaining(@RequestParam("name") String name, Pageable pageable);

}
