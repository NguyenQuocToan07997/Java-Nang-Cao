package controller;

import View.InsertStudent;
import model.Students;
import model.StudentsModel;

import javax.swing.*;
import java.awt.*;

public class StudentController_Impl implements StudentController {
    private Component parent;
    private StudentsModel studentsModel;
    private InsertStudent view;

    public StudentController_Impl(Component parent, StudentsModel model, InsertStudent view) {
        this.parent = parent;
        this.studentsModel = model;
        this.view = view;
    }

    @Override
    public void deleteStudent(int id) {
        int option = JOptionPane.showConfirmDialog(parent, "Bạn có muốn xóa nhân viên này ???", "Xóa nhân viên", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            studentsModel.deleteStudent(id);
        }
    }

    @Override
    public void updateStudent(int id) {
        int option = JOptionPane.showConfirmDialog(parent, view.getRootPanel(), "Chỉnh sử nhân viên", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            if(view.getFullName() == null){
                JOptionPane.showConfirmDialog(null, "Chưa nhập tên nhân viên", "Lỗi !!!", JOptionPane.OK_OPTION);
            }else {
                String fullName = view.getFullName();
                String birthYear = view.getGender();
                Students student = new Students();
                student.setFullName(fullName);
                student.setGender(birthYear);
                studentsModel.updateStudent(student, id);
            }
        }
    }
}
