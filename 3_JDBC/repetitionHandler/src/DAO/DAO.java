package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class DAO {

    private static final String url1 = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "";

    public static void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }


    public static ArrayList<Course> retrieveCourses() {
        Connection conn1 = null;
        ArrayList<Course> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM corso");
            while (rs.next()) {
                Course c = new Course(rs.getString("titolo"),rs.getString("descrizione"));
                out.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Teacher> retrieveTeachers() {
        Connection conn1 = null;
        ArrayList<Teacher> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM docente");
            while (rs.next()) {
                Teacher t = new Teacher(rs.getInt("matricola"), rs.getString("nome"),
                        rs.getString("cognome"));
                out.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Affilation> retrieveAffilations() {
        Connection conn1 = null;
        ArrayList<Affilation> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM affiliazione");
            while (rs.next()) {
                Affilation a = new Affilation(rs.getInt("matricola_docente"),
                        rs.getString("titolo_corso"));
                out.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<User> retrieveUsers() {
        Connection conn1 = null;
        ArrayList<User> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM utente");
            while (rs.next()) {
                User u = new User(rs.getInt("matricola"), rs.getString("account"),
                        rs.getString("password"), rs.getString("ruolo"));
                out.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Lesson> retrieveLessons() {
        Connection conn1 = null;
        ArrayList<Lesson> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ripetizione");
            while (rs.next()) {
                String[] hm = rs.getString("ora_inizio").split(":");
                Lesson l = new Lesson(rs.getInt("docente"), rs.getInt("utente"),
                        Integer.parseInt(hm[0]), Integer.parseInt(hm[1]), rs.getString("corso"));
                out.add(l);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static void insertCourse(){
        Connection conn1 = null;
        String title, desc;
        Scanner in = new Scanner(System.in);
        Course c = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Data input
            System.out.println("Insert a title: ");
            title = in.nextLine();
            System.out.println("Insert a description: ");
            desc = in.nextLine();
            c = new Course(title, desc);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into corso (titolo, descrizione) values ('" + title +
                    "', '" + desc + "')");
            System.out.println("New course (" + title + ") added!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

    }

    public static void deleteCourse(){
        Connection conn1 = null;
        String title = null;
        Scanner in = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Data input
            System.out.println("Insert the title of the course: ");
            title = in.nextLine();

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from corso where titolo =  '" + title + "'");
            System.out.println("Course " + title + " deleted.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

    }

    public static void insertTeacher(){
        Connection conn1 = null;
        int ID_number;
        String name, surname;
        Scanner in = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Data input
            System.out.println("Insert an ID number: ");
            ID_number = in.nextInt();
            in.nextLine(); //For workaroud to prevent input skipping
            System.out.println("Insert a name: ");
            name = in.nextLine();
            System.out.println("Insert a surname: ");
            surname = in.nextLine();

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into docente (matricola, nome, cognome) values ('" + ID_number +
                    "', '" + name + "', '" + surname + "')");
            System.out.println("Teacher " + name + " " + surname + " added!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

    }

    public static void deleteTeacher(){
        Connection conn1 = null;
        int id = 0;
        Scanner in = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Data input
            System.out.println("Insert the ID number of the teacher: ");
            id = in.nextInt();

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from docente where matricola =  '" + id + "'");
            System.out.println("Teacher " + id + " deleted.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

    }

    public static void insertAffilation(){
        Connection conn1 = null;
        int ID_number;
        String title;
        Scanner in = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Data input
            System.out.println("Insert the ID number of a teacher: ");
            ID_number = in.nextInt();
            in.nextLine(); //For workaroud to prevent input skipping
            System.out.println("Insert a course title: ");
            title = in.nextLine();

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into affiliazione (matricola_docente, titolo_corso) values ('" + ID_number +
                    "', '" + title + "')");
            System.out.println("Affiliation of the teacher " + ID_number +
                    " to the course " + title + " added!");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

    }

    public static void deleteAffilation(){
        Connection conn1 = null;
        int ID_number;
        String title;
        Scanner in = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Data input
            System.out.println("Insert the ID number of a teacher: ");
            ID_number = in.nextInt();
            in.nextLine(); //For workaroud to prevent input skipping
            System.out.println("Insert a course title: ");
            title = in.nextLine();

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from affiliazione where matricola_docente =  '" + ID_number +
                    "' and titolo_corso = '" + title + "'");
            System.out.println("Affiliation of the teacher " + ID_number +
                    " to the course " + title + " deleted.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

    }

    public static HashMap<String, HashSet<Teacher>> retrievePossibleRepetitions(){
        Connection conn1 = null;
        HashMap<String, HashSet<Teacher>> grid = new HashMap<>();

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM affiliazione join docente " +
                    "on matricola_docente = matricola");

            String title;
            Teacher t;
            //Loading the HashMap
            while (rs.next()){
                title = rs.getString("titolo_corso");
                if (!grid.containsKey(title)){
                    grid.put(title, new HashSet<>());
                }
                t = new Teacher(rs.getInt("matricola"), rs.getString("nome"),
                        rs.getString("cognome"));
                grid.get(title).add(t);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return grid;
    }

    public static void insertLesson(){
        Connection conn1 = null;
        int teacher, userr;
        String course;
        int h, m;
        Scanner in = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Data input
            System.out.println("Insert the ID number of the teacher: ");
            teacher = in.nextInt();
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

            //Execute insert query
            Statement st = conn1.createStatement();
            String hs = h + ":" + m;
            String hf = (h + 1) + ":" + m;
            st.execute("insert into ripetizione (docente, utente, ora_inizio, ora_fine, corso) " +
                    "values ('" + teacher + "', '" + userr + "', '" + hs + "', '" + hf + "', '" + course + "')");
            System.out.println("Lesson added!");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

    }

    public static void deleteLesson(){
        Connection conn1 = null;
        int teacher, userr;
        int h, m;
        Scanner in = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Data input
            System.out.println("Insert the ID number of the teacher: ");
            teacher = in.nextInt();
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

            //Execute insert query
            Statement st = conn1.createStatement();
            String hs = h + ":" + m;
            st.execute("delete from ripetizione where docente =  '" + teacher +
                    "' and utente = '" + userr + "' and ora_inizio = '" + hs + "'");
            System.out.println("The booked lesson of the user with id=" + userr + " with" +
                    " the teacher \nwith id=" + teacher + " at " + hs + ", is deleted.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

    }
}
