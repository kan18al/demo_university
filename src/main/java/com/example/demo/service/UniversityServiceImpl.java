package com.example.demo.service;

import com.example.demo.model.Audience;
import com.example.demo.model.Lesson;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UniversityServiceImpl implements UniversityService, CommandLineRunner {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private LessonRepository lessonRepo;

    @Autowired
    private AudienceRepository audienceRepo;

    private static final Map<Integer, Student> STUDENT_REPOSITORY_MAP = new HashMap<>();
    private static final Map<Integer, Lesson> LESSON_REPOSITORY_MAP = new HashMap<>();
    private static final Map<Integer, Audience> AUDIENCE_REPOSITORY_MAP = new HashMap<>();

    @Override
    public void createStudent(Student student) {
        final int studentId = generateUniqueId();
        student.setId(studentId);
        studentRepo.save(student);
        STUDENT_REPOSITORY_MAP.put(studentId, student);
        fetchData();
    }

    @Override
    public void createLesson(Lesson lesson) {
        final int lessonId = generateUniqueId();
        lesson.setId(lessonId);
        lessonRepo.save(lesson);
        LESSON_REPOSITORY_MAP.put(lessonId, lesson);
        fetchData();
    }

    @Override
    public List<Student> readAllStudent() {
        return new ArrayList<>(STUDENT_REPOSITORY_MAP.values());
    }

    @Override
    public Student readStudent(int id) {
        return STUDENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean updateStudent(Student student, int id) {
        if (STUDENT_REPOSITORY_MAP.containsKey(id)) {
            List<Lesson> lessons = new ArrayList<>();
            student.setId(id);
            lessons.addAll(STUDENT_REPOSITORY_MAP.get(id).getLessonsObj());
            student.setLessons(lessons);
            STUDENT_REPOSITORY_MAP.put(id, student);
            studentRepo.save(student);
            fetchData();
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        studentRepo.delete(STUDENT_REPOSITORY_MAP.get(id));
        fetchData();
        return STUDENT_REPOSITORY_MAP.remove(id) != null;
    }

    @Override
    public List<Audience> readAllAudiences() {
        return new ArrayList<>(AUDIENCE_REPOSITORY_MAP.values());
    }

    @Override
    public List<Lesson> readAllLessons() {
        return new ArrayList<>(LESSON_REPOSITORY_MAP.values());
    }

    @Override
    public boolean updateLesson(Lesson lesson, int id) {
        if (LESSON_REPOSITORY_MAP.containsKey(id)) {
            List<Student> students = new ArrayList<>();
            lesson.setId(id);
            students.addAll(LESSON_REPOSITORY_MAP.get(id).getStudentsObj());
            lesson.setStudents(students);
            LESSON_REPOSITORY_MAP.put(id, lesson);
            lessonRepo.save(lesson);
            fetchData();
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteLesson(int id) {
        lessonRepo.delete(LESSON_REPOSITORY_MAP.get(id));
        fetchData();
        return LESSON_REPOSITORY_MAP.remove(id) != null;
    }

    @Override
    public Lesson readLesson(int id) {
        return LESSON_REPOSITORY_MAP.get(id);
    }

    @Override
    public Audience readAudience(int id) {
        return AUDIENCE_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean updateAudience(Audience audience, int id) {
        if (AUDIENCE_REPOSITORY_MAP.containsKey(id)) {
            List<Lesson> lessons = new ArrayList<>();
            audience.setId(id);
            lessons.addAll(AUDIENCE_REPOSITORY_MAP.get(id).getLessonsObj());
            audience.setLessons(lessons);
            AUDIENCE_REPOSITORY_MAP.put(id, audience);
            audienceRepo.save(audience);
            fetchData();
            return true;
        }

        return false;
    }

    @Override
    public void createAudience(Audience audience) {
        final int audienceId = generateUniqueId();
        audience.setId(audienceId);
        audienceRepo.save(audience);
        AUDIENCE_REPOSITORY_MAP.put(audienceId, audience);
        fetchData();
    }

    @Override
    public boolean deleteAudience(int id) {
        audienceRepo.delete(AUDIENCE_REPOSITORY_MAP.get(id));
        fetchData();
        return AUDIENCE_REPOSITORY_MAP.remove(id) != null;
    }

    @Override
    public void run(String... args) throws Exception {
        createData();
        fetchData();

    }

    private void fetchData() {
        List<Student> studentList = studentRepo.findAll();
        List<Lesson> lessonList = lessonRepo.findAll();
        List<Audience> audienceList = audienceRepo.findAll();

        for (Lesson lesson:lessonList) {
            LESSON_REPOSITORY_MAP.put(lesson.getId(), lesson);
        }

        for (Student student:studentList) {
            STUDENT_REPOSITORY_MAP.put(student.getId(), student);
        }

        for (Audience audience:audienceList) {
            AUDIENCE_REPOSITORY_MAP.put(audience.getId(), audience);
        }
    }

    private void createData() {
        List<Student> listStud = new ArrayList<>();
        List<Lesson> listLess = new ArrayList<>();
        List<Audience> listAud = new ArrayList<>();

        Student student1 = new Student(generateUniqueId(), "Olya", "olya@gmail.com");
        Student student2 = new Student(generateUniqueId(), "Vasya", "vasya@gmail.com");
        Student student3 = new Student(generateUniqueId(), "Maxim", "maxim@gmail.com");

        listStud.add(student1);
        listStud.add(student2);
        listStud.add(student3);

        Lesson geography = new Lesson(generateUniqueId(), "Geography", 301, listStud);
        Lesson history = new Lesson(generateUniqueId(), "History", 301, listStud);
        Lesson algebra = new Lesson(generateUniqueId(), "Algebra", 301, student1);

        listLess.add(geography);
        listLess.add(history);
        listLess.add(algebra);

        Audience num301 = new Audience(generateUniqueId(), 301, listLess);

        listAud.add(num301);

        for (Student student:listStud) {
            STUDENT_REPOSITORY_MAP.put(student.getId(), student);
        }
        for (Lesson lesson:listLess) {
            LESSON_REPOSITORY_MAP.put(lesson.getId(), lesson);
        }
        for (Audience audience:listAud) {
            AUDIENCE_REPOSITORY_MAP.put(audience.getId(), audience);
        }

        studentRepo.saveAll(listStud);
        lessonRepo.saveAll(listLess);
        audienceRepo.saveAll(listAud);
    }

    public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }
}
