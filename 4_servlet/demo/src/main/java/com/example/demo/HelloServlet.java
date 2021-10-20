package com.example.demo;

import com.example.DAO.DAO;
import com.example.DAO.Teacher;

import java.io.*;
import java.util.ArrayList;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        DAO.registerDriver();

        out.println("<html><body>");
        out.println("<head><link href=\"style.css\" rel=\"stylesheet\"></head>");
        out.println("<h1>" + message + "</h1>");
        printTeachers(out);
        out.println("</body></html>");
    }

    public void destroy() {
    }
}