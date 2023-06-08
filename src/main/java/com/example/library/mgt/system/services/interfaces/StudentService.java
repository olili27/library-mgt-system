package com.example.library.mgt.system.services.interfaces;

import com.example.library.mgt.system.dtos.entries.StudentEntryDto;
import com.example.library.mgt.system.dtos.responses.StudentResponseDto;
import com.example.library.mgt.system.dtos.responses.StudentsResponseDto;


public interface StudentService {
    StudentResponseDto addStudent(StudentEntryDto studentEntryDto) throws Exception;

    StudentResponseDto getStudentById(Integer studentId) throws Exception;

    StudentsResponseDto getStudentsByName(String studentName)  throws Exception;

    StudentsResponseDto getAlStudents() throws Exception;
}
