package DAO;

public class Affilation {

    private int teacher_id;
    private String course_title;

    public Affilation(int teacher_id, String course_title) {
        this.teacher_id = teacher_id;
        this.course_title = course_title;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    @Override
    public String toString() {
        return "Affilation{" +
                "teacher_id=" + teacher_id +
                ", course_title='" + course_title + '\'' +
                '}';
    }
}
