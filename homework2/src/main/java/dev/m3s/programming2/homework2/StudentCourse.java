package dev.m3s.programming2.homework2;

public class StudentCourse {

    Course course;
    int gradeNum;
    int yearCompleted;

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
        if (gradeNum >= 0 && gradeNum <= 5){
            return true;
        } else if ((char) gradeNum == 'F' || (char) gradeNum == 'A'){
            return true;
        } else {
            return false;
        }
    }

    public boolean isPassed(){
        return gradeNum != 0 && gradeNum != 'F';
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
