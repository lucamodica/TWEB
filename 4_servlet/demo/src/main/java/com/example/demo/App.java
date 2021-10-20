package com.example.demo;

import com.example.DAO.*;
import com.example.model.*;

import java.util.*;

public class App {

    public static int selectOp(){
        int option = 0;
        Scanner input = new Scanner(System.in);

        do{
            System.out.println("Select the operation you want to do on the DB (or press 0 to exit the program): ");
            System.out.println("1 = Insert a new course title");
            System.out.println("2 = Remove an existent course title");
            System.out.println("3 = Insert a new teacher");
            System.out.println("4 = Remove an existent teacher");
            System.out.println("5 = Insert a new affilation between a course and a teacher");
            System.out.println("6 = Remove an existent affilation between a course and a teacher");
            System.out.println("7 = View, for each course, the available repetition lists");
            System.out.println("8 = Insert a new booked lesson");
            System.out.println("9 = Delete an existent booked lesson");
            System.out.println("0 = exit");
            System.out.print("> ");
            option = input.nextInt();

            if (option < 0 || option > 9) {
                System.out.println("Invalid choice or typo, please try again.\n");
            }
        }while (option < 0 || option > 9);

        return option;
    }

    public static void printCourses(){
        ArrayList<Course> courses = DAO.retrieveCourses();
        System.out.println("Current courses available: ");
        for (Course c: courses) {
            System.out.println(c);
        }
        System.out.println();
    }

    public static void printTeachers(){
        ArrayList<Teacher> teachers = DAO.retrieveTeachers();
        System.out.println("Current teachers available: ");
        for (Teacher t: teachers) {
            System.out.println(t);
        }
        System.out.println();
    }

    public static void printAffiliations(){
        ArrayList<Affilation> affilations = DAO.retrieveAffilations();
        System.out.println("Current affiliations teacher <---> course: ");
        for (Affilation a: affilations) {
            System.out.println(a);
        }
        System.out.println();
    }

    public static void printUsers(){
        ArrayList<User> users = DAO.retrieveUsers();
        System.out.println("Current users available: ");
        for (User u: users) {
            System.out.println(u);
        }
        System.out.println();
    }

    public static void printLessons(){
        ArrayList<Lesson> courses = DAO.retrieveLessons();
        System.out.println("Current lessons booked: ");
        for (Lesson l: courses) {
            System.out.println(l);
        }
        System.out.println();
    }


    public static void main(String[] args) {

        int choice = 0;
        Scanner in = new Scanner(System.in);

        //Registering the MySQL driver
        DAO.registerDriver();

        //Printing the current available data
        System.out.println("Welcome to the courseHandlerPlatform!!\n");
        printCourses();
        printTeachers();
        printAffiliations();
        printUsers();
        printLessons();
        System.out.println();

        //Data for insert/delete lessons
        int teacherr, userr, h, m;
        String course;
        Lesson l = null;

        //Managing data eheh
        do {
            choice = selectOp();
            System.out.println();
            switch (choice){
                case 1:
                    String title, desc;
                    Course c = null;

                    //Data input
                    System.out.println("Insert a title: ");
                    title = in.nextLine();
                    System.out.println("Insert a description: ");
                    desc = in.nextLine();
                    c = new Course(title, desc);
                    DAO.insertCourse(c);
                    break;

                case 2:
                    String title_del = null;

                    //Data input
                    System.out.println("Insert the title of the course: ");
                    title_del = in.nextLine();
                    DAO.deleteCourse(title_del);
                    break;

                case 3:
                    int ID_number;
                    String name, surname;
                    Teacher t;

                    //Data input
                    System.out.println("Insert an ID number: ");
                    ID_number = in.nextInt();
                    in.nextLine(); //For workaroud to prevent input skipping
                    System.out.println("Insert a name: ");
                    name = in.nextLine();
                    System.out.println("Insert a surname: ");
                    surname = in.nextLine();
                    t = new Teacher(ID_number, name, surname);
                    DAO.insertTeacher(t);
                    break;

                case 4:
                    int id = 0;

                    //Data input
                    System.out.println("Insert the ID number of the teacher: ");
                    id = in.nextInt();
                    DAO.deleteTeacher(id);
                    break;

                case 5:
                    int ID_number_aff;
                    String title_aff;
                    Affilation a = null;

                    //Data input
                    System.out.println("Insert the ID number of a teacher: ");
                    ID_number = in.nextInt();
                    in.nextLine(); //For workaroud to prevent input skipping
                    System.out.println("Insert a course title: ");
                    title = in.nextLine();
                    a = new Affilation(ID_number, title);

                    DAO.insertAffilation(a);
                    break;

                case 6:
                    int ID_number_del_aff;
                    String title_del_aff;
                    Affilation a_del = null;

                    //Data input
                    System.out.println("Insert the ID number of a teacher: ");
                    ID_number_del_aff = in.nextInt();
                    in.nextLine(); //For workaroud to prevent input skipping
                    System.out.println("Insert a course title: ");
                    title_del_aff = in.nextLine();
                    a_del = new Affilation(ID_number_del_aff, title_del_aff);
                    DAO.deleteAffilation(a_del);
                    break;

                case 7:
                    HashMap<String, HashSet<Teacher>> grid = DAO.retrievePossibleRepetitions();
                    for (Map.Entry<String, HashSet<Teacher>> set :
                            grid.entrySet()) {

                        System.out.print("Teacher for " + set.getKey() + ": ");
                        for (Teacher teacher: set.getValue()) {
                            System.out.print("| " + teacher.getName() + " " +
                                    teacher.getSurname() + " | ");
                        }
                        System.out.println();
                    }
                    break;

                case 8:
                    //Data input
                    System.out.println("Insert the ID number of the teacher: ");
                    teacherr = in.nextInt();
                    in.nextLine(); //For workaroud to prevent input skipping
                    System.out.println("Insert the ID number of the user: ");
                    userr = in.nextInt();
                    in.nextLine(); //For workaroud to prevent input skipping
                    System.out.println("Insert the course title: ");
                    course = in.nextLine();
                    System.out.println("Insert the hour of the lesson (its duration will be 1 hour): ");
                    h = in.nextInt();
                    in.nextLine(); //For workaroud to prevent input skipping
                    System.out.println("Insert the minutes of the hour: ");
                    m = in.nextInt();
                    in.nextLine(); //For workaroud to prevent input skipping

                    l = new Lesson(teacherr, userr, h, m, course);
                    DAO.insertLesson(l);
                    break;

                case 9:
                    //Data input
                    System.out.println("Insert the ID number of the teacher: ");
                    teacherr = in.nextInt();
                    in.nextLine(); //For workaroud to prevent input skipping
                    System.out.println("Insert the ID number of the user: ");
                    userr = in.nextInt();
                    in.nextLine(); //For workaroud to prevent input skipping
                    System.out.println("Insert the hour in which the lesson starts: ");
                    h = in.nextInt();
                    in.nextLine(); //For workaroud to prevent input skipping
                    System.out.println("Insert the minutes of the hour: ");
                    m = in.nextInt();
                    in.nextLine(); //For workaroud to prevent input skipping

                    l = new Lesson(teacherr, userr, h, m, null);
                    DAO.deleteLesson(l);
                    break;

                default:
                    break;
            }

            System.out.println("\n\n");
        }while (choice != 0);


    }
}
