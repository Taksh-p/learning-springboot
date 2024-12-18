package com.example.SpringSecurity.SpringSecurityApplication.controller;

import com.example.SpringSecurity.SpringSecurityApplication.dto.PostDto;
import com.example.SpringSecurity.SpringSecurityApplication.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public List<PostDto> getAllPost(){ return postService.getAllPosts();}

    @GetMapping("/{postId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public PostDto getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost) {
        return postService.createNewPost(inputPost);
    }


}
