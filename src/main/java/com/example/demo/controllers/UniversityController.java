package com.example.demo.controllers;

import com.example.demo.model.Audience;
import com.example.demo.model.Lesson;
import com.example.demo.model.Student;
import com.example.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> read() {
        final List<Student> students = universityService.readAll();

        return students != null &&  !students.isEmpty()
                ? new ResponseEntity<>(students, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity<Student> read(@PathVariable(name = "id") int id) {
        final Student student = universityService.read(id);

        return student != null
                ? new ResponseEntity<>(student, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/audiences")
    public ResponseEntity<List<Audience>> readAllAudiences() {
        final List<Audience> audiences = universityService.readAllAudiences();

        return audiences != null &&  !audiences.isEmpty()
                ? new ResponseEntity<>(audiences, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/audiences/{id}")
    public ResponseEntity<Audience> readAudience(@PathVariable(name = "id") int id) {
        final Audience audience = universityService.readAudience(id);

        return audience != null
                ? new ResponseEntity<>(audience, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/audiences/{id}")
    public ResponseEntity<?> updateAudience(@PathVariable(name = "id") int id, @RequestBody Audience audience) {
        final boolean updateAudience = universityService.updateAudience(audience, id);

        return updateAudience
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/audiences")
    public ResponseEntity<?> createAudience(@RequestBody Audience audience) {
        universityService.createAudience(audience);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/audiences/{id}")
    public ResponseEntity<?> deleteAudience(@PathVariable(name = "id") int id) {
        final boolean deleted = universityService.deleteAudience(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/lessons")
    public ResponseEntity<List<Lesson>> readAllLessons() {
        final List<Lesson> lessons = universityService.readAllLessons();

        return lessons != null &&  !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/lessons/{id}")
    public ResponseEntity<Lesson> readLesson(@PathVariable(name = "id") int id) {
        final Lesson lesson = universityService.readLesson(id);

        return lesson != null
                ? new ResponseEntity<>(lesson, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/students")
    public ResponseEntity<?> create(@RequestBody Student student) {
        universityService.create(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/students/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Student student) {
        final boolean updated = universityService.update(student, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/lessons")
    public ResponseEntity<?> createLesson(@RequestBody Lesson lesson) {
        universityService.create(lesson);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/lessons/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable(name = "id") int id, @RequestBody Lesson lesson) {
        final boolean updatedLesson = universityService.updateLesson(lesson, id);

        return updatedLesson
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/students/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = universityService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/lessons/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable(name = "id") int id) {
        final boolean deleted = universityService.deleteLesson(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
