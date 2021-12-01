package com.example.demo;

import com.example.demo.model.Audience;
import com.example.demo.model.Lesson;
import com.example.demo.model.Student;
import com.example.demo.service.AudienceRepository;
import com.example.demo.service.LessonRepository;
import com.example.demo.service.StudentRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoApplicationTests {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    AudienceRepository audienceRepository;

    @Test
    @Order(1)
    public void testCreateStudent() {
        Student student = new Student();
        student.setId(1234);
        student.setName("Anna");
        student.setEmail("anna@test.com");

        studentRepository.save(student);
        assertNotNull(studentRepository.findById(1234).get());
    }
    @Test
    @Order(2)
    public void testReadAllStudents() {
        List<Student> students = studentRepository.findAll();
        assertThat(students).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testSingleStudent() {
        Student student = studentRepository.findById(1234).get();
        assertEquals("Anna", student.getName());
    }

    @Test
    @Order(4)
    public void testUpdateStudent() {
        Student student = studentRepository.findById(1234).get();
        student.setEmail("annaTestUpdate@gmail.com");
        studentRepository.save(student);
        assertEquals("annaTestUpdate@gmail.com", studentRepository.findById(1234).get().getEmail());
    }

    @Test
    @Order(5)
    public void testDeleteStudent() {
        studentRepository.deleteById(1234);
        assertThat(studentRepository.existsById(1234)).isFalse();
    }

    @Test
    @Order(6)
    public void testCreateLesson() {
        Lesson lesson = new Lesson();
        lesson.setId(123);
        lesson.setLesson("Work");
        lesson.setAudience(401);

        lessonRepository.save(lesson);
        assertNotNull(lessonRepository.findById(123).get());
    }
    @Test
    @Order(7)
    public void testReadAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        assertThat(lessons).size().isGreaterThan(0);
    }

    @Test
    @Order(8)
    public void testSingleLesson() {
        Lesson lesson = lessonRepository.findById(123).get();
        assertEquals("Work", lesson.getLesson());
    }

    @Test
    @Order(9)
    public void testUpdateLesson() {
        Lesson lesson = lessonRepository.findById(123).get();
        lesson.setAudience(301);
        lessonRepository.save(lesson);
        assertEquals(301, lessonRepository.findById(123).get().getAudience());
    }

    @Test
    @Order(10)
    public void testDeleteLesson() {
        lessonRepository.deleteById(123);
        assertThat(studentRepository.existsById(123)).isFalse();
    }


    @Test
    @Order(11)
    public void testCreateAudience() {
        Audience audience = new Audience();
        audience.setId(12);
        audience.setAudienceNumber(777);

        audienceRepository.save(audience);
        assertNotNull(audienceRepository.findById(12).get());
    }
    @Test
    @Order(12)
    public void testReadAllAudiences() {
        List<Audience> audiencesList = audienceRepository.findAll();
        assertThat(audiencesList).size().isGreaterThan(0);
    }

    @Test
    @Order(13)
    public void testSingleAudience() {
        Audience audience = audienceRepository.findById(12).get();
        assertEquals(777, audience.getAudienceNumber());
    }

    @Test
    @Order(14)
    public void testUpdateAudience() {
        Audience audience = audienceRepository.findById(12).get();
        audience.setAudienceNumber(555);
        audienceRepository.save(audience);
        assertEquals(555, audienceRepository.findById(12).get().getAudienceNumber());
    }

    @Test
    @Order(15)
    public void testDeleteAudience() {
        audienceRepository.deleteById(12);
        assertThat(audienceRepository.existsById(12)).isFalse();
    }


}
