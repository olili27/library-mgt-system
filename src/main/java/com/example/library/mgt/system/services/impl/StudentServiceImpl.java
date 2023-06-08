package com.example.library.mgt.system.services.impl;

import com.example.library.mgt.system.dtos.entries.StudentEntryDto;
import com.example.library.mgt.system.dtos.responses.ResponseDto;
import com.example.library.mgt.system.dtos.responses.StudentResponseDto;
import com.example.library.mgt.system.exceptions.EmailAlreadyExistsException;
import com.example.library.mgt.system.exceptions.ResourceNotFoundException;
import com.example.library.mgt.system.models.Student;
import com.example.library.mgt.system.repositories.StudentRepository;
import com.example.library.mgt.system.services.interfaces.StudentService;
import com.example.library.mgt.system.transformers.StudentTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentResponseDto addStudent(StudentEntryDto studentEntryDto) throws Exception {

        if (studentRepository.existsByEmail(studentEntryDto.getEmail())) throw new EmailAlreadyExistsException("Email already exists");

        Student student = Student.builder()
                .name(studentEntryDto.getName())
                .age(studentEntryDto.getAge())
                .email(studentEntryDto.getEmail())
                .school(studentEntryDto.getSchool())
                .build();

        Student savedStudent = studentRepository.save(student);

        return StudentTransformer.studentEntityToStudentResponseDto(savedStudent);
    }

    @Override
    public StudentResponseDto getStudentById(Integer studentId) throws Exception {
        if (!studentRepository.existsById(studentId)) throw new ResourceNotFoundException("User not found");

        Student student = studentRepository.findById(studentId).get();

        return StudentTransformer.studentEntityToStudentResponseDto(student);
    }

    @Override
    public List<StudentResponseDto> getStudentByName(String studentName) throws Exception {
        if (studentRepository.existsByName(studentName)) throw new ResourceNotFoundException("No user found");
        return null;
    }
}
