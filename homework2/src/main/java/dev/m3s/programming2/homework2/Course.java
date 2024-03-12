package dev.m3s.programming2.homework2;
//package kotiteht2;



public class Course {

    private String name;
    private String courseCode;
    private char courseBase;
    private int courseType;
    private int period;
    private double credits;
    private boolean numericGrade;

    public Course(){

    }

    public Course(String name,  final int code, char courseBase, final int type,
                  final int period, final double credits, boolean numericGrade){

        setName(name);
        setCourseCode(code, courseBase);
        setCourseType(type);
        setPeriod(period);
        setCredits(credits);
        setNumericGrade(numericGrade);
    }

    public Course(Course course){
        this.name = course.name;
        this.courseCode = course.courseCode;
        this.courseBase = course.courseBase;
        this.courseType = course.courseType;
        this.period = course.period;
        this.credits = course.credits;
        this.numericGrade = course.numericGrade;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        if (name != null && !name.isEmpty()){
            this.name = name;
        }
    }

    public String getCourseTypeString(){
        if (courseType == ConstantValues.MANDATORY){
            return "Mandatory";
        } else {
            return "Optional";
        }
    }

    public int getCourseType(){
        return courseType;
    }

    public void setCourseType(final int type){
        if (type == 1 || type == 0){
             courseType = type;
        }
    }

    public String getCourseCode(){
        return courseCode;
    }

    public void setCourseCode(final int courseCode, char courseBase){
        courseBase = Character.toUpperCase(courseBase);
        if (courseCode > 0 && courseCode < 1000000 && (courseBase == 'A'  || courseBase == 'P' || courseBase == 'S')){
            this.courseCode = String.valueOf(courseCode) + (courseBase);
            this.courseBase = (courseBase);
        }
    }

    public char getCourseBase(){
        return courseBase;
    }

    public int getPeriod(){
        return period;
    }

    public void setPeriod(final int period){
        if (period >= ConstantValues.MIN_PERIOD && period <= ConstantValues.MAX_PERIOD){
            this.period = period;
        }
    }

    public double getCredits(){
        return credits;
    }

    private void setCredits(final double credits){
        if (credits >= ConstantValues.MIN_CREDITS && credits <= ConstantValues.MAX_COURSE_CREDITS){
            this.credits = credits;
        }
    }
    public boolean isNumericGrade(){
        return numericGrade;
    }

    public void setNumericGrade(boolean numericGrade){
        this.numericGrade = numericGrade;
    }


    @Override
    public String toString(){
        return "[" + courseCode + " (" + String.format("%.2f", credits) + " cr), \"" + name + "\". " + getCourseTypeString() + ", period: " + period + ".]";
    }
}
