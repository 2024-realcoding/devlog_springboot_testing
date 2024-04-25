package com.cnu.devlog_springboot.controller;

import com.cnu.devlog_springboot.model.Post;
import com.cnu.devlog_springboot.model.request.PostRequest;
import com.cnu.devlog_springboot.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PostService postService;

    // GET /posts
    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        logger.info("request get post list");
        return ResponseEntity.ok(postService.getPosts());
    }

    // GET /posts/{postId}
    @GetMapping("{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId")Integer postId) {
        logger.info("request get post {}", postId);
        return ResponseEntity.ok(postService.getPost(postId));
    }

    // POST /posts
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest) {
        logger.info("request create post {} {}", postRequest.title(), postRequest.contents());
        return ResponseEntity.ok(postService.createPost(postRequest));
    }

    // PUT /posts/{postId}
    // ex. localhost:8080/posts/3
    @PutMapping("{postId}")
    public ResponseEntity<Post> updatePost(
            @PathVariable("postId")Integer postId,
            @RequestBody PostRequest postRequest
    ) {
        logger.info("request update post {}", postId);
        return ResponseEntity.ok(postService.updatePost(postId, postRequest));
    }

    // DELETE /posts/{postId}
    @DeleteMapping("{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") Integer postId) {
        logger.info("request delete post {}", postId);
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
