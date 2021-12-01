package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    private Integer id;
    private String lesson;
    private Integer audience;
    private Integer audience_id;

    public Lesson() {
        this.students = new ArrayList<>();
    }

    public Lesson(Integer id, String lesson, Integer audience) {
        this.id = id;
        this.lesson = lesson;
        this.audience = audience;
        this.students = new ArrayList<>();
    }

    public Lesson(Integer id, String lesson, Integer audience, Integer audience_id) {
        this.id = id;
        this.lesson = lesson;
        this.audience = audience;
        this.audience_id = audience_id;
        this.students = new ArrayList<>();
    }

    public Lesson(Integer id, String lesson, Integer audience, List<Student> students) {
        this.id = id;
        this.lesson = lesson;
        this.audience = audience;
        this.students = students;
    }

    public Lesson(Integer id, String lesson, Integer audience, Student student) {
        this.id = id;
        this.lesson = lesson;
        this.audience = audience;
        if (this.students == null) {
            this.students = new ArrayList<>();
        }
        this.students.add(student);

    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="student_lesson",
        joinColumns = @JoinColumn(name="lesson_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name="student_id", referencedColumnName="id")
    )
    private List<Student> students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public Integer getAudience() {

        return audience;
    }

    public void setAudience(Integer audience) {
        this.audience = audience;
    }

    public String getStudents() {
        String str = "";
        for (Student student:students) {
            str += student.getEmail() + "; ";
        }

        return str;
    }

    @JsonIgnore
    public List<Student> getStudentsObj() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Integer getAudience_id() {
        return audience_id;
    }

    public void setAudience_id(Integer audience_id) {
        this.audience_id = audience_id;
    }
}
