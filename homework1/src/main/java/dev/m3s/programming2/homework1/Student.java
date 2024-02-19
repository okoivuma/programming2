package dev.m3s.programming2.homework1;

import static kotiteht1.ConstantValues.*;

import java.util.Random;

public class Student {

    private String firstName = NO_NAME;
    private String lastName = NO_NAME;
    private int id;
    private double bachelorCredits;
    private double masterCredits;
    private String titleOfMasterThesis = NO_TITLE;
    private String titleOfBachelorThesis = NO_TITLE;
    private  int startYear = CURRENT_YEAR;
    private int graduationYear;
    private String birthDate = NO_BIRTHDATE;


    public Student(){
        id = getRandomId();
    }

    public Student(String lname, String fname){
        setFirstName(fname);
        setLastName(lname);
        id = getRandomId();
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
        if (id >= MIN_ID && id <= MAX_ID){
            this.id = id;
        }else {
            this.id = getRandomId();
        }
    }

    public double getBachelorCredits(){
        return bachelorCredits;
    }

    public void setBachelorCredits(final double bachelorCredits){
        if (bachelorCredits >= MIN_CREDITS && bachelorCredits <= MAX_CREDITS){
            this.bachelorCredits = bachelorCredits;
        }
    }

    public double getMasterCredits() {
        return masterCredits;
    }

    public void setMasterCredits(final double masterCredits){
        if (masterCredits >= MIN_CREDITS && masterCredits <= MAX_CREDITS){
            this.masterCredits = masterCredits;
        }
    }

    public String getTitleOfMasterThesis() {
        return titleOfMasterThesis;
    }

    public void setTitleOfMasterThesis(String title){
        if (title != null && !title.isEmpty()){
            titleOfMasterThesis = title;
        }
    }

    public String getTitleOfBachelorThesis() {
        return titleOfBachelorThesis;
    }

    public void setTitleOfBachelorThesis(String title) {
        if (title != null && !title.isEmpty()){
            titleOfBachelorThesis = title;
        }
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(final int startYear) {
        if (2000 < startYear && startYear <= CURRENT_YEAR){
            this.startYear = startYear;
        }
    }

    public int getGraduationYear(){
        return graduationYear;
    }

    public String setGraduationYear(final int graduationYear){
        if (!canGraduate()){
            return "Check the required studies";
        } else if (graduationYear < startYear || graduationYear > CURRENT_YEAR) {
            return "Check graduation year";
        } else {
            this.graduationYear = graduationYear;
            return "OK";
        }
    }

    public boolean hasGraduated() {
        return (graduationYear >= startYear && graduationYear <= CURRENT_YEAR);
    }

    private boolean canGraduate(){
        if (bachelorCredits < BACHELOR_CREDITS || masterCredits < MASTER_CREDITS){
            return false;
        } else if (titleOfBachelorThesis.equals(NO_TITLE) || titleOfMasterThesis.equals(NO_TITLE)){
            return false;
        } else {
            return true;
        }
    }

    public int getStudyYears(){
        if (hasGraduated()){
            return graduationYear - startYear;
        } else {
            return CURRENT_YEAR - startYear;
        }
    }

    private int getRandomId(){
        Random ran = new Random();
        id = ran.nextInt(MAX_ID) + MIN_ID;
        return id;
    }

    public String setPersonId(final String personId){
        String year;
        if (checkPersonIDNumber(personId)){
            char centChar = personId.charAt(6);

            year = switch (centChar) {
                case '+' -> "18";
                case '-' -> "19";
                case 'A' -> "20";
                default -> "virheellinen merkki";
            };

            String bDate = personId.substring(0,2) + "." + personId.substring(2,4) + "." + year + personId.substring(4,6);

            if (checkBirthdate(bDate)){

                if (!checkValidCharacter(personId)){
                    return INCORRECT_CHECKMARK;
                }

            } else {
                return INVALID_BIRTHDAY;
            }
            birthDate = bDate;
            return "Ok";
        } else {
            return INVALID_BIRTHDAY;
        }
    }

    private boolean checkPersonIDNumber(final String personID){
        char centChar = personID.charAt(6);
        return personID.length() == 11 && (centChar == '+' || centChar == 'A' || centChar == '-');
    }

    private boolean checkLeapYear(int year){
        return (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0);
    }


    private boolean checkValidCharacter(final String personID){
        char c = personID.charAt(personID.length() - 1);
        char [] controlChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
                'E', 'F', 'H', 'J', 'K', 'L','M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y'};

        String numbersOfID = personID.substring(0,6) + personID.substring(7,10);
        int valueOfID = Integer.parseInt(numbersOfID);
        valueOfID = (valueOfID % 31);

        return controlChar[valueOfID] == c;
    }

    private boolean checkBirthdate(final String date){

        if (date.length() != 10){
            return false;
        }

        int day = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(3,5));
        int year = Integer.parseInt(date.substring(6,10));

        if (day >= 1 && day <= 31 && month >=1 && month <= 12 && year > 0){

            if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30){
                return false;
            } else if (month == 2){
                if (checkLeapYear(year) && day > 29 || !checkLeapYear(year) && day > 28){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private String status(){
        if (hasGraduated()){
            return "The student has graduated in " + graduationYear;
        } else {
            return "The student has not graduated, yet.";
        }
    }

    private String bachelorStatus(){
        if (bachelorCredits >= BACHELOR_CREDITS){
            return "All required bachelor credits completed (" + bachelorCredits + "/" + BACHELOR_CREDITS + ")";
        } else {
            double missing = BACHELOR_CREDITS - bachelorCredits;
            return "Missing bachelor credits " + missing + "(" + bachelorCredits + "/" + BACHELOR_CREDITS + ")";
        }
    }

    private String masterStatus(){
        if (masterCredits >= MASTER_CREDITS){
            return "All required master's credits completed (" + masterCredits + "/" + MASTER_CREDITS + ")";
        } else {
            double missing = MASTER_CREDITS - masterCredits;
            return "Missing master's credits " + missing + "(" + masterCredits + "/" + MASTER_CREDITS + ")";
        }
    }



    @Override
    public String toString(){
        return "Student id: " + id + "\n" +
                "     " + "FirstName: " + firstName + ", LastName: " + lastName + "\n" +
                "     " + "Date of birth: " + birthDate + "\n" +
                "     " + "Status: " + status() + "\n" +
                "     " + "StartYear: " + startYear + " (Studies have lasted for " + getStudyYears() + " years)" + "\n" +
                "     " + "BachelorCredits: " + bachelorCredits + " ==> " + bachelorStatus() + "\n" +
                "     " + "TitleOfBachelorThesis: " + titleOfBachelorThesis + "\n" +
                "     " + "MasterCredits: " + masterCredits + " ==> " + masterStatus() + "\n" +
                "     " + "TitleOfMastersThesis: " + titleOfMasterThesis + "\n\n";

    }
}
