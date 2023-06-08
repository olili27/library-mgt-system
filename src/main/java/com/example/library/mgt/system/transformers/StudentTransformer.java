package com.example.library.mgt.system.transformers;

import com.example.library.mgt.system.dtos.responses.StudentResponseDto;
import com.example.library.mgt.system.models.Student;
import lombok.experimental.UtilityClass;

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
}
