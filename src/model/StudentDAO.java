package model;

import java.util.List;

public interface StudentDAO {
    //Create
    void insertStudent(Students students);

    //Get by ID
    Students getStudentById(int id);

    //Get All
    List<Students> getAllStudent();

    void update(Students students, int id);

    void delete(int id);


}
