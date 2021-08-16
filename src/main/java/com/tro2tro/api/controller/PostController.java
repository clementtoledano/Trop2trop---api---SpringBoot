package com.tro2tro.api.controller;

import com.tro2tro.api.dao.CategoryRepository;
import com.tro2tro.api.dao.PostRepository;
import com.tro2tro.api.dao.UserRepository;
import com.tro2tro.api.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/custom")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category/{categoryId}/posts")
    public Page<Post> getAllPostsByPostId(@PathVariable(value = "categoryId") Long categoryId,
                                          Pageable pageable) {
        return postRepository.findByCategoryId(categoryId, pageable);
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public Post createPost(@PathVariable(value = "userId") Long userId,
                           @PathVariable(value = "categoryId") Long categoryId,
                           @Valid @RequestBody Post post) {
        return userRepository.findById(userId).map(user -> {
            post.setUser(user);
            return categoryRepository.findById(categoryId).map(category -> {
                post.setCategory(category);
                return postRepository.save(post);
            }).orElseThrow(() -> new ResourceNotFoundException("PostId " + categoryId + " not found"));
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + userId + " not found"));
    }

    @PutMapping("/user/{userId}/category/{categoryId}/posts/{postId}")
    public Post editPost(@PathVariable(value = "userId") Long userId,
                         @PathVariable(value = "categoryId") Long categoryId,
                         @PathVariable(value = "postId") Long postId,
                         @Valid @RequestBody Post post){
        return null;
    }

}
