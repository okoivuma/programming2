package dev.m3s.programming2.homework3;


import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private int id;
    private  int startYear = ConstantValues.CURRENT_YEAR;
    private int graduationYear;
    private List<Degree> degrees = new ArrayList<Degree>(3);

    public Student(String lname, String fname){
        super(lname, fname);
        id = getRandomId(ConstantValues.MIN_STUDENT_ID, ConstantValues.MAX_STUDENT_ID);
        degrees.add(new Degree());
        degrees.add(new Degree());
        degrees.add(new Degree());
    }




    public int getId(){
        return id;
    }

    public void setId(final int id) {
        if (id >= ConstantValues.MIN_STUDENT_ID && id <= ConstantValues.MAX_STUDENT_ID){
            this.id = id;
        }
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(final int startYear) {
        if (2000 < startYear && startYear <= ConstantValues.CURRENT_YEAR){
            this.startYear = startYear;
        }
    }

    public int getGraduationYear(){
        return graduationYear;
    }

    public String setGraduationYear(final int graduationYear){

        if (canGraduate()) {
            if (graduationYear > 2000){
                if (startYear <= graduationYear && graduationYear <= ConstantValues.CURRENT_YEAR){
                    this.graduationYear = graduationYear;
                    return "Ok";
                }
            } return "Check graduation year";
        }
        return "Check amount of required credits";
    }


    public void setDegreeTitle(final int i, String dName){
        if (0 <= i && i < 3){
            degrees.get(i).setDegreeTitle(dName);
        }
    }

    public boolean addCourse(final int i, StudentCourse course){
        if (0 <= i && i < 3 && course != null){
            if (degrees.get(i).getCount() < 50){
                degrees.get(i).addStudentCourse(course);
                return true;
            }
        }
        return false;
    }

    public int addCourses(final int i, List<StudentCourse> courses){
        int addedCourses = 0;
        if (courses == null){
            return 0;
        }
        for (StudentCourse course : courses){
            if (addCourse(i, course)){
                addedCourses++;
            }
        }
        return addedCourses;
    }

    public void printCourses(){
        for (Degree courses : degrees){
            courses.printCourses();
        }
    }

    public void printDegrees(){
        for (Degree courses : degrees){
            System.out.println(courses);
        }
    }

    public void setTitleOfThesis(final int i, String title){
        if (0 <= i && i < 3 && title != null){
            degrees.get(i).setTitleOfThesis(title);
        }
    }

    public boolean hasGraduated() {
        return (graduationYear >= startYear && graduationYear <= ConstantValues.CURRENT_YEAR);
    }

    private boolean canGraduate(){

        boolean bachelorCompleted = degrees.get(0).getCredits() >= ConstantValues.BACHELOR_CREDITS &&
                degrees.get(0).getCredits() >= ConstantValues.BACHELOR_MANDATORY &&
                !degrees.get(0).getTitleOfThesis().equals(ConstantValues.NO_TITLE);

        boolean masterCompleted = degrees.get(1).getCredits() >= ConstantValues.MASTER_CREDITS &&
                degrees.get(1).getCredits() >= ConstantValues.MASTER_MANDATORY &&
                !degrees.get(1).getTitleOfThesis().equals(ConstantValues.NO_TITLE);

        return bachelorCompleted && masterCompleted;
    }

    public int getStudyYears(){
        if (hasGraduated()){
            return graduationYear - startYear;
        } else {
            return ConstantValues.CURRENT_YEAR - startYear;
        }
    }

    private String status(){
        if (hasGraduated()){
            return "The student has graduated in " + graduationYear;
        } else {
            return "The student has not graduated, yet.";
        }
    }

    private double totalCredits(){
        double sumOfCredits = 0;
        for (Degree degree : degrees){
            sumOfCredits += degree.getCredits();
        }
        return sumOfCredits;
    }

    private String bachelorStatus(){
        if (degrees.get(0).getCredits() >= ConstantValues.BACHELOR_CREDITS){
            return "Total bachelor credits completed (" + degrees.get(0).getCredits() + "/" + ConstantValues.BACHELOR_CREDITS + ")";
        } else {
            double missing = ConstantValues.BACHELOR_CREDITS - degrees.get(0).getCredits();
            return "Missing bachelor credits " + missing + "(" + degrees.get(0).getCredits() + "/" + ConstantValues.BACHELOR_CREDITS + ")";
        }
    }

    private String mandatoryBachelorStatus(){
        if (degrees.get(0).getCreditsByType(ConstantValues.MANDATORY) >= ConstantValues.BACHELOR_MANDATORY ){
            return "All mandatory bachelor credits completed (" + degrees.get(0).getCreditsByType(1) + "/" + ConstantValues.BACHELOR_MANDATORY;
        } else {
            double missing = ConstantValues.BACHELOR_MANDATORY - degrees.get(0).getCreditsByType(1);
            return "Missing mandatory bachelor credits " + missing + "(" + degrees.get(0).getCreditsByType(1) + "/" + ConstantValues.BACHELOR_MANDATORY;
        }
    }

    private String masterStatus(){
        if (degrees.get(1).getCredits() >= ConstantValues.MASTER_CREDITS){
            return "Total master's credits completed (" + degrees.get(1).getCredits() + "/" + ConstantValues.MASTER_CREDITS + ")";
        } else {
            double missing = ConstantValues.MASTER_CREDITS - degrees.get(1).getCredits();
            return "Missing master's credits " + missing + "(" + degrees.get(1).getCredits() + "/" + ConstantValues.MASTER_CREDITS + ")";
        }
    }

    private String mandatoryMasterStatus(){
        if (degrees.get(1).getCreditsByType(ConstantValues.MANDATORY) >= ConstantValues.MASTER_MANDATORY ){
            return "All mandatory master credits completed (" + degrees.get(1).getCreditsByType(1) + "/" + ConstantValues.MASTER_MANDATORY;
        } else {
            double missing = ConstantValues.MASTER_MANDATORY - degrees.get(1).getCreditsByType(1);
            return "Missing mandatory master credits " + missing + "(" + degrees.get(1).getCreditsByType(1) + "/" + ConstantValues.MASTER_MANDATORY;
        }
    }


    */private double getAverage(int type){
        double gpa = 0.0;
        int degreeCount = 0;
        for (Degree degree : degrees) {
            List<Double> gpaList = degree.getGPA(type);
            if (!gpaList.isEmpty()) {
                gpa += gpaList.get(2);
                degreeCount++;
            }
        }

        if (degreeCount > 0){
            return gpa / degreeCount;
        } else {
            return 0.0;
        }
    }*/

    private String calculateTotalGPA(int type) {
        List<Double> gpaData = getGPA(type);
        double totalGPA = 0.0;


        if (!gpaData.isEmpty()) {
            double sum = gpaData.get(0);
            double count = gpaData.get(1);


            if (count != 0) {
                totalGPA = sum / count;
            }
        }

        return String.format(".2f", totalGPA);
    }

    @Override
    public String toString() {
        return "Student id: " + id + "\n" +
                "     FirstName: " + getFirstName() + ", LastName: " + getLastName() + "\n" +
                "     Date of birth: " + getBirthDate() + "\n" +
                "     Status: " + status() + "\n" +
                "     Start year: " + startYear + " (Studies have lasted for " + getStudyYears() + " years)" + "\n" +
                "     Total credits: " +  totalCredits() + " (GPA = " + getAverage(2) + ")\n" +
                "     Bachelor credits: " + degrees.get(0).getCredits() + "\n" +
                "         " + bachelorStatus() + "\n" +
                "         " + mandatoryBachelorStatus() + "\n" +
                "          GPA of Bachelor studies: " + getAverage(0) + "\n" +
                "          Title of BSc Thesis: " + degrees.get(0).getTitleOfThesis() +
                "     Master credits: " + degrees.get(1).getCredits() + "\n" +
                "         " + masterStatus() + "\n" +
                "         " + mandatoryMasterStatus() + "\n" +
                "          GPA of Master studies: " + getAverage(1) + "\n" +
                "          Title of BSc Thesis: " + degrees.get(1).getTitleOfThesis();
    }

    @Override
    String getIdString() {
        return "Student id: " + id;
    }

}
