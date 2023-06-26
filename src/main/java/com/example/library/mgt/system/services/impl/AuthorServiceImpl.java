package com.example.library.mgt.system.services.impl;

import com.example.library.mgt.system.dtos.entries.AuthorEntryDto;
import com.example.library.mgt.system.dtos.responses.AuthorResponseDto;
import com.example.library.mgt.system.exceptions.EmailAlreadyExistsException;
import com.example.library.mgt.system.models.Author;
import com.example.library.mgt.system.repositories.AuthorRepository;
import com.example.library.mgt.system.services.interfaces.AuthorService;
import com.example.library.mgt.system.transformers.AuthorTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorResponseDto addAuthor(AuthorEntryDto authorEntryDto) throws Exception {
        if (authorRepository.existsByEmail(authorEntryDto.getEmail())) throw new EmailAlreadyExistsException("Email already exists");

        Author author = AuthorTransformer.entryDtoToAuthorEntity(authorEntryDto);
        author = authorRepository.save(author);

        return AuthorTransformer.authorEntityToResponseDto(author);
    }
}
