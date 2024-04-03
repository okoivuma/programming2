//package kotiteht3;
package dev.m3s.programming2.homework3;

import java.util.Random;

abstract class Person {

    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    public Person(String lname, String fname){
        setLastName(lname);
        setFirstName(fname);
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

    public String getBirthDate(){
        return birthDate;
    }

    public String setBirthDate(String personId){

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

    protected int getRandomId(final int min, final int max){
        Random ran = new Random();
        return ran.nextInt(max) + min;
    }

    abstract String getIdString();

}
