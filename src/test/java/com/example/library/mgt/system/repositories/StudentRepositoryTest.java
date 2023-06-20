package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.Card;
import com.example.library.mgt.system.models.Student;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private  StudentRepository studentRepository;

    Student student;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .name("timothy")
                .age(24)
                .school("must")
                .email("timo@gmail.com")
                .card(Card.builder().student(student).build())
                .build();

        studentRepository.save(student);
    }

    @AfterEach
    void tearDown() {
        student = null;
        studentRepository.deleteAll();
    }

    @Test
    void testFindByEmail_Found() {
        Student studentFromDb = studentRepository.findByEmail("timo@gmail.com");
        assertThat(studentFromDb.getEmail()).isEqualTo(student.getEmail());
        assertThat(studentFromDb.getName()).isEqualTo(student.getName());
        assertThat(studentFromDb.getAge()).isEqualTo(student.getAge());
        assertThat(studentFromDb.getSchool()).isEqualTo(student.getSchool());
    }

    @Test
    void testFindByEmail_NotFound() {
        Student studentFromDb = studentRepository.findByEmail("timo@gmail.com");

    }

    @Test
    void testFindAllByName() {
    }

    @Test
    void existsByEmail() {
    }

    @Test
    void existsByName() {
    }

    @Test
    void findAllByAge() {
    }
}