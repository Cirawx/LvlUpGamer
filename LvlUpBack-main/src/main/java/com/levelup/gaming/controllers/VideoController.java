package com.levelup.gaming.controllers;

import com.levelup.gaming.models.Video;
import com.levelup.gaming.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "*")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @GetMapping("/featured")
    public List<Video> getFeaturedVideos() {
        return videoRepository.findByIsFeaturedTrue();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable String id,
            @RequestBody com.levelup.gaming.dto.VideoUpdateDTO videoUpdate) {
        return videoRepository.findById(id)
                .map(existingVideo -> {
                    if (videoUpdate.getTitle() != null)
                        existingVideo.setTitle(videoUpdate.getTitle());
                    if (videoUpdate.getEmbedUrl() != null)
                        existingVideo.setEmbedUrl(videoUpdate.getEmbedUrl());
                    if (videoUpdate.getIsFeatured() != null)
                        existingVideo.setFeatured(videoUpdate.getIsFeatured());

                    Video saved = videoRepository.save(existingVideo);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Video> createVideo(@RequestBody Video video) {
        if (video.getId() == null) {
            video.setId(java.util.UUID.randomUUID().toString());
        }
        Video saved = videoRepository.save(video);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable String id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
