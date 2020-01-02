package controller;

import View.InsertStudent;
import model.Students;
import model.StudentsModel;

import javax.swing.*;
import java.awt.*;

public class NewStudentController_Impl implements NewStudentController {
    private Component parent;

    private StudentsModel studentsModel;
    private InsertStudent view;
    public NewStudentController_Impl(Component parent, StudentsModel studentsModel, InsertStudent view){
        this.parent = parent;
        this.view = view;
        this.studentsModel = studentsModel;
    }
    @Override
    public void newStudent() {
        int option = JOptionPane.showConfirmDialog(parent, view.getRootPanel() , "Thêm nhân viên", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(option == JOptionPane.YES_OPTION){
            if(view.getFullName() == null){
                JOptionPane.showConfirmDialog(null, "Chưa nhập tên nhân viên", "Lỗi !!!", JOptionPane.OK_OPTION);
            }else {
                String fullName = view.getFullName();
                String gender = view.getGender();
                Students student = new Students();
                student.setFullName(fullName);
                student.setGender(gender);
                studentsModel.addStudent(student);
            }
        }
    }
}
