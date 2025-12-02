package com.levelup.gaming.controllers;

import com.levelup.gaming.models.Blog;
import com.levelup.gaming.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable String id) {
        return blogRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/admin")
    public ResponseEntity<Blog> createPost(@RequestBody Blog blog) {
        if (blog.getId() == null) {
            blog.setId(java.util.UUID.randomUUID().toString());
        }
        if (blog.getCreatedAt() == null) {
            blog.setCreatedAt(java.time.LocalDateTime.now());
        }
        Blog saved = blogRepository.save(blog);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}/admin")
    public ResponseEntity<Blog> updatePost(@PathVariable String id,
            @RequestBody com.levelup.gaming.dto.BlogUpdateDTO blogUpdate) {
        return blogRepository.findById(id)
                .map(existingBlog -> {
                    if (blogUpdate.getTitle() != null)
                        existingBlog.setTitle(blogUpdate.getTitle());
                    if (blogUpdate.getExcerpt() != null)
                        existingBlog.setExcerpt(blogUpdate.getExcerpt());
                    if (blogUpdate.getContent() != null)
                        existingBlog.setContent(blogUpdate.getContent());
                    if (blogUpdate.getImageUrl() != null)
                        existingBlog.setImageUrl(blogUpdate.getImageUrl());
                    if (blogUpdate.getAuthor() != null)
                        existingBlog.setAuthor(blogUpdate.getAuthor());

                    Blog saved = blogRepository.save(existingBlog);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/admin")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        if (blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
