package com.example.DAO;

import com.example.model.*;

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


    public static Course insertCourse(Course c){
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into corso (titolo, descrizione) values ('" + c.getTitle() +
                    "', '" + c.getDesc() + "')");

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

    public static String deleteCourse(String title){
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

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

    public static Teacher insertTeacher(Teacher t){
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into docente (matricola, nome, cognome) values ('" + t.getId_number() +
                    "', '" + t.getName() + "', '" + t.getSurname() + "')");

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

    public static int deleteTeacher(int id){
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

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

    public static Affilation insertAffilation(Affilation a){
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into affiliazione (matricola_docente, titolo_corso) values ('" + a.getTeacher_id() +
                    "', '" + a.getCourse_title() + "')");

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

    public static Affilation deleteAffilation(Affilation a){
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from affiliazione where matricola_docente =  '" + a.getTeacher_id() +
                    "' and titolo_corso = '" + a.getCourse_title() + "'");

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

    public static void insertLesson(Lesson l){
        Connection conn1 = null;

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("insert into ripetizione (docente, utente, ora_inizio, ora_fine, corso) " +
                    "values ('" + l.getTeacher() + "', '" + l.getUser() + "', '" + l.getH_start() +
                    "', '" + l.getH_end() + "', '" + l.getCourse() + "')");
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

    public static void deleteLesson(Lesson l){
        Connection conn1 = null;

        Scanner in = new Scanner(System.in);

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            //Execute insert query
            Statement st = conn1.createStatement();
            st.execute("delete from ripetizione where docente =  '" + l.getTeacher() +
                    "' and utente = '" + l.getUser() + "' and ora_inizio = '" + l.getH_start() + "'");
            System.out.println("The booked lesson of the user with id=" + l.getUser() + " with" +
                    " the teacher \nwith id=" + l.getTeacher() + " at " + l.getH_start() + ", is deleted.");

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
