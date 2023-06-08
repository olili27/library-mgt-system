package com.example.library.mgt.system.dtos.responses;

import com.example.library.mgt.system.enums.BookStatus;
import com.example.library.mgt.system.enums.TransactionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseDto {

    String authorEmail;

    Integer authorAge;

    String authorName;

    String bookName;

    BookStatus bookStatus;

    TransactionStatus transactionStatus;

    String responseMessage;

    HttpStatus responseStatusCode;

    Integer numberOfCopies;
}
