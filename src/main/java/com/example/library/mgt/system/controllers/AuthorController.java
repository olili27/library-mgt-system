package com.example.library.mgt.system.controllers;

import com.example.library.mgt.system.dtos.entries.AuthorEntryDto;
import com.example.library.mgt.system.dtos.responses.AuthorResponseDto;
import com.example.library.mgt.system.exceptions.EmailAlreadyExistsException;
import com.example.library.mgt.system.services.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody AuthorEntryDto authorEntryDto) {
        AuthorResponseDto response = new AuthorResponseDto();

        try {
            response = authorService.addAuthor(authorEntryDto);
            response.setResponseStatusCode(HttpStatus.CREATED);
        }
        catch (EmailAlreadyExistsException e) {
            response.setResponseStatusCode(HttpStatus.BAD_REQUEST);
            response.setResponseMessage(e.getMessage());
        } catch (Exception e) {
            response.setResponseMessage(e.getMessage());
        }

        return new ResponseEntity<>(response, response.getResponseStatusCode());
    }
}
