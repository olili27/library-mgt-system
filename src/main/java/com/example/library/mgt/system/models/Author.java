package com.example.library.mgt.system.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    String email;

    Integer age;

    @CreationTimestamp
    LocalDate joinedAt;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Book> books = new ArrayList<>();
}
