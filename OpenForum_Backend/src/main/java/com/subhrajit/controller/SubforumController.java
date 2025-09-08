package com.subhrajit.controller;

import com.subhrajit.dto.SubforumDto;
import com.subhrajit.service.SubforumService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subforum")
@AllArgsConstructor
@Slf4j
public class SubforumController {

    private final SubforumService subforumService;

    @PostMapping
    public ResponseEntity<SubforumDto> createSubForum(
            @RequestBody SubforumDto subforumDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subforumService.save(subforumDto));
    }

    @GetMapping
    public ResponseEntity<List<SubforumDto>> getAllSubforums() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(subforumService.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<SubforumDto> getSubforum(
            @PathVariable Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subforumService.getSubforum(id));

    }
}
