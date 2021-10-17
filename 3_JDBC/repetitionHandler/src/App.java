import DAO.*;

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

        //Managing data eheh
        do {
            choice = selectOp();
            System.out.println();
            switch (choice){
                case 1:
                    DAO.insertCourse();
                    break;

                case 2:
                    DAO.deleteCourse();
                    break;

                case 3:
                    DAO.insertTeacher();
                    break;

                case 4:
                    DAO.deleteTeacher();
                    break;

                case 5:
                    DAO.insertAffilation();
                    break;

                case 6:
                    DAO.deleteAffilation();
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
                    DAO.insertLesson();
                    break;

                case 9:
                    DAO.deleteLesson();
                    break;

                default:
                    break;
            }

            System.out.println("\n\n");
        }while (choice != 0);


    }
}
