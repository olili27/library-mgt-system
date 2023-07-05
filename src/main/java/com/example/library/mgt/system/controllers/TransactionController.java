package com.example.library.mgt.system.controllers;

import com.example.library.mgt.system.dtos.entries.TransactionEntryDto;
import com.example.library.mgt.system.dtos.responses.TransactionResponseDto;
import com.example.library.mgt.system.exceptions.BookNotAvailableException;
import com.example.library.mgt.system.exceptions.InvalidCardException;
import com.example.library.mgt.system.exceptions.ResourceNotFoundException;
import com.example.library.mgt.system.services.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/book-a-copy")
    public ResponseEntity<TransactionResponseDto> bookACopy(@RequestBody TransactionEntryDto bookingDto) throws Exception {
        TransactionResponseDto responseDto = new TransactionResponseDto();

        try {
            responseDto = transactionService.bookACopy(bookingDto);
            responseDto.setResponseStatusCode(HttpStatus.CREATED);
        }
        catch (ResourceNotFoundException | BookNotAvailableException e) {
            responseDto.setResponseMessage(e.getMessage());
            responseDto.setResponseStatusCode(HttpStatus.NOT_FOUND);
        }
        catch (InvalidCardException e) {
            responseDto.setResponseMessage(e.getMessage());
            responseDto.setResponseStatusCode(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            responseDto.setResponseStatusCode(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(responseDto, responseDto.getResponseStatusCode());
    }

    @PostMapping("/return-a-copy")
    public ResponseEntity<TransactionResponseDto> returnACopy(@RequestBody TransactionEntryDto returnABookDto) throws Exception {
        TransactionResponseDto responseDto = new TransactionResponseDto();

        try {
            responseDto = transactionService.returnACopy(returnABookDto);
            responseDto.setResponseStatusCode(HttpStatus.OK);
        }
        catch (ResourceNotFoundException e) {
            responseDto.setResponseStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setResponseMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDto.setResponseStatusCode(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(responseDto, responseDto.getResponseStatusCode());
    }
}
