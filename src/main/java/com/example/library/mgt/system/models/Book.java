package com.example.library.mgt.system.models;

import com.example.library.mgt.system.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;

    String category;

    @Enumerated(EnumType.STRING)
    BookStatus status;

    Integer numberOfCopies;
}
