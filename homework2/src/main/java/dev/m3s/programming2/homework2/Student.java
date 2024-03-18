package dev.m3s.programming2.homework2;
//package kotiteht2;
import kotiteht2.ConstantValues;

import java.util.Random;

public class Student {

    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private int id;
    private  int startYear = ConstantValues.CURRENT_YEAR;
    private int graduationYear;
    private int degreeCount = 3;
    // degree 0 -> bachelor
    // degreee 1 -> master
    // degree 2 -> reserved for doctoral studies
    private Degree [] degrees = new Degree[degreeCount];
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    public Student(){
        id = getRandomId();
        for (int i = 0; i < degreeCount; i++){
            degrees[i] = new Degree();
        }
    }
    public Student(String lname, String fname){
        this();
        setFirstName(fname);
        setLastName(lname);
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        if (firstName != null && !firstName.isEmpty()){
            this.firstName = firstName;
        }
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        if (lastName != null && !lastName.isEmpty()){
            this.lastName = lastName;
        }
    }

    public int getId(){
        return id;
    }

    public void setId(final int id) {
        if (id >= ConstantValues.MIN_ID && id <= ConstantValues.MAX_ID){
            this.id = id;
        }else {
            this.id = getRandomId();
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
        
        if (!canGraduate() && graduationYear > 2000) {
            return "Check the amount of required credits";
        } else if (graduationYear < startYear || graduationYear > ConstantValues.CURRENT_YEAR || graduationYear < 2000){
            return "Check graduation year";
        } else {
            this.graduationYear = graduationYear;
            return "Ok";
        }
    }


    public void setDegreeTitle(final int i, String dName){
        if (0 <= i && i < degreeCount && degrees[i] != null){
            degrees[i].setDegreeTitle(dName);
        }
    }

    public boolean addCourse(final int i, StudentCourse course){
        if (0 < i && i < degreeCount && course != null){
            degrees[i].addStudentCourse(course);
            return true;
        } else {
            return false;
        }
    }

    public int addCourses(final int i, StudentCourse [] courses){
        int addedCourses = 0;
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
        if (0 < i && i < degreeCount){
            degrees[i].setTitleOfThesis(title);
        }
    }

    public String getBirthDate(){
        return birthDate;
    }


    public String setBirthDate(String personId) {
        if (personId == null || personId.isEmpty()) {
            return "No change";
        } else if (personId.length() != 11 || (personId.charAt(6) != '+' && personId.charAt(6) != 'A' && personId.charAt(6) != '-')) {
            return "No change";
        }

        PersonID student = new PersonID();
        String result = student.setPersonId(personId);
        if (result.equals("Ok")){
            birthDate = student.getBirthDate();
            return birthDate;
        } else {
            return "No change";
        }
    }



    public boolean hasGraduated() {
        return (graduationYear >= startYear && graduationYear <= ConstantValues.CURRENT_YEAR);
    }

    private boolean canGraduate(){
        if (degrees[0].getCredits() < ConstantValues.BACHELOR_CREDITS ||
                degrees[1].getCredits() < ConstantValues.MASTER_CREDITS){
            return false;
        } else if (degrees[0].getTitleOfThesis().equals(ConstantValues.NO_TITLE) ||
                degrees[1].getTitleOfThesis().equals(ConstantValues.NO_TITLE)){
            return false;
        } else {
            return true;
        }
    }

    public int getStudyYears(){
        if (hasGraduated()){
            return graduationYear - startYear;
        } else {
            return ConstantValues.CURRENT_YEAR - startYear;
        }
    }
    private int getRandomId(){
        Random ran = new Random();
        id = ran.nextInt(ConstantValues.MAX_ID) + ConstantValues.MIN_ID;
        return id;
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
        if (degrees[0].getCredits() >= ConstantValues.BACHELOR_CREDITS){
            return "Total bachelor credits completed (" + degrees[0].getCredits() + "/" + ConstantValues.BACHELOR_CREDITS + ")";
        } else {
            double missing = ConstantValues.BACHELOR_CREDITS - degrees[0].getCredits();
            return "Missing bachelor credits " + missing + "(" + degrees[0].getCredits() + "/" + ConstantValues.BACHELOR_CREDITS + ")";
        }
    }

    private String masterStatus(){
        if (degrees[1].getCredits() >= ConstantValues.MASTER_CREDITS){
            return "Total master's credits completed (" + degrees[1].getCredits() + "/" + ConstantValues.MASTER_CREDITS + ")";
        } else {
            double missing = ConstantValues.MASTER_CREDITS - degrees[1].getCredits();
            return "Missing master's credits " + missing + "(" + degrees[1].getCredits() + "/" + ConstantValues.MASTER_CREDITS + ")";
        }
    }



    @Override
    public String toString() {
        return "Student id: " + id + "\n" +
                "     FirstName: " + firstName + ", LastName: " + lastName + "\n" +
                "     Date of birth: " + birthDate + "\n" +
                "     Status: " + status() + "\n" +
                "     Start year: " + startYear + " (Studies have lasted for " + getStudyYears() + " years)" + "\n" +
                "     Total credits: " +  totalCredits() + "\n" +
                "     Bachelor credits: " + degrees[0].getCredits() + "\n" +
                "         " + bachelorStatus() + "\n" +
                "          Title of BSc Thesis: " + degrees[0].getTitleOfThesis() +
                "     Master credits: " + degrees[1].getCredits() + "\n" +
                "         " + masterStatus() + "\n" +
                "          Title of BSc Thesis: " + degrees[1].getTitleOfThesis();
    }
}
