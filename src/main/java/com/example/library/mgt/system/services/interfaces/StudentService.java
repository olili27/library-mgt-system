package com.example.library.mgt.system.services.interfaces;

import com.example.library.mgt.system.dtos.entries.StudentEntryDto;
import com.example.library.mgt.system.dtos.responses.ResponseDto;
import com.example.library.mgt.system.dtos.responses.StudentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {
    StudentResponseDto addStudent(StudentEntryDto studentEntryDto) throws Exception;

    StudentResponseDto getStudentById(Integer studentId) throws Exception;

    List<StudentResponseDto> getStudentByName(String studentName)  throws Exception;
}
