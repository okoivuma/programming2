
package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.List;

public class AssistantTeacher extends Employee implements Teacher, Payment {

    private List<DesignatedCourse> courses = new ArrayList<>();

    public AssistantTeacher(String lname, String fname){
        super(lname,fname);
    }

    public String getEmployeeIdString(){
        return "OY_ASSISTANT_";
    }

    public String getCourses(){
        String coursesString = "";
        for (DesignatedCourse course : courses){
            coursesString += course + "\n";
        }
        return coursesString;
    }

    public void setCourses(List<DesignatedCourse> courses) {
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
                "        Assistant for courses:\n" +
                "       " + getCourses();
    }
}
