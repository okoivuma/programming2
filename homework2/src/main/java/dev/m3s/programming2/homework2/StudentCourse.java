package dev.m3s.programming2.homework2;
//package kotiteht2;
public class StudentCourse {

    private Course course;
    private int gradeNum;
    private int yearCompleted;

    public StudentCourse(){

    }

    public StudentCourse(Course course, final int gradeNum, final int yearCompleted){
        setCourse(course);
        setGrade(gradeNum);
        setYear(yearCompleted);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGradeNum() {
        return gradeNum;
    }

    protected void setGrade(int gradeNum){
        if (course.isNumericGrade() && checkGradeValidity(gradeNum)){
            this.gradeNum = gradeNum;
        } else if (checkGradeValidity(gradeNum)) {
            this.gradeNum = gradeNum;
        }

        if (yearCompleted == 0){
            yearCompleted = ConstantValues.CURRENT_YEAR;
        }
    }

    private boolean checkGradeValidity(final int gradeNum){
        if (course.isNumericGrade()){
            return gradeNum >= 0 && gradeNum <= 5;
        } else {
            return gradeNum == 'F' || gradeNum == 'A';
        }
    }

    public boolean isPassed(){
        if (course.isNumericGrade()){
            return gradeNum > 0 && gradeNum <= 5;
        } else {
            return gradeNum == 'A';
        }
    }

    public int getYear(){
        return yearCompleted;
    }

    public void setYear(final int year){
        if (year > 2000 && year <= ConstantValues.CURRENT_YEAR){
            yearCompleted = year;
        }
    }
    private String printGrade(){
        if (checkGradeValidity(gradeNum)) {
            if (gradeNum == 0) {
                return "Not graded";
            } else if (course.isNumericGrade()) {
                return String.valueOf(gradeNum);
            } else {
                return String.valueOf((char) gradeNum);
            }
        }
        return "";
    }

    @Override
    public String toString(){
        return course + " Year: " + yearCompleted + ", Grade: " + printGrade() + ".]";
    }
}
