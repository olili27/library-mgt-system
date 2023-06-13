package com.example.library.mgt.system.services.interfaces;

import com.example.library.mgt.system.dtos.entries.BookEntryDto;
import com.example.library.mgt.system.dtos.responses.BookResponseDto;
import org.springframework.stereotype.Service;

public interface BookService {

    BookResponseDto addBook(Integer authorId, BookEntryDto bookEntryDto);

    BookResponseDto getBookById(Integer bookId);

    BookResponseDto getBookByName(String bookName) throws Exception;
}
