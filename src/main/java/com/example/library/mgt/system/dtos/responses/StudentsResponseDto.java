package com.example.library.mgt.system.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentsResponseDto {
    List<StudentResponseDto> studentResponseDtos;

    String responseMessage;

    HttpStatus responseStatusCode;
}
