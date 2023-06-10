package com.example.library.mgt.system.services.impl;

import com.example.library.mgt.system.dtos.entries.BookEntryDto;
import com.example.library.mgt.system.dtos.responses.BookResponseDto;
import com.example.library.mgt.system.enums.BookStatus;
import com.example.library.mgt.system.models.Author;
import com.example.library.mgt.system.models.Book;
import com.example.library.mgt.system.repositories.AuthorRepository;
import com.example.library.mgt.system.repositories.BookRepository;
import com.example.library.mgt.system.services.interfaces.BookService;
import com.example.library.mgt.system.transformers.BookTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    @Override
    public BookResponseDto addBook(Integer authorId, BookEntryDto bookEntryDto) {
        Author author = authorRepository.findById(authorId).get();

        Book book = BookTransformer.bookResponseDtoToBookEntity(bookEntryDto);
        book.setAuthor(author);
        book.setStatus(BookStatus.AVAILABLE);

        author.getBooks().add(book);
        authorRepository.save(author);

        return BookTransformer.bookEntityToBookResponseDto(book);
    }
}
