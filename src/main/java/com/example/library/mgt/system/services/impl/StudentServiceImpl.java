package com.example.library.mgt.system.services.impl;

import com.example.library.mgt.system.dtos.entries.StudentEntryDto;
import com.example.library.mgt.system.dtos.responses.ResponseDto;
import com.example.library.mgt.system.exceptions.EmailAlreadyExistsException;
import com.example.library.mgt.system.exceptions.ResourceNotFoundException;
import com.example.library.mgt.system.models.Student;
import com.example.library.mgt.system.repositories.StudentRepository;
import com.example.library.mgt.system.services.interfaces.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public ResponseDto addStudent(StudentEntryDto studentEntryDto) throws Exception {

        if (studentRepository.existsByEmail(studentEntryDto.getEmail())) throw new EmailAlreadyExistsException("Email already exists");

        Student student = Student.builder()
                .name(studentEntryDto.getName())
                .age(studentEntryDto.getAge())
                .email(studentEntryDto.getEmail())
                .school(studentEntryDto.getSchool())
                .build();

        Student savedStudent = studentRepository.save(student);

        return ResponseDto.builder()
                .studentName(savedStudent.getName())
                .studentSchool(savedStudent.getSchool())
                .studentAge(savedStudent.getAge())
                .build();
    }

    @Override
    public ResponseDto getStudentById(Integer studentId) throws Exception {
        if (!studentRepository.existsById(studentId)) throw new ResourceNotFoundException("User not found");

        Student student = studentRepository.findById(studentId).get();

        return ResponseDto.builder()
                .studentName(student.getName())
                .studentSchool(student.getSchool())
                .studentAge(student.getAge())
                .build();
    }
}
