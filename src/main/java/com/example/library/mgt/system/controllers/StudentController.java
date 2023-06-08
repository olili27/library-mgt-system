package com.example.library.mgt.system.controllers;

import com.example.library.mgt.system.dtos.entries.StudentEntryDto;
import com.example.library.mgt.system.dtos.responses.ResponseDto;
import com.example.library.mgt.system.exceptions.EmailAlreadyExistsException;
import com.example.library.mgt.system.exceptions.ResourceNotFoundException;
import com.example.library.mgt.system.services.interfaces.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-by-id/{studentId}")
    public ResponseEntity<ResponseDto> getStudentById(@PathVariable("studentId") Integer studentId) throws Exception {

        ResponseDto responseDto = new ResponseDto();

        try {
            responseDto = studentService.getStudentById(studentId);
            responseDto.setResponseStatusCode(HttpStatus.OK);
        }
        catch (ResourceNotFoundException e) {
            responseDto.setResponseStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setResponseMessage(e.getMessage());
        }
        return new ResponseEntity<>(responseDto, responseDto.getResponseStatusCode());
    }
}

/*
TODO
get by id
get by name
get all
get by age
get by school
update
get students by book name
 */
