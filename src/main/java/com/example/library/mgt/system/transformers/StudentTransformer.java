package com.example.library.mgt.system.transformers;

import com.example.library.mgt.system.dtos.entries.StudentEntryDto;
import com.example.library.mgt.system.dtos.responses.StudentResponseDto;
import com.example.library.mgt.system.dtos.responses.StudentsResponseDto;
import com.example.library.mgt.system.models.Student;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class StudentTransformer {

    public StudentResponseDto studentEntityToStudentResponseDto(Student student) {
        return StudentResponseDto.builder()
                .studentName(student.getName())
                .studentSchool(student.getSchool())
                .studentEmail(student.getEmail())
                .studentAge(student.getAge())
                .build();
    }

    public StudentsResponseDto toStudentsResponseDto(List<StudentResponseDto> studentResponseDtos) {
        return StudentsResponseDto.builder()
                .studentResponseDtos(studentResponseDtos)
                .build();
    }

    public Student studentEntryDtoToStudentEntry(StudentEntryDto studentEntryDto) {
        return Student.builder()
                .name(studentEntryDto.getName())
                .email(studentEntryDto.getEmail())
                .age(studentEntryDto.getAge())
                .school(studentEntryDto.getSchool())
                .build();
    }
}
