package com.example.library.mgt.system.services.interfaces;

import com.example.library.mgt.system.dtos.entries.StudentEntryDto;
import com.example.library.mgt.system.dtos.responses.ResponseDto;
import org.springframework.stereotype.Service;


public interface StudentService {
    ResponseDto addStudent(StudentEntryDto studentEntryDto) throws Exception;
}
