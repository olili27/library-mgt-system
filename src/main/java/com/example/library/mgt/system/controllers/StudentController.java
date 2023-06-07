package com.example.library.mgt.system.controllers;

import com.example.library.mgt.system.dtos.entries.StudentEntryDto;
import com.example.library.mgt.system.dtos.responses.ResponseDto;
import com.example.library.mgt.system.exceptions.EmailAlreadyExistsException;
import com.example.library.mgt.system.services.interfaces.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("add")
    public ResponseEntity<ResponseDto> addStudent(@RequestBody StudentEntryDto studentEntryDto) throws Exception {

        ResponseDto responseDto = new ResponseDto();

        try {
            responseDto = studentService.addStudent(studentEntryDto);
            responseDto.setResponseStatusCode(HttpStatus.CREATED);
        }
        catch (EmailAlreadyExistsException e) {
            responseDto.setResponseMessage(e.getMessage());
            responseDto.setResponseStatusCode(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            responseDto.setResponseStatusCode(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(responseDto, responseDto.getResponseStatusCode());
    }
}
