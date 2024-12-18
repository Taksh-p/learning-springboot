package com.example.SpringSecurity.SpringSecurityApplication.service;

import com.example.SpringSecurity.SpringSecurityApplication.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createNewPost(PostDto inputPost);

    PostDto getPostById(Long postId);

}
