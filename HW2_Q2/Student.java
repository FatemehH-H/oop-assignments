import java.util.List;
import java.util.ArrayList;
public class Student {

    private String firstName;
    private String lastName;
    private String studentCode;
    private String year;
    private String phoneNumber;
    private String passedUnitNumber;
    private String studentCourse;
    private String studentUnit;
    private List<Course> courses;
    private List<Student> editProfile;

    public void setStudentUnit(String studentUnit) {
        this.studentUnit = studentUnit;
    }

    public String getStudentUnit() {
        return studentUnit;
    }

    private List<Course> getCourses;
    public List<Course> getCourses2() {
        return courses;
    }


    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public Student(String firstName, String lastName, String studentCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentCode = studentCode;
        this.courses = new ArrayList<>();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassedUnitNumber(String passedUnitNumber) {
        this.passedUnitNumber = passedUnitNumber;
    }

    public String getYear() {
        return year;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassedUnitNumber() {
        return passedUnitNumber;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCoursesList() {
        return courses;
    }

    public List<Student> getEditProfile() {
        return editProfile;
    }

    public void setEditProfile(List<Student> editProfile) {
        this.editProfile = editProfile;
    }
}
