package com.example.library.mgt.system.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    String email;

    Integer age;

    String school;

    @CreationTimestamp
    LocalDate joinedAt;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    Card card;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Transaction> transactions;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<BookItem> bookItems;
}
