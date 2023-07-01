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
    Student student4;

    @BeforeEach
    void setUp() {
        student1 = Student.builder()
                .name("john")
                .age(20)
                .school("kiu")
                .email("john@gmail.com")
                .card(Card.builder().student(student1).build())
                .build();

        student2 = Student.builder()
                .name("timothy")
                .age(24)
                .school("must")
                .email("timo@gmail.com")
                .card(Card.builder().student(student2).build())
                .build();

        student3 = Student.builder()
                .name("timothy")
                .age(26)
                .school("kyu")
                .email("timothy@gmail.com")
                .card(Card.builder().student(student3).build())
                .build();

        student4 = Student.builder()
                .name("alextimothy")
                .age(26)
                .school("ktc")
                .email("alextimothy@gmail.com")
                .card(Card.builder().student(student4).build())
                .build();

        studentRepository.saveAll(List.of(student1, student2, student3, student4));
    }

    @AfterEach
    void tearDown() {
        student1 = null;
        student2 = null;
        student3 = null;
        student4 = null;
        studentRepository.deleteAll();
    }

    @Test
    void testFindByEmail_Found() {
        Student studentFromDb = studentRepository.findByEmail("alextimothy@gmail.com");
        assertThat(studentFromDb.getEmail()).isEqualTo(student4.getEmail());
        assertThat(studentFromDb.getName()).isEqualTo(student4.getName());
        assertThat(studentFromDb.getAge()).isEqualTo(student4.getAge());
        assertThat(studentFromDb.getSchool()).isEqualTo(student4.getSchool());
    }

    @Test
    void testFindByEmail_NotFound() {
        Student studentFromDb = studentRepository.findByEmail("timo@gmail.com2");
        assertThat(studentFromDb == null).isTrue();
    }

    @Test
    void testFindAllByName_Found() {
        List<Student> studentList = studentRepository.findByNameContaining("timothy");
        assertThat(studentList).containsExactlyInAnyOrder(student2, student3, student4);
    }

    @Test
    void testFindAllByName_NotFound() {
        List<Student> studentList = studentRepository.findAllByName("timothy2");
        assertThat(studentList.isEmpty()).isTrue();
    }

    @Test
    void existsByEmail_Found() {
        boolean existsByEmail = studentRepository.existsByEmail("timo@gmail.com");
        assertThat(existsByEmail).isTrue();
    }

    @Test
    void existsByEmail_NotFound() {
        boolean existsByEmail = studentRepository.existsByEmail("timoo@gmail.com");
        assertThat(existsByEmail).isFalse();
    }

    @Test
    void existsByName_Found() {
        boolean existsByName = studentRepository.existsByName("timothy");
        assertThat(existsByName).isTrue();
    }

    @Test
    void existsByName_NotFound() {
        boolean existsByName = studentRepository.existsByName("timothyo");
        assertThat(existsByName).isFalse();
    }

    @Test
    void findAllByAge_Found() {
        List<Student> students = studentRepository.findAllByAge(24);
        assertThat(students.size() > 0).isTrue();
    }

    @Test
    void findAllByAge_NotFound() {
        List<Student> students = studentRepository.findAllByAge(28);
        assertThat(students.isEmpty()).isTrue();
    }
}