package dev.m3s.programming2.homework3;
//package kotiteht3;

abstract class Employee extends Person implements Payment{
    private String empId;
    private int startYear;
    private Payment payment;

    public Employee(String lname, String fname){
        super(lname, fname);
        // generate the id string and set startyear
        if (this.getClass().getSimpleName().equals("AssistantTeacher")){
            empId = getEmployeeIdString() + getRandomId(2001, 3000);
        } else if (this.getClass().getSimpleName().equals("ResponsibleTeacher")){
            empId = getEmployeeIdString() + getRandomId(2001, 3000);
        }
        startYear = ConstantValues.CURRENT_YEAR;
    }

    public String getIdString(){
        return empId;
    }

    public int getStartYear(){
        return startYear;
    }

    public void setStartYear(final int startYear){
        if (startYear > 2000 && startYear <= ConstantValues.CURRENT_YEAR){
            this.startYear = startYear;
        }
    }

    public double calculatePayment(){

        if (payment != null){
            return payment.calculatePayment();
        } else {
            return 0.0;
        }
    }

    protected abstract String getEmployeeIdString();
}