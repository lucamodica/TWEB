package DAO;

import java.util.Date;

public class Lesson {

    private int teacher;
    private int user;
    private Time h_start;
    private Time h_end;
    private String course;

    public Lesson(int teacher, int user, int h_start, int m_start, String course) {
        this.teacher = teacher;
        this.user = user;
        this.h_start = new Time(h_start, m_start);
        this.h_end = new Time(h_start + 1, m_start);
        this.course = course;
    }

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "{" +
                "teacher=" + teacher +
                ", user=" + user +
                ", h_start='" + h_start + '\'' +
                ", h_end='" + h_end + '\'' +
                ", course='" + course + '\'' +
                '}';
    }


    private class Time{
        private int hours;
        private int minutes;

        public Time(int hours, int minutes) {
            this.hours = hours;
            this.minutes = minutes;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        @Override
        public String toString() {
            return hours + ":" + minutes;
        }
    }
}
