package View;

import controller.NewStudentController;
import controller.NewStudentController_Impl;
import controller.StudentController;
import controller.StudentController_Impl;
import model.Students;
import model.StudentsModel;
import model.TableObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class StudentListView extends JFrame implements TableObserver {
    private JPanel panel1;
    private JPanel rootPanel;
    private JTable studentTable;

    private StudentTableModel studentTableModel;

    private JButton addButton;

    private StudentsModel studentsModel;

    private List<TableObserver> tableObserver = new Vector<>();

    private List<Students> students = new Vector<>();

    private JButton deleteButton;
    private JButton updateButton;

    String[] Gender = {"Nam", "Nữ"};

    public StudentListView(StudentsModel model){
        this.studentsModel = model;
        this.studentsModel.registerObserver(this);

        setTitle("Student Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(800,600));
        setLocation(500,200);
        pack();
        setVisible(true);

        studentTableModel = new StudentTableModel();
        studentTable.setModel(studentTableModel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddStudent(e);
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDeleteStudent(e);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onUpdateStudent(e);
            }
        });

        List<Students> students = this.studentsModel.getAllStudent();
        studentTableModel.updateStudents(students);

    }

    private void onAddStudent(ActionEvent e){
        NewStudentController controller = new NewStudentController_Impl(this,studentsModel,new InsertStudent());
        controller.newStudent();
    }

    private void onDeleteStudent(ActionEvent e){
        if (studentTable.getSelectedRow() != -1) {
            StudentController studentController = new StudentController_Impl(this, studentsModel, new InsertStudent());
            studentController.deleteStudent((Integer) studentTableModel.getValueAt(studentTable.getSelectedRow(), 0));
            System.out.println(studentTable.getSelectedRow());
        }else {
            JOptionPane.showConfirmDialog(null, "Chưa chọn bất kỳ nhân viên nào", "Xóa nhân viên", JOptionPane.OK_OPTION);
        }
    }

    private void onUpdateStudent(ActionEvent e){
        if (studentTable.getSelectedRow() != -1) {
            StudentController studentController = new StudentController_Impl(this, studentsModel, new InsertStudent());
            studentController.updateStudent((Integer) studentTableModel.getValueAt(studentTable.getSelectedRow(), 0));
        }else {
            JOptionPane.showConfirmDialog(null, "Chưa chọn bất kỳ nhân viên nào", "Xóa nhân viên", JOptionPane.OK_OPTION);
        }
    }

    public JPanel getRootPanel(){
        return rootPanel;
    }


    @Override
    public void updateTable(List<Students> students) {
        studentTableModel.updateStudents(students);
    }




}
