import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.*;
public class Manager {

    private ArrayList<Course> getCourses;

    private ArrayList<Student> signIn;




    public Manager() {
        this.getCourses = new ArrayList<>();
        this.signIn = new ArrayList<>();
    }

    public void getCourse(Course course) {
        getCourses.add(course);
    }

    private Student getStudent(String firstName, String lastName, String studentCode) {
        for (Student student : signIn)
            if (student.getStudentCode().equals(studentCode) &&
                    student.getFirstName().equals(firstName) &&
                    student.getLastName().equals(lastName))
                return student;

        return null;
    }

    private Course getCourse2(String courseCode) {
        for (Course course : getCourses)
            if (course.getCourseCode().equals(courseCode))
                return course;

        return null;
    }

    private int getUnit() {
        int x = 0;
        if(loggedInStudent !=null)
            for (Course course : loggedInStudent.getCoursesList())
                x += Integer.parseInt(course.getCourseUnit());

        return x;
    }

    private Student loggedInStudent;

    public void signIn(Student student) {
        boolean studentSignIn = false;
        String studentCode = student.getStudentCode();
        String studentFirstName = student.getFirstName();
        String studentLastName = student.getLastName();



        if (Character.isLowerCase(student.getFirstName().charAt(0))) {
            System.out.println("First name first character should be uppercase !");
            return;
        } else if (Character.isLowerCase(student.getLastName().charAt(0))) {
            System.out.println("Last name first character should be uppercase !");
            return;
        } else if (!Pattern.matches("\\d{9}", studentCode)) {
            System.out.println("The ID number contains characters other than numbers or is too short !");
            return;
        }


        for (Student student1 : signIn) {

            if (student1.getFirstName().equals(student.getFirstName()) && student1.getLastName().equals(student.getLastName()) && student1.getStudentCode().equals(student.getStudentCode())) {
                System.out.println("A student with this name exist !");
                studentSignIn = true;
                break;
            }
        }

        if (!studentSignIn) {
            System.out.println("Student with name \"" + studentFirstName + " " + studentLastName + "\" and ID number \"" + studentCode + "\" created !");
            signIn.add(student);


        }
    }

    boolean logIn2 = false;



    public void logIn(String firstName, String lastName, String studentCode) {
        boolean logIn = true;

        for (Student student1 : signIn) {
            if (student1.getFirstName().equals(firstName) && student1.getLastName().equals(lastName) && student1.getStudentCode().equals(studentCode)) {
                System.out.println("Welcome \"" + firstName + "\"");
                logIn2 = true;
                loggedInStudent = student1;
                profileEditStudent = student1;
                logIn = false;
                break;
            }
        }

        if (logIn) {
            System.out.println("There is no student with this name and ID !");
        }
    }


    public void logOut() {

        if (loggedInStudent==null ) {
            System.out.println("Invalid command !");
        } else {
            System.out.println("Logged out successfully !");
            // signIn.remove(loggedInStudent);
            loggedInStudent = null;
            // profileEditStudent=null;
        }
    }

    private Student profileEditStudent;
    boolean editProfile=false;

    public void editProfile(String year, String phoneNumber, String passedUnitNumber) {
        int year1 = Integer.parseInt(year);
        int passedUnits = Integer.parseInt(passedUnitNumber);

        if (loggedInStudent == null) {
            System.out.println("Invalid command !");
        } else if (!year.matches("\\d+")) {
            System.out.println("Year must be a number !");
        } else if (!(year1 == 1402 || year1 == 1401)) {
            System.out.println("Year must be 1401 or 1402 !");
        } else if (!phoneNumber.matches("\\d{10}")) {
            System.out.println("Phone must be a number and it's length should be 10 !");
        } else if (!passedUnitNumber.matches("\\d+")) {
            System.out.println("Units must be a number !");
        }
        else if(passedUnits<=10 || passedUnits>=90){
            System.out.println("Units must be between 10 and 90 !");
        }
//        else if (!(passedUnits > 10 && passedUnits < 90)) {
//            System.out.println("Units must be between 10 and 90 !");
//        }
        else {

            loggedInStudent.setYear(year);
            loggedInStudent.setPhoneNumber(phoneNumber);
            loggedInStudent.setPassedUnitNumber(passedUnitNumber);
            // signIn.add(loggedInStudent);
            System.out.println("Profile edited successfully !");
            profileEditStudent = null;
            editProfile=true;
        }


    }


    public void showDepartmentList() {
        System.out.println("Department List is :");

        for (int i = 0; i < getCourses.size(); i++) {
            Course course = getCourses.get(i);
            String courseTime = "";
            switch (course.getCourseTime()) {
                case "1":
                    courseTime = "7:30 to 9:00";
                    break;
                case "2":
                    courseTime = "9:00 to 10:30";
                    break;
                case "3":
                    courseTime = "10:30 to 12:00";
                    break;
                default:
                    courseTime = "N/A";
                    break;
            }

            String courseDay = course.getCourseDay().equals("1") ? "Saturday / Monday" : "Sunday / Tuesday";

            System.out.println((i + 1) + ". " + course.getCourseName() + " - " + course.getCourseCode() + " - " +
                    course.getCourseMaster() + " - Time : " + courseTime + " - Day : " + courseDay);
        }
    }






    public void showMyList() {
        if (loggedInStudent == null) {
            System.out.println("Invalid command !");
        } else if (loggedInStudent.getCoursesList() == null || loggedInStudent.getCoursesList().isEmpty()) {
            System.out.println("List is empty !");
        } else {
            System.out.println("My List is :");
            for (int i = 0; i < loggedInStudent.getCoursesList().size(); i++) {
                System.out.println((i + 1) + ". " + loggedInStudent.getCoursesList().get(i).getCourseName() + " - " + loggedInStudent.getCoursesList().get(i).getCourseCode());
            }
        }
    }


    public void selectCourse(String courseCode) {
        Course course = getCourse2(courseCode);
        if(!courseCode.matches("\\d{6}")){
            System.out.println("Invalid Code !");
        }
        else  {
            String firstFourDigits = courseCode.substring(0, 5);
            if( course==null ){
                System.out.println("Invalid Code !");
            }

            if (course != null) {
                if (loggedInStudent == null) {
                    System.out.println("Invalid command !");
                } else if (loggedInStudent.getYear()==null) {
                    System.out.println("Please first edit your profile !");
                }
//            else if (course.getCourseCode() == null) {
//                System.out.println("Invalid Code !");
//            }
                else if (getUnit() == 20 || getUnit() + Integer.parseInt(course.getCourseUnit()) > 20) {
                    System.out.println("Unit Limits Error !");
                } else {
                    boolean courseExists = false;
                    for (Course takenCourse : loggedInStudent.getCoursesList()) {
                        if ((takenCourse.getCourseCode()).startsWith(firstFourDigits)) {
                            System.out.println("Same Course Code Error !");
                            courseExists = true;
                            break;
                        } else if (takenCourse.getCourseDay().startsWith(course.getCourseDay()) && takenCourse.getCourseTime().startsWith(course.getCourseTime())) {
                            System.out.println("Same Time Error !");
                            courseExists = true;
                            break;
                        }
                    }
                    if (!courseExists) {
                        getUnit();
                        loggedInStudent.getCoursesList().add(course);
                        System.out.println("Course added successfully !");

                    }
                }
            }
        }
    }

    public void deleteCourse(String courseCode) {
        Course course = getCourse2(courseCode);

         if ( !loggedInStudent.getCoursesList().contains(course) || !courseCode.matches("\\d{6}") || course == null) {
            System.out.println("Course with this code does not exist in your list !");
        } else {
            boolean removed = loggedInStudent.getCoursesList().remove(course);
            if (removed) {
                System.out.println("Course deleted successfully !");
            }

        }

    }


    public void showProfile() {
        int unitLimitsInt=20 - getUnit();
        String unitLimits= String.valueOf(unitLimitsInt);
        //String addedCourse=String.valueOf(addedCourse);
        if (loggedInStudent == null) {
            System.out.println("Invalid command !");
        }
        else if (loggedInStudent.getYear()==null) {
            System.out.println("Please first edit your profile !");
        }
        else{
            System.out.println("First name : " + loggedInStudent.getFirstName());
            System.out.println("Last name : " + loggedInStudent.getLastName());
            System.out.println("Student ID : " + loggedInStudent.getStudentCode());
            System.out.println("Entrance year : "+ loggedInStudent.getYear());
            System.out.println("Phone number : "+ loggedInStudent.getPhoneNumber());
            System.out.println("Passed units : " + loggedInStudent.getPassedUnitNumber());
            System.out.println("Units limit : " + unitLimits);
            System.out.println("Courses number : " + loggedInStudent.getCoursesList().size());

        }

    }
    static List<String> successMessages = new ArrayList<>();
    static List<String> errorInvalidCode = new ArrayList<>();
    static List<String> errorUnitLimit =new ArrayList<>();
    static List<String> errorSameCourseCode = new ArrayList<>();
    static List<String> errorSameTimeCode = new ArrayList<>();
    boolean selectAllError=false;
    public void selectAllError(){
        int unit=getUnit();
        if (loggedInStudent == null) {
            System.out.println("Invalid command !");
            selectAllError=true;
            return;
        }
        else if (loggedInStudent.getYear()==null) {
            System.out.println("Please first edit your profile !");
            selectAllError=true;
            return;
        }
        else if (unit == 20) {
            System.out.println("You have selected 20 units already !");
            selectAllError=true;
            return;
        }


    }
    public void selectAll(String courseCodes) {


        if(!selectAllError) {
            Course course = getCourse2(courseCodes);
            boolean limitUnit = false;
            boolean sameCourseCode = false;
            boolean sameTimeCode = false;

            if (course == null || !courseCodes.matches("\\d{6}")) {
                errorInvalidCode.add(courseCodes);
                // continue;
            } else {
                String firstFourDigits = courseCodes.substring(0, 5);
                int getCourseUnit = Integer.parseInt(course.getCourseUnit());
                if (getUnit() + getCourseUnit > 20) {
                    errorUnitLimit.add(courseCodes);
                    limitUnit = true;
                }

                if (!limitUnit) {
                    boolean hasConflict = false;
                    for (Course takenCourse : loggedInStudent.getCoursesList()) {

                        if (takenCourse.getCourseCode().startsWith(firstFourDigits)) {
                            errorSameCourseCode.add(courseCodes);
                            hasConflict = true;
                            sameCourseCode = true;
                            break;

                        } else if (takenCourse.getCourseTime().equals(course.getCourseTime()) && takenCourse.getCourseDay().equals(course.getCourseDay())) {
                            errorSameTimeCode.add(courseCodes);
                            hasConflict = true;
                            sameTimeCode = true;
                        }
                    }
                    if (sameCourseCode) {
                        if (sameTimeCode)
                            errorSameTimeCode.remove(errorSameTimeCode.size() - 1);
                    }
                    if (!hasConflict) {
                        loggedInStudent.getCoursesList().add(course);
                        successMessages.add("Course " + courseCodes + " added successfully !");
                    }
                }

            }
        }


    }

    public void selectAllPrint() {
        for (String successMsg : successMessages) {
            System.out.println(successMsg);
        }

        if (!errorInvalidCode.isEmpty()) {
            System.out.println("Invalid Codes -> " + String.join(" ", errorInvalidCode));
        }

        if (!errorUnitLimit.isEmpty()) {
            System.out.println("Unit Limit Codes -> " + String.join(" ", errorUnitLimit));
        }

        if (!errorSameCourseCode.isEmpty()) {
            System.out.println("Same Course Codes -> " + String.join(" ", errorSameCourseCode));
        }

        if (!errorSameTimeCode.isEmpty()) {
            System.out.println("Same Time Codes -> " + String.join(" ", errorSameTimeCode));
        }
        successMessages.clear();
        errorInvalidCode.clear();
        errorUnitLimit.clear();
        errorSameCourseCode.clear();
        errorSameTimeCode.clear();

    }



//    public void selectAll(ArrayList<String> courseCodes) {
//        if (loggedInStudent == null) {
//            System.out.println("Invalid command !");
//            return;
//        }
//        else if (loggedInStudent.getYear()==null) {
//            System.out.println("Please first edit your profile !");
//            return;
//        }
//        else if (getUnit() == 20) {
//            System.out.println("You have selected 20 units already !");
//            return;
//        }
//
//        List<String> successMessages = new ArrayList<>();
//        List<String> errorInvalidCode = new ArrayList<>();
//        List<String> errorUnitLimit =new ArrayList<>();
//        List<String> errorSameCourseCode = new ArrayList<>();
//        List<String> errorSameTimeCode = new ArrayList<>();
//
//        for (String code : courseCodes) {
//
//
//            Course course = getCourse2(code);
//            boolean limitUnit = false;
//
//
//            if (course == null || !code.matches("\\d{6}")) {
//                errorInvalidCode.add( code);
//                continue;
//            }
//
//            else {
//                String firstFourDigits = code.substring(0, 5);
//                int getCourseUnit = Integer.parseInt(course.getCourseUnit());
//                if (getUnit() + getCourseUnit > 20) {
//                    errorUnitLimit.add(code);
//                    limitUnit = true;
//                }
//
//                if (!limitUnit) {
//                    boolean hasConflict = false;
//                    for (Course takenCourse : loggedInStudent.getCoursesList()) {
//                        if (takenCourse.getCourseCode().startsWith(firstFourDigits)) {
//                            errorSameCourseCode.add( code);
//                            hasConflict = true;
//                            break;
//                        } else if (takenCourse.getCourseTime().equals(course.getCourseTime()) && takenCourse.getCourseDay().equals(course.getCourseDay())) {
//                            errorSameTimeCode.add(code);
//                            hasConflict = true;
//                            break;
//                        }
//                    }
//                    if (!hasConflict) {
//                        loggedInStudent.getCoursesList().add(course);
//                        successMessages.add("Course " + code + " added successfully !");
//                    }
//                }
//            }
//        }
//
//        for (String successMsg : successMessages) {
//            System.out.println(successMsg);
//        }
//
//        if (!errorInvalidCode.isEmpty()) {
//            System.out.println("Invalid Codes -> " + String.join(" ", errorInvalidCode));
//        }
//
//        if (!errorUnitLimit.isEmpty()) {
//            System.out.println("Unit Limit Codes -> " + String.join(" ", errorUnitLimit));
//        }
//
//        if (!errorSameCourseCode.isEmpty()) {
//            System.out.println("Same Course Codes -> " + String.join(" ", errorSameCourseCode));
//        }
//
//        if (!errorSameTimeCode.isEmpty()) {
//            System.out.println("Same Time Codes -> " + String.join(" ", errorSameTimeCode));
//        }
//
//
//
//
//
//
//
//    }







    }

