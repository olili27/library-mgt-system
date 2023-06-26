package com.example.library.mgt.system.transformers;

import com.example.library.mgt.system.dtos.entries.AuthorEntryDto;
import com.example.library.mgt.system.dtos.responses.AuthorResponseDto;
import com.example.library.mgt.system.models.Author;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorTransformer {

    public Author entryDtoToAuthorEntity(AuthorEntryDto authorEntryDto) {
        return Author.builder()
                .name(authorEntryDto.getName())
                .age(authorEntryDto.getAge())
                .email(authorEntryDto.getEmail())
                .build();
    }

    public AuthorResponseDto authorEntityToResponseDto(Author author) {
        return AuthorResponseDto.builder()
                .authorName(author.getName())
                .authorEmail(author.getEmail())
                .authorAge(author.getAge())
                .build();
    }
}
