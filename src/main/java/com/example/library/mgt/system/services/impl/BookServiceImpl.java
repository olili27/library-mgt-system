package com.example.library.mgt.system.services.impl;

import com.example.library.mgt.system.dtos.entries.BookEntryDto;
import com.example.library.mgt.system.dtos.responses.BookResponseDto;
import com.example.library.mgt.system.enums.BookStatus;
import com.example.library.mgt.system.exceptions.ResourceNotFoundException;
import com.example.library.mgt.system.models.Author;
import com.example.library.mgt.system.models.Book;
import com.example.library.mgt.system.models.BookItem;
import com.example.library.mgt.system.repositories.AuthorRepository;
import com.example.library.mgt.system.repositories.BookRepository;
import com.example.library.mgt.system.services.interfaces.BookService;
import com.example.library.mgt.system.transformers.BookItemTransformer;
import com.example.library.mgt.system.transformers.BookTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        List<BookItem> bookItems = new ArrayList<>();

        int noOfCopies = bookEntryDto.getNumberOfCopies();
        for (int i = 0; i < noOfCopies; i++) {
            bookItems.add(BookItemTransformer.createBookItem(book));
        }

        book.setBookItems(bookItems);

        author.getBooks().add(book);
        authorRepository.save(author);

        return BookTransformer.bookEntityToBookResponseDto(book);
    }

    @Override
    public BookResponseDto getBookById(Integer bookId) {
        Book book = bookRepository.findById(bookId).get();

        return BookTransformer.bookEntityToBookResponseDto(book);
    }

    @Override
    public BookResponseDto getBookByName(String bookName) throws Exception {
        if (!bookRepository.existsByTitle(bookName)) throw new ResourceNotFoundException("No book found found");

        Book book = bookRepository.findByTitle(bookName);
        return BookTransformer.bookEntityToBookResponseDto(book);
    }
}
