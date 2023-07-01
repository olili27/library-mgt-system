package com.example.library.mgt.system.services.impl;

import com.example.library.mgt.system.dtos.entries.StudentEntryDto;
import com.example.library.mgt.system.dtos.responses.StudentResponseDto;
import com.example.library.mgt.system.dtos.responses.StudentsResponseDto;
import com.example.library.mgt.system.exceptions.EmailAlreadyExistsException;
import com.example.library.mgt.system.exceptions.ResourceNotFoundException;
import com.example.library.mgt.system.models.Card;
import com.example.library.mgt.system.models.Student;
import com.example.library.mgt.system.repositories.StudentRepository;
import com.example.library.mgt.system.services.interfaces.StudentService;
import com.example.library.mgt.system.transformers.StudentTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

        Card card = Card.builder()
                .student(student)
                .createdAt(LocalDate.now())
                .hasBooked(false)
                .expiresAt(LocalDate.now().plusYears(4))
                .build();

        student.setCard(card);
        Student savedStudent = studentRepository.save(student);

        return StudentTransformer.studentEntityToStudentResponseDto(savedStudent);
    }

    @Override
    public StudentResponseDto getStudentById(Integer studentId) throws Exception {
        if (!studentRepository.existsById(studentId)) throw new ResourceNotFoundException("Student not found");

        Student student = studentRepository.findById(studentId).get();

        return StudentTransformer.studentEntityToStudentResponseDto(student);
    }

    @Override
    public StudentsResponseDto getStudentsByName(String studentName) throws Exception {
        if (!studentRepository.existsByNameContaining(studentName)) throw new ResourceNotFoundException("No student found with name " + studentName);

        List<Student> students = studentRepository.findByNameContaining(studentName);
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();

        for (Student student: students) {
            studentResponseDtos.add(StudentTransformer.studentEntityToStudentResponseDto(student));
        }

        return StudentTransformer.toStudentsResponseDto(studentResponseDtos);
    }

    @Override
    public StudentsResponseDto getAllStudents() throws Exception {
        List<Student> students = studentRepository.findAll();

        if (students == null) throw new ResourceNotFoundException("No students found");

        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();

        for (Student student: students) {
            studentResponseDtos.add(StudentTransformer.studentEntityToStudentResponseDto(student));
        }

        return StudentTransformer.toStudentsResponseDto(studentResponseDtos);
    }

    @Override
    public StudentsResponseDto getStudentsByAge(Integer age) throws Exception {
        List<Student> students = studentRepository.findAllByAge(age);

        if (students == null) throw new ResourceNotFoundException("No students found");

        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();

        for (Student student: students) {
            studentResponseDtos.add(StudentTransformer.studentEntityToStudentResponseDto(student));
        }

        return StudentTransformer.toStudentsResponseDto(studentResponseDtos);
    }

    @Override
    public StudentResponseDto updateStudent(Integer studentId, StudentEntryDto studentEntryDto) throws Exception {
        Student student = studentRepository.findById(studentId).get();

        if (!student.getEmail().equals(studentEntryDto.getEmail()) && studentRepository.existsByEmail(studentEntryDto.getEmail())) throw new EmailAlreadyExistsException("Email already exists");

        student.setName(studentEntryDto.getName());
        student.setAge(studentEntryDto.getAge());
        student.setEmail(studentEntryDto.getEmail());
        student.setSchool(studentEntryDto.getSchool());

        Student savedStudent = studentRepository.save(student);

        return StudentTransformer.studentEntityToStudentResponseDto(savedStudent);
    }
}
