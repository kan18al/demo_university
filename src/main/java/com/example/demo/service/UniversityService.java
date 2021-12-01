package com.example.demo.service;


import com.example.demo.model.Audience;
import com.example.demo.model.Lesson;
import com.example.demo.model.Student;

import java.util.List;

public interface UniversityService {

    /**
     * Создает нового студента
     * @param student - студент для создания
     */
    void create(Student student);
    void create(Lesson lesson);

    /**
     * Возвращает список всех имеющихся студентов
     * @return список студентов
     */
    List<Student> readAll();

    /**
     * Возвращает студента по его ID
     * @param id - ID студента
     * @return - объект студента с заданным ID
     */
    Student read(int id);

    /**
     * Обновляет студента с заданным ID,
     * в соответствии с переданным студентом
     * @param student - студент в соответсвии с которым нужно обновить данные
     * @param id - id студента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Student student, int id);

    /**
     * Удаляет клиента с заданным ID
     * @param id - id студента, которого нужно удалить
     * @return - true если студент был удален, иначе false
     */
    boolean delete(int id);

    /**
     * Возвращает список всех имеющихся аудиторий
     * @return список аудиторий
     */
    List<Audience> readAllAudiences();

    List<Lesson> readAllLessons();

    boolean updateLesson(Lesson lesson, int id);

    boolean deleteLesson(int id);

    Lesson readLesson(int id);

    Audience readAudience(int id);

    boolean updateAudience(Audience audience, int id);

    void createAudience(Audience audience);

    boolean deleteAudience(int id);
}
