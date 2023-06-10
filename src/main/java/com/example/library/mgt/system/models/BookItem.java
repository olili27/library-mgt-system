package com.example.library.mgt.system.models;

import com.example.library.mgt.system.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "book_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;

    @Enumerated(EnumType.STRING)
    BookStatus status;

    @ManyToOne
    @JoinColumn
    Student student;
}
