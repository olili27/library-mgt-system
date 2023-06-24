package com.example.library.mgt.system.models;

import com.example.library.mgt.system.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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

    @ManyToOne
    @JoinColumn
    Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    List<BookItem> bookItems;

    public void decreaseNumberOfCopies() {
        setNumberOfCopies(getNumberOfCopies() - 1);;
    }

    public void increaseNumberOfCopies() {
        setNumberOfCopies(getNumberOfCopies() + 1);
    }
}
