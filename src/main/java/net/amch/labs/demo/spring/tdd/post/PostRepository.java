package net.amch.labs.demo.spring.tdd.post;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface PostRepository extends ListCrudRepository<Post, Integer> {

    Optional<Post> findByTitle(String title);
}
