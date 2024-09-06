package com.prod_ready_feature.production_ready_feature.services;

import com.prod_ready_feature.production_ready_feature.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createPost(PostDto inputPost);

    PostDto getPostById(Long postId);


    PostDto updatePost(PostDto postDto, Long postId);
}
