package com.subhrajit.mapper;

import com.subhrajit.dto.SubforumDto;
import com.subhrajit.model.Post;
import com.subhrajit.model.Subforum;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubforumMapper {
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subforum.getPosts()))")
    SubforumDto mapSubforumToDto(Subforum subforum);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subforum mapDtoToSubforum(SubforumDto dto);
}
