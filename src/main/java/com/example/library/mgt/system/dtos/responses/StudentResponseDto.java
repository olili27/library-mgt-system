package com.example.library.mgt.system.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponseDto {
    String studentName;

    Integer studentAge;

    String studentEmail;

    String studentSchool;

    String responseMessage;

    HttpStatus responseStatusCode;
}
