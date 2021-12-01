package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "audiences")
public class Audience {
    @Id
    private Integer id;
    private Integer audienceNumber;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "audience_id")
    private List<Lesson> lessons;

    public Audience() {
        this.lessons = new ArrayList<>();
    }

    public Audience(Integer id, Integer audienceNumber, List<Lesson> lessons) {
        this.id = id;
        this.audienceNumber = audienceNumber;
        this.lessons = lessons;
    }

    public Audience(Integer id, Integer audienceNumber, Lesson lesson) {
        this.id = id;
        this.audienceNumber = audienceNumber;
        if (this.lessons == null) {
            this.lessons = new ArrayList<>();
        }
        this.lessons.add(lesson);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAudienceNumber() {
        return audienceNumber;
    }

    public void setAudienceNumber(Integer audienceNumber) {
        this.audienceNumber = audienceNumber;
    }

    public String getLessons() {

        String str = "";
        for (Lesson lesson:lessons) {
            str += lesson.getLesson() + "; ";
        }

        return str;
    }

    @JsonIgnore
    public List<Lesson> getLessonsObj() {
        return this.lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
