package com.example.library.mgt.system.dtos.responses;

import com.example.library.mgt.system.models.Book;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorResponseDto {

    String authorName;

    String authorEmail;

    Integer authorAge;

    List<Book> books;


    String responseMessage;

    HttpStatus responseStatusCode;
}
