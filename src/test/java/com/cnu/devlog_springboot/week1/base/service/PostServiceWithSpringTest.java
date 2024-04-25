package com.cnu.devlog_springboot.week1.base.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.cnu.devlog_springboot.model.Post;
import com.cnu.devlog_springboot.model.request.PostRequest;
import com.cnu.devlog_springboot.service.PostService;
import com.cnu.devlog_springboot.week1.practice.service.fixture.PostFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest
public class PostServiceWithSpringTest {
    @Autowired
    PostService postService;

    @DisplayName("Spring 테스트 코드로 Spring Component 실행해보기")
    @Test
    void testDoCodeRun() {
        PostRequest normalPostRequest = PostFixture.getNormalPostRequest();// given
        Post post = postService.createPost(normalPostRequest); // when
        assertThat(post).isNotNull(); // then
    }
}
