package com.example.library.mgt.system.services.interfaces;

import com.example.library.mgt.system.dtos.entries.AuthorEntryDto;
import com.example.library.mgt.system.dtos.responses.AuthorResponseDto;
import org.springframework.stereotype.Service;

public interface AuthorService {

    AuthorResponseDto addAuthor(AuthorEntryDto authorEntryDto) throws Exception;
}
