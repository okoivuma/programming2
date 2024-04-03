package dev.m3s.programming2.homework3;
//package kotiteht3;

public class DesignatedCourse {

    private Course course;
    private boolean responsible;
    private int year;

    public DesignatedCourse(){

    }

    public DesignatedCourse(Course course, boolean resp, int year){
        setCourse(course);
        setResponsible(resp);
        setYear(year);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        if (course != null){
            this.course = course;
        }
    }

    public boolean isResponsible(){
        if (responsible){
            return true;
        } else {
            return false;
        }
    }

    public void setResponsible(boolean responsible) {
        this.responsible = responsible;
    }

    public boolean getResponsible(){
        return responsible;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year) {
        if (2000 <= year && year <= (ConstantValues.CURRENT_YEAR + 1)){
            this.year = year;
        }
    }

    @Override
    public String toString() {
        return  "[course=" + course + ", year=" + year + ']';
    }
}
