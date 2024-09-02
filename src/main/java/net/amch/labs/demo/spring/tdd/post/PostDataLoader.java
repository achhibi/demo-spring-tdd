package net.amch.labs.demo.spring.tdd.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PostDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(PostDataLoader.class);
    private final ObjectMapper objectMapper;
    private final PostRepository postRepository;

    @Value("classpath:data/posts.json")
    private Resource postsJson;

    public PostDataLoader(ObjectMapper objectMapper, PostRepository postRepository) {
        this.objectMapper = objectMapper;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws IOException {
        if (postRepository.count() == 0) {
            log.info("Loading posts into database from JSON: /data/posts.json");

            Posts response = objectMapper.readValue(postsJson.getInputStream(), Posts.class);
            postRepository.saveAll(response.posts());
        }
    }
}