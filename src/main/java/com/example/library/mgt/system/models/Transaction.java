package com.example.library.mgt.system.models;

import com.example.library.mgt.system.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.awt.print.Book;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String transactionNumber;

    @Enumerated(EnumType.STRING)
    TransactionStatus status;

    @CreationTimestamp
    LocalDate happenedAt;

    @ManyToOne
    @JoinColumn
    Student student;

    @ManyToOne
    @JoinColumn
    BookItem bookItem;
}
