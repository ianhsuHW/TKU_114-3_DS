package w0721;

import java.util.ArrayList;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();
        addCourse(courses, new Course("DS101", "Data Structures", 2));
        enroll(courses, "DS101");
        enroll(courses, "DS101");
        printReport(courses);
    }
    public static boolean addCourse(ArrayList<Course> courses, Course course) { if(findCourse(courses,course.getCode())!=null) return false; courses.add(course); return true; }
    public static Course findCourse(ArrayList<Course> courses, String code) { for(Course course:courses) if(course.getCode().equalsIgnoreCase(code)) return course; return null; }
    public static boolean enroll(ArrayList<Course> courses, String code) { Course course=findCourse(courses,code); return course != null && course.enroll(); }
    public static boolean withdraw(ArrayList<Course> courses, String code) { Course course=findCourse(courses,code); return course != null && course.withdraw(); }
    public static boolean removeCourse(ArrayList<Course> courses, String code) { Course course=findCourse(courses,code); return course != null && courses.remove(course); }
    public static void printReport(ArrayList<Course> courses) { int total=0; for(Course course:courses) total+=course.getEnrolled(); System.out.println("總課程數："+courses.size()+"，總選課人次："+total); for(Course course:courses) if(course.isFull()) System.out.println("額滿課程："+course); }
}
