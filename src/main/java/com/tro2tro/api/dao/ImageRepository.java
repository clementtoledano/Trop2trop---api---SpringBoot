package com.tro2tro.api.dao;


import com.tro2tro.api.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("http://localhost:4200")
public interface ImageRepository extends JpaRepository<Image, Long> {
}
