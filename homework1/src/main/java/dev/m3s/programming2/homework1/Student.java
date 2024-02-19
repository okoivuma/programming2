package dev.m3s.programming2.homework1;

import java.util.Random;

public class Student {

    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private int id;
    private double bachelorCredits;
    private double masterCredits;
    private String titleOfMasterThesis = ConstantValues.NO_TITLE;
    private String titleOfBachelorThesis = ConstantValues.NO_TITLE;
    private  int startYear = ConstantValues.CURRENT_YEAR;
    private int graduationYear;
    private String birthDate = ConstantValues.NO_BIRTHDATE;


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
        if (id >= ConstantValues.MIN_ID && id <= ConstantValues.MAX_ID){
            this.id = id;
        }else {
            this.id = getRandomId();
        }
    }

    public double getBachelorCredits(){
        return bachelorCredits;
    }

    public void setBachelorCredits(final double bachelorCredits){
        if (bachelorCredits >= ConstantValues.MIN_CREDITS && bachelorCredits <= ConstantValues.MAX_CREDITS){
            this.bachelorCredits = bachelorCredits;
        }
    }

    public double getMasterCredits() {
        return masterCredits;
    }

    public void setMasterCredits(final double masterCredits){
        if (masterCredits >= ConstantValues.MIN_CREDITS && masterCredits <= ConstantValues.MAX_CREDITS){
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
        if (2000 < startYear && startYear <= ConstantValues.CURRENT_YEAR){
            this.startYear = startYear;
        }
    }

    public int getGraduationYear(){
        return graduationYear;
    }

    public String setGraduationYear(final int graduationYear){
        if (!canGraduate()){
            return "Check the required studies";
        } else if (graduationYear < startYear || graduationYear > ConstantValues.CURRENT_YEAR) {
            return "Check graduation year";
        } else {
            this.graduationYear = graduationYear;
            return "OK";
        }
    }

    public boolean hasGraduated() {
        return (graduationYear >= startYear && graduationYear <= ConstantValues.CURRENT_YEAR);
    }

    private boolean canGraduate(){
        if (bachelorCredits < ConstantValues.BACHELOR_CREDITS || masterCredits < ConstantValues.MASTER_CREDITS){
            return false;
        } else if (titleOfBachelorThesis.equals(ConstantValues.NO_TITLE) || titleOfMasterThesis.equals(ConstantValues.NO_TITLE)){
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

    public String setPersonId(final String personId){
        if (personId != null && !personId.isEmpty()) {
            String year;
            if (checkPersonIDNumber(personId)) {
                char centChar = personId.charAt(6);

                year = switch (centChar) {
                    case '+' -> "18";
                    case '-' -> "19";
                    case 'A' -> "20";
                    default -> "virheellinen merkki";
                };

                String bDate = personId.substring(0, 2) + "." + personId.substring(2, 4) + "." + year + personId.substring(4, 6);

                if (checkBirthdate(bDate)) {

                    if (!checkValidCharacter(personId)) {
                        return ConstantValues.INCORRECT_CHECKMARK;
                    }

                } else {
                    return ConstantValues.INVALID_BIRTHDAY;
                }
                birthDate = bDate;
                return "Ok";
            } else {
                return ConstantValues.INVALID_BIRTHDAY;
            }
        } else
            return ConstantValues.INVALID_BIRTHDAY;
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
        if (bachelorCredits >= ConstantValues.BACHELOR_CREDITS){
            return "All required bachelor credits completed (" + bachelorCredits + "/" + ConstantValues.BACHELOR_CREDITS + ")";
        } else {
            double missing = ConstantValues.BACHELOR_CREDITS - bachelorCredits;
            return "Missing bachelor credits " + missing + "(" + bachelorCredits + "/" + ConstantValues.BACHELOR_CREDITS + ")";
        }
    }

    private String masterStatus(){
        if (masterCredits >= ConstantValues.MASTER_CREDITS){
            return "All required master's credits completed (" + masterCredits + "/" + ConstantValues.MASTER_CREDITS + ")";
        } else {
            double missing = ConstantValues.MASTER_CREDITS - masterCredits;
            return "Missing master's credits " + missing + "(" + masterCredits + "/" + ConstantValues.MASTER_CREDITS + ")";
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

