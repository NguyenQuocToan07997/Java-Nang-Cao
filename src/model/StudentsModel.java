package model;

import java.util.List;

public interface StudentsModel {
    List<Students> getAllStudent();

    void addStudent(Students students);

    void deleteStudent(int id);

    void updateStudent(Students students, int id);

    void registerObserver(TableObserver observer);

    void un_registerObserver(TableObserver observer);
}
