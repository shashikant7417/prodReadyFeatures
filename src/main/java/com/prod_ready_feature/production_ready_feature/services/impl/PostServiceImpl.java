package com.prod_ready_feature.production_ready_feature.services.impl;

import com.prod_ready_feature.production_ready_feature.dto.PostDto;
import com.prod_ready_feature.production_ready_feature.entities.PostEntity;
import com.prod_ready_feature.production_ready_feature.exceptions.ResourceNotFoundException;
import com.prod_ready_feature.production_ready_feature.repositories.PostRepository;
import com.prod_ready_feature.production_ready_feature.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createPost(PostDto inputPost) {

         log.info("Trying to create Post");
         PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);

        return modelMapper.map(postRepository.save(postEntity), PostDto.class);

    }

    @Override
    public PostDto getPostById(Long postId) {
        log.trace("Trying to get post with id: {}" , postId);

        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " +postId));
        log.error("Exception occured in getPostById" );
        return modelMapper.map(postEntity,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {

        PostEntity olderPost = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id : "+postId));
        postDto.setId(postId);
        modelMapper.map(postDto,olderPost);
        PostEntity savedPostEntity = postRepository.save(olderPost);
        return modelMapper.map(savedPostEntity,PostDto.class);

    }

}
