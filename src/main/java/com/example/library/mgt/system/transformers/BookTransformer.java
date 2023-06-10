package com.example.library.mgt.system.transformers;

import com.example.library.mgt.system.dtos.entries.BookEntryDto;
import com.example.library.mgt.system.dtos.responses.BookResponseDto;
import com.example.library.mgt.system.models.Book;
import com.example.library.mgt.system.models.Student;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookTransformer {

    public Book bookResponseDtoToBookEntity(BookEntryDto bookEntryDto) {
        return Book.builder()
                .title(bookEntryDto.getBookTitle())
                .category(bookEntryDto.getCategory())
                .numberOfCopies(bookEntryDto.getNumberOfCopies())
                .build();
    }

    public BookResponseDto bookEntityToBookResponseDto(Book book) {
        return BookResponseDto.builder()
                .bookTitle(book.getTitle())
                .category(book.getCategory())
                .authorName(book.getAuthor().getName())
                .numberOfCopies(book.getNumberOfCopies())
                .bookStatus(book.getStatus().toString())
                .build();
    }
}
