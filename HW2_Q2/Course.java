public class Course {

    private String courseName;
    private String courseUnit;
    private String courseMaster;
    private String courseDay;
    private String courseTime;
    private String courseCode;

    public Course(String courseName, String courseUnit, String courseMaster, String courseDay, String courseTime, String courseCode) {
        this.courseName = courseName;
        this.courseUnit = courseUnit;
        this.courseMaster = courseMaster;
        this.courseDay = courseDay;
        this.courseTime = courseTime;
        this.courseCode = courseCode;
    }
    @Override
    public String toString() {
        return  courseName + " - " + courseCode + " - " + courseMaster + " - " + courseTime + " - " + courseDay;
    }


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseUnit(String courseUnit) {
        this.courseUnit = courseUnit;
    }

    public void setCourseMaster(String courseMaster) {
        this.courseMaster = courseMaster;
    }

    public void setCourseDay(String courseDay) {
        this.courseDay = courseDay;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseUnit() {
        return courseUnit;
    }

    public String getCourseMaster() {
        return courseMaster;
    }

    public String getCourseDay() {
        return courseDay;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public String getCourseCode() {
        return courseCode;
    }
}
