package com.example.Task1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Task1.model.Post;
import com.example.Task1.repository.PostRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepo;

    public void add(Post post) {
        postRepo.save(post);
    }

    @CircuitBreaker(name = "postService", fallbackMethod = "fallbackView")
    public List<Post> view() {
        return postRepo.findAll();
    }

    @CircuitBreaker(name = "postService", fallbackMethod = "fallbackGetPostById")
    public Post getPostById(Long postId) {
        Optional<Post> optionalPost = postRepo.findById(postId);
        if (optionalPost.isPresent()) {
            return optionalPost.get();
        } else {
            throw new RuntimeException("Post not found");
        }
    }

    // Fallback method for view()
    public List<Post> fallbackView(Exception ex) {
        // Return an empty list or handle accordingly
        return List.of(); // Return an empty list
    }

    // Fallback method for getPostById()
    public Post fallbackGetPostById(Long postId, Exception ex) {
        // Create a fallback Post object with a default message
        Post fallbackPost = new Post();
        fallbackPost.setContent("No post found for ID: " + postId);
        return fallbackPost;
    }
}
