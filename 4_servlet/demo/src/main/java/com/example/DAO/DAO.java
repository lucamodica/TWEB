package com.example.DAO;

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

    public static Course insertCourse(){
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

        return c;
    }

    public static String deleteCourse(){
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

        return title;
    }

    public static Teacher insertTeacher(){
        Connection conn1 = null;
        int ID_number;
        String name, surname;
        Scanner in = new Scanner(System.in);
        Teacher t = null;

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
            t = new Teacher(ID_number, name, surname);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into docente (matricola, nome, cognome) values ('" + ID_number +
                    "', '" + name + "', '" + surname + "')");


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

        return t;
    }

    public static int deleteTeacher(){
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

        return id;
    }

    public static Affilation insertAffilation(){
        Connection conn1 = null;
        int ID_number;
        String title;
        Scanner in = new Scanner(System.in);
        Affilation a = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Data input
            System.out.println("Insert the ID number of a teacher: ");
            ID_number = in.nextInt();
            in.nextLine(); //For workaroud to prevent input skipping
            System.out.println("Insert a course title: ");
            title = in.nextLine();
            a = new Affilation(ID_number, title);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into affiliazione (matricola_docente, titolo_corso) values ('" + ID_number +
                    "', '" + title + "')");


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

        return a;
    }

    public static Affilation deleteAffilation(){
        Connection conn1 = null;
        int ID_number;
        String title;
        Scanner in = new Scanner(System.in);
        Affilation a = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Data input
            System.out.println("Insert the ID number of a teacher: ");
            ID_number = in.nextInt();
            in.nextLine(); //For workaroud to prevent input skipping
            System.out.println("Insert a course title: ");
            title = in.nextLine();
            a = new Affilation(ID_number, title);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from affiliazione where matricola_docente =  '" + ID_number +
                    "' and titolo_corso = '" + title + "'");

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

        return a;
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
}
