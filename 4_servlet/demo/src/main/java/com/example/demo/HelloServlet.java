package com.example.demo;

import com.example.DAO.DAO;
import com.example.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Taoooo!";
    }

    public static void printTeachers(PrintWriter out){
        ArrayList<Teacher> teachers = DAO.retrieveTeachers();
        out.println("<h2>Current teachers available: </h2>");
        out.println("<ul>");
        for (Teacher t: teachers) {
            out.println("<li>" + t + "</li>");
        }
        out.println("</ul>");
    }

    public static void printAllPossibleRepetitions(PrintWriter out){
        HashMap<String, HashSet<Teacher>> grid = DAO.retrievePossibleRepetitions();

        for (Map.Entry<String, HashSet<Teacher>> set : grid.entrySet()) {

            out.print("<p>Teacher for " + set.getKey() + ": ");
            for (Teacher teacher: set.getValue()) {
                out.print("| " + teacher.getName() + " " +
                        teacher.getSurname() + " | ");
            }
            out.println("<p>");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        DAO.registerDriver();

        out.println("<html><body>");
        out.println("<head><link href=\"style.css\" rel=\"stylesheet\"></head>");
        out.println("<h1>" + message + "</h1>");
        printAllPossibleRepetitions(out);
        out.println("</body></html>");
    }

    public void destroy() {
    }
}