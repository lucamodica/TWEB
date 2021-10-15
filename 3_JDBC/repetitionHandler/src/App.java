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
            System.out.println("0 = exit");
            System.out.print("> ");
            option = input.nextInt();

            if (option < 0 || option > 7) {
                System.out.println("Invalid choice or typo, please try again.\n");
            }
        }while (option < 0 || option > 7);

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

    public static void main(String[] args) {

        int choice = 0;

        //Registering the MySQL driver
        DAO.registerDriver();

        //Printing the current available data
        System.out.println("Welcome to the courseHandlerPlatform!!\n");
        printCourses();
        printTeachers();
        printAffiliations();
        System.out.println();

        //Managing data eheh
        do {
            choice = selectOp();
            System.out.println();
            switch (choice){
                case 1:
                    Course c = DAO.insertCourse();
                    System.out.println("New course (" + c.getTitle() + ") added!");
                    break;

                case 2:
                    System.out.println("Course " + DAO.deleteCourse() + " deleted.");
                    break;

                case 3:
                    Teacher t = DAO.insertTeacher();
                    System.out.println("Teacher " + t.getName() + " " + t.getSurname() + " added!");
                    break;

                case 4:
                    System.out.println("Teacher " + DAO.deleteTeacher() + " deleted.");
                    break;

                case 5:
                    Affilation a = DAO.insertAffilation();
                    System.out.println("Affiliation of the teacher " + a.getTeacher_id() +
                            " to the course " + a.getCourse_title() + " added!");
                    break;

                case 6:
                    Affilation a_del = DAO.deleteAffilation();
                    System.out.println("Affiliation of the teacher " + a_del.getTeacher_id() +
                            " to the course " + a_del.getCourse_title() + " deleted.");
                    break;

                case 7:
                    HashMap<String, HashSet<Teacher>> grid = DAO.retrievePossibleRepetitions();
                    for (Map.Entry<String, HashSet<Teacher>> set :
                            grid.entrySet()) {

                        System.out.println("Teacher for " + set.getKey() + ": "
                                + set.getValue());
                    }
                    break;

                default:
                    break;
            }

            System.out.println("\n\n");
        }while (choice != 0);


    }
}
