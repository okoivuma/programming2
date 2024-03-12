package dev.m3s.programming2.homework2;
//package kotiteht2;
public class Degree {

    private static final int MAX_COURSES = 50;
    private int count;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private StudentCourse [] myCourses = new StudentCourse[MAX_COURSES];


    public StudentCourse [] getCourses(){
        return myCourses;
    }

    public void addStudentCourses(StudentCourse [] courses){
        if (courses != null) {
            for (StudentCourse cours : courses) {
                addStudentCourse(cours);
            }
        }
    }

    public boolean addStudentCourse(StudentCourse course){
        if (course != null && count < MAX_COURSES){
            myCourses[count] = course;
            count++;
            return true;
        } else {
            return false;
        }
    }

    public String getDegreeTitle(){
        return degreeTitle;
    }

    public void setDegreeTitle(String degreeTitle){
        if (degreeTitle != null && !degreeTitle.isEmpty()){
            this.degreeTitle = degreeTitle;
        }
    }

    public String getTitleOfThesis(){
        return titleOfThesis;
    }

    public void setTitleOfThesis(String titleOfThesis){
        if (titleOfThesis != null && !titleOfThesis.isEmpty()){
            this.titleOfThesis = titleOfThesis;
        }
    }

    public double getCreditsByBase(char base){

        double credits = 0;
        for (StudentCourse course : myCourses ){
            if (course.getCourse() != null && course.getCourse().getCourseBase() == base && isCourseCompleted(course)){
                credits += course.getCourse().getCredits();
            }
        }
        return credits;
    }

    public double getCreditsByType(final int courseType){


        double credits = 0;
        for (StudentCourse course : myCourses){
            if (course.getCourse() != null && course.getCourse().getCourseType() == courseType && isCourseCompleted(course)){
                credits += course.getCourse().getCredits();
            }
        }
        return credits;
    }

    public double getCredits(){

        double credits = 0;
        for (StudentCourse course : myCourses){
            if (isCourseCompleted(course)){
                credits += course.getCourse().getCredits();
            }
        }
        return credits;
    }

    private boolean isCourseCompleted(StudentCourse c){
        return c != null && c.getCourse() != null && c.isPassed();
    }

    public void printCourses(){
        for (StudentCourse course : myCourses){
            if (course != null){
                System.out.println(course);
            }
        }
    }

    private String coursesToString(){
        int i  = 1;
        String allCourses = null;
        for (StudentCourse course : myCourses){
            if (course != null){
                allCourses += ("       " + i + ". " +  course + "\n");
                i++;
            }
        }
        return allCourses;
    }

    public int getCount(){
        return count;
    }

    @Override
    public String toString() {
        return "Degree [Title: " + degreeTitle + " (courses: " + count + ")\n" +
                "        Thesis title: " + titleOfThesis + "\n" +
                coursesToString() + "]";
    }
}
