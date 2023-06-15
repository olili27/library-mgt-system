package com.example.library.mgt.system.controllers;

import com.example.library.mgt.system.dtos.entries.BookEntryDto;
import com.example.library.mgt.system.dtos.responses.BookResponseDto;
import com.example.library.mgt.system.dtos.responses.BooksResposeDto;
import com.example.library.mgt.system.exceptions.ResourceNotFoundException;
import com.example.library.mgt.system.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    /*
      TODO
      -> handle null or empty fields
       */

    private final BookService bookService;

    @PostMapping("/add/{authorId}")
    public ResponseEntity<BookResponseDto> addBook(@PathVariable("authorId") Integer authorId, @RequestBody BookEntryDto bookEntryDto)  {

        return new ResponseEntity<>(bookService.addBook(authorId, bookEntryDto), HttpStatus.CREATED);
    }

    @GetMapping("/get-by-id/{bookId}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable("bookId") Integer bookId) {
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<BookResponseDto> getBookByName(@RequestParam("bookName") String bookName)throws Exception {
        BookResponseDto bookResponseDto = new BookResponseDto();
        try {
            bookResponseDto = bookService.getBookByName(bookName);
            bookResponseDto.setResponseStatusCode(HttpStatus.OK);
        }
        catch (ResourceNotFoundException e) {
            bookResponseDto.setResponseMessage(e.getMessage());
            bookResponseDto.setResponseStatusCode(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            bookResponseDto.setResponseStatusCode(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookResponseDto, bookResponseDto.getResponseStatusCode());
    }

    @GetMapping("/get-all")
    public ResponseEntity<BooksResposeDto> getAllBooks() {
        return null;
    }

}
