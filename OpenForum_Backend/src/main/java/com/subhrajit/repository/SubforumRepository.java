package com.subhrajit.repository;

import com.subhrajit.model.Subforum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubforumRepository extends JpaRepository<Subforum, Long> {

    Optional<Subforum> findByName(String subforumName);
    boolean existsByName(String name);
}
