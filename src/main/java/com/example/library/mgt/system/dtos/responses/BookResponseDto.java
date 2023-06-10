package com.example.library.mgt.system.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponseDto {

    String bookTitle;

    String category;

    String authorName;

    Integer numberOfCopies;

    String bookStatus;

    String responseMessage;

    HttpStatus responseStatusCode;
}
