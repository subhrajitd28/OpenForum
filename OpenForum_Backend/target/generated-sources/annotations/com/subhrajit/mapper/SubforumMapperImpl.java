package com.subhrajit.mapper;

import com.subhrajit.dto.SubforumDto;
import com.subhrajit.model.Subforum;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-07T01:47:45+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class SubforumMapperImpl implements SubforumMapper {

    @Override
    public SubforumDto mapSubforumToDto(Subforum subforum) {
        if ( subforum == null ) {
            return null;
        }

        SubforumDto.SubforumDtoBuilder subforumDto = SubforumDto.builder();

        subforumDto.id( subforum.getId() );
        subforumDto.name( subforum.getName() );
        subforumDto.description( subforum.getDescription() );

        subforumDto.numberOfPosts( mapPosts(subforum.getPosts()) );

        return subforumDto.build();
    }

    @Override
    public Subforum mapDtoToSubforum(SubforumDto dto) {
        if ( dto == null ) {
            return null;
        }

        Subforum.SubforumBuilder subforum = Subforum.builder();

        subforum.id( dto.getId() );
        subforum.name( dto.getName() );
        subforum.description( dto.getDescription() );

        return subforum.build();
    }
}
