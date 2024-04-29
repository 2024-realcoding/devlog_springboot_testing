package com.cnu.devlog_springboot.service;

import com.cnu.devlog_springboot.error.SlangBadRequestException;
import com.cnu.devlog_springboot.model.Post;
import com.cnu.devlog_springboot.model.request.PostRequest;
import com.cnu.devlog_springboot.repository.PostRepository;
import com.cnu.devlog_springboot.service.valid.PostValidService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostValidService postValidService;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }
    private final List<String> slangList = List.of("비속어", "비속어1", "비속어2");

    public Post createPost(PostRequest postRequest) {
        if (postValidService.isSlangInclude(slangList, postRequest.title(), postRequest.contents())){
            throw new SlangBadRequestException();
        }
        return postRepository.save(new Post(
                null,
                postRequest.title(),
                postRequest.contents(),
                postRequest.tag()
        ));
    }

    @Transactional
    public Post updatePost(Integer postId, PostRequest postRequest) {
        return postRepository.findById(postId)
                .map(post -> {
                    post.setTitle(postRequest.title());
                    post.setContents(postRequest.contents());
                    post.setTag(postRequest.tag());
                    return postRepository.save(post);
                })
                .orElse(null);
    }

    public Post getPost(Integer postId) {
        log.info("getPost {}", postId);
        return postRepository.findById(postId)
                .orElse(null);
    }

    public void deletePost(Integer postId) {
        postRepository.findById(postId)
                .ifPresent(postRepository::delete);
    }
}
