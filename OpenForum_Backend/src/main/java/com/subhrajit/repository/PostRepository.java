package com.subhrajit.repository;

import com.subhrajit.model.Post;
import com.subhrajit.model.Subforum;
import com.subhrajit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubforum(Subforum subforum);

    List<Post> findByUser(User user);
}
