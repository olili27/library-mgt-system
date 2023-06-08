package com.example.library.mgt.system.controllers;

import com.example.library.mgt.system.dtos.entries.StudentEntryDto;
import com.example.library.mgt.system.dtos.responses.ResponseDto;
import com.example.library.mgt.system.dtos.responses.StudentResponseDto;
import com.example.library.mgt.system.dtos.responses.StudentsResponseDto;
import com.example.library.mgt.system.exceptions.EmailAlreadyExistsException;
import com.example.library.mgt.system.exceptions.ResourceNotFoundException;
import com.example.library.mgt.system.services.interfaces.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("add")
    public ResponseEntity<StudentResponseDto> addStudent(@RequestBody StudentEntryDto studentEntryDto) throws Exception {

        StudentResponseDto studentResponseDto = new StudentResponseDto();

        try {
            studentResponseDto = studentService.addStudent(studentEntryDto);
            studentResponseDto.setResponseStatusCode(HttpStatus.CREATED);
        }
        catch (EmailAlreadyExistsException e) {
            studentResponseDto.setResponseMessage(e.getMessage());
            studentResponseDto.setResponseStatusCode(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            studentResponseDto.setResponseStatusCode(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(studentResponseDto, studentResponseDto.getResponseStatusCode());
    }

    @GetMapping("/get-by-id/{studentId}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable("studentId") Integer studentId) throws Exception {

        StudentResponseDto studentResponseDto = new StudentResponseDto();

        try {
            studentResponseDto = studentService.getStudentById(studentId);
            studentResponseDto.setResponseStatusCode(HttpStatus.OK);
        }
        catch (ResourceNotFoundException e) {
            studentResponseDto.setResponseStatusCode(HttpStatus.NOT_FOUND);
            studentResponseDto.setResponseMessage(e.getMessage());
        }
        catch (Exception e) {
            studentResponseDto.setResponseStatusCode(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(studentResponseDto, studentResponseDto.getResponseStatusCode());
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<StudentsResponseDto> getStudentByName(@RequestParam String studentName) {

        StudentsResponseDto studentsResponseDto = new StudentsResponseDto();

        try {
            studentsResponseDto = studentService.getStudentByName(studentName);
            studentsResponseDto.setResponseStatusCode(HttpStatus.OK);
        }
        catch (ResourceNotFoundException e) {
            studentsResponseDto.setResponseMessage(e.getMessage());
            studentsResponseDto.setResponseStatusCode(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            studentsResponseDto.setResponseStatusCode(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(studentsResponseDto, studentsResponseDto.getResponseStatusCode());
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
