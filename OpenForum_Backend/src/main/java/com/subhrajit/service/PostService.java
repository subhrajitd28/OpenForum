package com.subhrajit.service;


import com.subhrajit.dto.PostRequest;
import com.subhrajit.dto.PostResponse;
import com.subhrajit.exceptions.PostNotFoundException;
import com.subhrajit.exceptions.SubforumNotFoundException;
import com.subhrajit.mapper.PostMapper;
import com.subhrajit.model.Post;
import com.subhrajit.model.Subforum;
import com.subhrajit.model.User;
import com.subhrajit.repository.PostRepository;
import com.subhrajit.repository.SubforumRepository;
import com.subhrajit.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final SubforumRepository subforumRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        Subforum subforum = subforumRepository.findByName(postRequest.getSubforumName())
                .orElseThrow(() -> new SubforumNotFoundException(postRequest.getSubforumName()));
        postRepository.save(postMapper.map(postRequest, subforum, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubforum(Long subforumId) {
        Subforum subforum = subforumRepository.findById(subforumId)
                .orElseThrow(() -> new SubforumNotFoundException(subforumId.toString()));
        List<Post> posts = postRepository.findAllBySubforum(subforum);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
