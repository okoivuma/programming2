package dev.m3s.programming2.homework3;
//package kotiteht3;

import java.util.ArrayList;
import java.util.List;

public class ResponsibleTeacher extends Employee implements Teacher, Payment{

    private List<DesignatedCourse> courses = new ArrayList<>();

    public ResponsibleTeacher(String lname, String fname){
        super(lname, fname);
    }

    public String getEmployeeIdString(){
        return "OY_TEACHER_";
    }

    public String getCourses(){
        String coursesString = "";
        for (DesignatedCourse course : courses){
            if (course.isResponsible()){
                coursesString += "Responsible teacher: " + course + "\n";
            } else {
                coursesString += "Teacher: " + course + "\n";
            }
        }
        return coursesString;
    }

    public void setCourses(List<DesignatedCourse> courses){
        if (courses != null){
            this.courses = courses;
        }
    }

    @Override
    public String toString() {
        return  "Teacher id: " + getIdString() + "\n" +
                "        First name: " + getFirstName() + ", Last name: " + getLastName() + "\n" +
                "        Birthdate: " + getBirthDate() + "\n" +
                "        Salary: " + String.format("%.2f", calculatePayment()) + "\n" +
                "        Teacher for courses: \n" +
                "        " + getCourses();

    }
}
