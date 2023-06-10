package com.example.library.mgt.system.controllers;

import com.example.library.mgt.system.dtos.entries.BookEntryDto;
import com.example.library.mgt.system.dtos.responses.BookResponseDto;
import com.example.library.mgt.system.repositories.BookRepository;
import com.example.library.mgt.system.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/add/{authorId}")
    public ResponseEntity<BookResponseDto> addBook(@PathVariable("authorId") Integer authorId, @RequestBody BookEntryDto bookEntryDto)  {

        return new ResponseEntity<>(bookService.addBook(authorId, bookEntryDto), HttpStatus.CREATED);
    }
}
