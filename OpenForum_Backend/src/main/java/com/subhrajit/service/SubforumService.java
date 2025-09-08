package com.subhrajit.service;

import com.subhrajit.dto.SubforumDto;
import com.subhrajit.exceptions.OpenForumException;
import com.subhrajit.exceptions.SubforumAlreadyExistsException;
import com.subhrajit.mapper.SubforumMapper;
import com.subhrajit.model.Subforum;
import com.subhrajit.repository.SubforumRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubforumService {

    private final SubforumRepository subforumRepository;
    private final SubforumMapper subforumMapper;

    @Transactional
    public SubforumDto save(SubforumDto subforumDto) {
        if (subforumRepository.existsByName(subforumDto.getName())) {
            throw new SubforumAlreadyExistsException("Subforum with name '" + subforumDto.getName() + "' already exists!");
        }
        Subforum save = subforumRepository.save(subforumMapper.mapDtoToSubforum(subforumDto));
        subforumDto.setId(save.getId());
        return subforumDto;
    }

    @Transactional(readOnly = true)
    public List<SubforumDto> getAll() {
        return subforumRepository.findAll()
                .stream()
                .map(subforumMapper::mapSubforumToDto)
                .collect(toList());
    }

    public SubforumDto getSubforum(Long id) {
        Subforum subforum = subforumRepository.findById(id)
                .orElseThrow(()-> new OpenForumException("No subforum with id: " + id));
        return subforumMapper.mapSubforumToDto(subforum);
    }
}
