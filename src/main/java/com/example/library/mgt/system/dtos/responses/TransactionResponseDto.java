package com.example.library.mgt.system.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TransactionResponseDto {
    String transactionNumber;

    String bookTitle;

    String studentName;

    String studentEmail;

    HttpStatus responseStatusCode;

    String responseMessage;
}
