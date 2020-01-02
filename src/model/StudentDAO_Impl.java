package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

public class StudentDAO_Impl implements StudentDAO {

    public StudentDAO_Impl(){
        Database db = new Database();
        String SQL_CREATE_STUDENT_TABLE = "CREATE TABLE IF NOT EXISTS StaffTable (\n"
                + "    ID integer PRIMARY KEY,\n"
                + "    FullName text NOT NULL,\n"
                + "    Gender text\n"
                + ");";
        try {
            Statement statement = db.getConnection().createStatement();
            statement.execute(SQL_CREATE_STUDENT_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();

    }
    @Override
    public void insertStudent(Students students) {
        Database db = new Database();
        final String SQL_CREATE_STUDENT = "INSERT INTO StaffTable(FullName, Gender)" +
                "VALUES(?,?)";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQL_CREATE_STUDENT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, students.getFullName());
            ps.setString(2, students.getGender());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                students.setId(id);
                System.out.println("Inserted id: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }

    @Override
    public Students getStudentById(int id) {
        Students students = null;
        Database db = new Database();
        final String SQL_SELECT_STUDENT_BY_ID = "SELECT * FROM StaffTable WHERE ID=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQL_SELECT_STUDENT_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int student_id = rs.getInt(1);
                String fullName = rs.getString(2);
                String gender = rs.getString(3);
                students = new Students(student_id, fullName, gender);
                System.out.println("ID: " + student_id + ", Full Name: " + fullName + ", Gender : " + gender);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return students;
    }

    @Override
    public List<Students> getAllStudent() {
        List<Students> students = new Vector<>();

        Database db = new Database();
        final String SQL_SELECT_ALL_STUDENT = "SELECT * FROM StaffTable";
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_STUDENT);
            while (rs.next()) {
                int id = rs.getInt(1);
                String fullName = rs.getString(2);
                String gender = rs.getString(3);
                Students student = new Students(id, fullName, gender);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void update(Students students, int id) {
        Database db = new Database();
        final String SQL_UPDATE_STUDENT_BY_ID = "UPDATE StaffTable SET FullName = ?, Gender = ? WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = db.getConnection().prepareStatement(SQL_UPDATE_STUDENT_BY_ID);
            ps.setString(1, students.getFullName());
            ps.setString(2, students.getGender());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }

    @Override
    public void delete(int id) {
        Database db = new Database();
        final String SQL_DELETE_STUDENT_BY_ID = "DELETE FROM StaffTable WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = db.getConnection().prepareStatement(SQL_DELETE_STUDENT_BY_ID);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }
}
