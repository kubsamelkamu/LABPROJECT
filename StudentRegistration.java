package com.studentreg;

/**
 * 
 *  Student Registration System
 *  This Project aim to develop java CLI application for managing student registration in high_educational institution.
 *  The system will allow users to add, update, and delete student records, manage course enrollments, and generate reports.
 
 *  @author [Kubsa Melkamu]
 
 */

import java.util.*;


public class StudentRegistration {
    /**
     * @param args
     */
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("--------- Wel-Come to Student registration_ System --------\n Are you underGruate Student or PostGraduate Student:");
            System.out.println(" Enter 1 if you are UnderGraduate and 2 if you are PostGraduate\n ");
            String selectOption = scanner.nextLine();
            
            String optionmatch = "[1-9]";
            int attempt  = 3;

            while (!(selectOption.matches(optionmatch))) {
                
                System.out.println("Invalid option please try again you have "+ attempt+"option:  ");
                selectOption = scanner.nextLine();
                
                if (attempt == 0) {
                    System.out.println("too much trial!!!");
                    break;
                }
                attempt--;
            }

            try {
                Integer selectedOption = Integer.parseInt(selectOption);
                UnderGraduateStudent undergraduatestudent = null;
                switch (selectedOption){
                    case  1:

                    System.out.print("Enter your Name: ");
                    String underGraduateStudentName = scanner.nextLine();
                    String nameregex = "^[a-zA-Z]+$";
                    int attempt_ = 3;
                    while (!underGraduateStudentName.matches(nameregex)) {
                        System.out.println("Enter Valid Name: you have " + attempt_ +  "options" );
                        underGraduateStudentName = scanner.nextLine();
                        attempt_--;
                        if (attempt_ == 0) {
                            System.out.println("too many trial!!!");
                            System.exit(0);
                            
                        }
                        
                    }
                    System.out.print("Enter your id_number: ");
                    String UnderGraduate_Id_no = scanner.nextLine();
                    System.out.print("Enter your Major Field:");
                    String mojorField = scanner.nextLine();
                    System.out.print("Enter your acedemic year:");
                    int acedemicYear  = scanner.nextInt();
                    undergraduatestudent = new UnderGraduateStudent(underGraduateStudentName, UnderGraduate_Id_no, mojorField,acedemicYear);

                    System.out.println("To how many courses do you want to enroll :");  
                    int numberOfCourses = scanner.nextInt();

                    for (int i = 1; i <= numberOfCourses; i++) {
                        System.out.println("Enter " + i +"Course: ");
                        String course = scanner.next();
                        undergraduatestudent.addRegisteredCourses(course);
                    } 
                    System.out.println("Here is the information of undergruated student: ");
                    System.out.println(undergraduatestudent.toString());
                        break;

                    case 2:
                    System.out.print("Enter your Name: ");
                    String PostGraduateStudentName = scanner.nextLine();
                    System.out.print("Enter your id_number: ");
                    String PostGraduate_Id_no = scanner.nextLine();
                    System.out.print("Enter your First Degree:");
                    String firstDegree = scanner.nextLine();
                    System.out.print("Enter your current program:");
                    String currentDegree = scanner.nextLine();

                    // Creating instance of PostGraduateStudent 
                    PostGraduateStudent postgraduatestudent = new PostGraduateStudent(PostGraduateStudentName, PostGraduate_Id_no);
                    postgraduatestudent.setUnderGraduateDegree(firstDegree);
                    postgraduatestudent.setCurrentProgram(currentDegree);

                    System.out.println("Dear post_Graduate Student to how many courses do you wanna to enroll: ");
                    int numberOfCourses_ = scanner.nextInt();
                    for (int i = 1; i <= numberOfCourses_; i++) {
                        System.out.println("Enter" + i +"course");
                        String course_ = scanner.next();
                        postgraduatestudent.addRegisteredCourses(course_);
                    }
                    System.out.println("Here below is the information of  postgraduate student:\n");
                    System.out.println(postgraduatestudent.toString());
                        break;
                    default:
                    System.err.println("invalid option try again!!");
                    System.exit(0);
                        break;
                }
            }catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }catch(Exception e){
             System.out.println(e.getMessage());
            }
        }
    } 
}

class Student{
    private String fullName = "af";
    private String id_no;
   
    public Student(String fullName,String id_no){

        this.isValidName(fullName);
        this.isValid_Id_No(id_no); 
        this.fullName = fullName;
        this.id_no = id_no;
    }
     
  
   
    /**
     * The function checks if a given full name is valid by ensuring it only contains alphabetic
     * characters and has a length less than 15.
     * 
     * @param fullName The fullName parameter is a String that represents the full name that needs to
     * be validated.
     * @return The method is returning a boolean value.
     */
    public boolean isValidName(String fullName){
        final String ALPHABET_REGEX = "^[a-zA-Z]+$";
        if (fullName.matches(ALPHABET_REGEX) && fullName.length() < 15 ) {
            return true;    
        }else{
             System.err.println("The Name you entered is either too long or has invalid character!");
             System.exit(0);
        }
        return false;
    }


    public boolean isValid_Id_No(String  id_no){
       final String ID_REGEX = "^[0-9/T]+$";
        if (id_no.matches(ID_REGEX) ) {
            return true;
        }else{
            System.err.println("invalid id_no!");
            System.exit(0);
        }
        return false;
    }
    

    public String  getName(){
        return   fullName;
    }
    public String getIdNo(){
        return this.id_no;
    }

}   

class UnderGraduateStudent extends Student{
     
    public String majorField;
    protected String courses;
    public int yearOfStudy;
    public List<String> registeredCourses;

    public UnderGraduateStudent(String fullName, String id_no,String majorField,int yearOfStudy){
        super(fullName, id_no);
        this.majorField = majorField;
        this.yearOfStudy = yearOfStudy;

        registeredCourses = new ArrayList<>();   
    }

    

    public void addRegisteredCourses(String courses){
        try {
            String coursereg = "^[1-9a-Z]+$";
            if (courses.matches(coursereg)) {
                System.out.println("invalid course registration try again");
                System.exit(0);
            }else{
               registeredCourses.add(courses); 
            }
        } catch (Exception e) {
            e.getMessage();
        }
        

    }

    public List<String> getRegisteredCourses(){
        return registeredCourses;
    }
    

    /**
     * The toString() function returns a string representation of an UnderGraduate-Student object,
     * including the name, ID number, year of study, major field, and registered courses.
     * 
     * @return The toString() method is returning a string representation of an UnderGraduate - Student
     * object. The returned string includes the name, id number, year of study, major field, and
     * registered courses of the student.
     */
    @Override
    public String toString(){
        return "----------------------------------------------------\n "+
         "UNDERGRADUATE - STUDENT " + 
        "NAME : " + getName() + "\n\t\t\tID_NO:" 
        + getIdNo() + "\n\t\t\tYEAR_OF_STUDY:" + this.yearOfStudy +
        "\n\t\t\tMAJORFIELD" + this.majorField +  "\n\t\t\tREGISTEREDcOURSES:" + this.registeredCourses +
        "\n--------------------------------------------------------------";
    }

    public Object getMajorField() {
        return this.majorField;
    }
    public Object getYearOfStudy() {
        return this.yearOfStudy;
    }
    
}

class PostGraduateStudent extends Student{

    protected String underGraduateDegree;
    public String currentProgram;
    public List<String > registeredCourse;

    public PostGraduateStudent(String fullName, String id_no) {
        super(fullName, id_no);
        registeredCourse = new ArrayList<>();
        
    }
    public void setUnderGraduateDegree(String underGraduateDegree) {
        this.underGraduateDegree = underGraduateDegree;
    }
    public void setCurrentProgram(String currentProgram) {
        this.currentProgram = currentProgram;
    }

    public void addRegisteredCourses(String courses){
        registeredCourse.add(courses);
    }

    public List<String> getRegistaredCourses() {
        return this.registeredCourse;
    }

   /**
    * The toString() function returns a string representation of a postgraduate student object,
    * including their name, ID number, former degree, current degree, and registered courses.
    * 
    * @return The method is returning a string representation of a postgraduate student object. The
    * string includes the student's name, ID number, former degree, current degree, and registered
    * courses.
    */
    
    @Override
    public String toString(){
        return "----------------------------------------------------------------/n" +
         "pOSTGRADUATE - STUDENT " + 
        "NAME : " + getName() + "\n\t\t\tID_NO:" 
        + getIdNo() + "\n\t\t\tFORMER_DEGREE:" + this.underGraduateDegree +
        "\n\t\t\tCURRENTDEGREE" + this.currentProgram+  "\n\t\t\tREGISTEREDCOURSES:" + registeredCourse+
        "\n--------------------------------------------------------------------------";
    }
  
}




