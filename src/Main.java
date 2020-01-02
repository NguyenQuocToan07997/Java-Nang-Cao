import View.StudentListView;
import model.StudentModel_lmpl;
import model.StudentsModel;

public class Main{

    public static void main(String[] args) {
        StudentsModel model = new StudentModel_lmpl();
        StudentListView studentsListView = new StudentListView(model);

    }
}
