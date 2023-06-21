package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.Card;
import com.example.library.mgt.system.models.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private  StudentRepository studentRepository;

    Student student1;
    Student student2;
    Student student3;

    @BeforeEach
    void setUp() {
        student1 = Student.builder()
                .name("timothy")
                .age(24)
                .school("must")
                .email("timo@gmail.com")
                .card(Card.builder().student(student1).build())
                .build();

        student2 = Student.builder()
                .name("timothy")
                .age(26)
                .school("kyu")
                .email("timothy@gmail.com")
                .card(Card.builder().student(student2).build())
                .build();

        student3 = Student.builder()
                .name("john")
                .age(20)
                .school("kiu")
                .email("john@gmail.com")
                .card(Card.builder().student(student1).build())
                .build();

        studentRepository.saveAll(List.of(student1, student2, student3));
    }

    @AfterEach
    void tearDown() {
        student1 = null;
        student2 = null;
        student3 = null;
        studentRepository.deleteAll();
    }

    @Test
    void testFindByEmail_Found() {
        Student studentFromDb = studentRepository.findByEmail("timo@gmail.com");
        assertThat(studentFromDb.getEmail()).isEqualTo(student1.getEmail());
        assertThat(studentFromDb.getName()).isEqualTo(student1.getName());
        assertThat(studentFromDb.getAge()).isEqualTo(student1.getAge());
        assertThat(studentFromDb.getSchool()).isEqualTo(student1.getSchool());
    }

    @Test
    void testFindByEmail_NotFound() {
        Student studentFromDb = studentRepository.findByEmail("timo@gmail.com2");
        assertThat(studentFromDb == null).isTrue();
    }

    @Test
    void testFindAllByName_Found() {
        List<Student> studentList = studentRepository.findAllByName("timothy");
        assertThat(studentList).containsExactlyInAnyOrder(student1, student2);
    }

    @Test
    void testFindAllByName_NotFound() {
        List<Student> studentList = studentRepository.findAllByName("timothy2");
        assertThat(studentList.isEmpty()).isTrue();
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