package model;

import java.util.List;
import java.util.Vector;

public class StudentModel_lmpl implements StudentsModel {

    private List<TableObserver> tableObserver = new Vector<>();

    @Override
    public List<Students> getAllStudent() {
        StudentDAO dao = new StudentDAO_Impl();

        return dao.getAllStudent();
    }

    @Override
    public void addStudent(Students students) {
        StudentDAO dao = new StudentDAO_Impl();
        dao.insertStudent(students);
        notifyObserver();
    }

    @Override
    public void deleteStudent(int id) {
        StudentDAO dao = new StudentDAO_Impl();
        dao.delete(id);
        notifyObserver();
    }

    @Override
    public void updateStudent(Students students, int id) {
        StudentDAO dao = new StudentDAO_Impl();
        dao.update(students,id);
        notifyObserver();
    }

    @Override
    public void registerObserver(TableObserver observer) {
        if(!tableObserver.contains(observer)){
            tableObserver.add(observer);
        }
    }

    @Override
    public void un_registerObserver(TableObserver observer) {
        tableObserver.remove(observer);
    }

    private void notifyObserver(){
        List<Students> students = getAllStudent();
        for (TableObserver observer: tableObserver){
            observer.updateTable(students);
        }
    }
}
